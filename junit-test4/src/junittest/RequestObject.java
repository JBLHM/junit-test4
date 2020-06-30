package junittest;

import java.util.HashMap;
import java.util.Map;

import oa.servlet.ServletRequestA;

/*
 * 负责封装请求参数
 */
public class RequestObject implements ServletRequestA {
public Map<String,String[]> parameterMap=new HashMap<String,String[]>();
public RequestObject(String requestURI)
{
	/*
	 * requestURI有以下可能 /oa/user/?
	 *  				/oa/user?username=
	 *  				/oa/user?username=admin
	 *  				/oa/user?username=admin&gender=1
	 *  				/oa/user?username=admin&gender=1&interest=food
	 *  				/oa/user?username=admin&gender=1&interest=food&interest=sleep
	 *  
	 */
	/*
	 * 逻辑:判断一:是否有?,是代表请求里有参数,进行判断二,否则不进行任何操作
	 * -?判断二:是否只有一个参数,是则进行判断三,否则进行判断四,
	 * ->判断三:判断是否参数有值  ->判断四:判断是否有&,是则代表请求有多个参数,进行判断五,否进行判断六
	 * ->判断五:判断是否是复选框  判断六:判断是否参数有值,没值时将""作为value,参数作为key
	 */
	//判断requestURI是否包含参数

	if(requestURI.contains("?"))
	{
		
		
		String[] URIAndData=requestURI.split("[?]");
		
		if (URIAndData.length!=0)
		{
			//获取参数部分
			String data=URIAndData[1];
			
			//判断是否有多个参数
			if(data.contains("&"))
			{
				//多个参数
				String[] nameAndValues=data.split("&");
				//遍历nameAndValueAttr
				for(String nameAndValue:nameAndValues)
				{
					String[] nameAndValueAttr=nameAndValue.split("=");
					//判断是否是复选框,复选框多个value对应的key是一样的,结果是存到parameterMap集合中的
					if(parameterMap.containsKey(nameAndValueAttr[0]))
					{
						//多选框
						//获取之前多选框的值存到values数组中
						String values[]=parameterMap.get(nameAndValueAttr[0]);
						//也是存取多选框的值
						String newarray[]=new String[values.length+1];
						//数组复制
						System.arraycopy(values, 0, newarray, 0, values.length);
						//判断参数是否有值
						if(nameAndValueAttr.length>1)
						{
							newarray[newarray.length-1]=nameAndValueAttr[1];
							
						}else
						{
							newarray[newarray.length-1]="";
						}
						parameterMap.put(nameAndValueAttr[0],newarray);
					}
					else
					{
						
						//单选框
						//判断参数是否有值
						if(nameAndValueAttr.length>1)
						{
							//参数有值
							parameterMap.put(nameAndValueAttr[0], new String[]{nameAndValueAttr[1]});
						}
						else {
							
							parameterMap.put(nameAndValueAttr[0], new String[]{""});
						}
					}
				}
			}
			else
			{
				System.out.println("只有一个参数");
				//只有一个参数
				String[] nameAndValueAttr=data.split("=");
				//判断参数是否有值
				if(nameAndValueAttr.length>1)
				{
					System.out.println("参数有值");
					//对应/oa/user?username=admin
					parameterMap.put(nameAndValueAttr[0], new String[]{ nameAndValueAttr[1]});
				}else
				{
					//对应/oa/user?username=
					System.out.println("参数无值");
					parameterMap.put(nameAndValueAttr[0], new String[]{""});
				}
			}
		}

	}
//	else
//	{
//		
//	}
	
	
	

}
/*
 * 获取普通标签参数的值
 * 
 */
public String getParameterValue(String key)
{
	String[] value=parameterMap.get(key);
	return(value !=null && value.length!=0) ? value[0] : null ;
}
/*
 * 获取复选框的值
 * @param key:标签name属性的值
 * @Return String[] 复选框的值
 */
public String[] getParameterValues(String key)
{
	return parameterMap.get(key);
}

}
