package junittest;

import java.util.HashMap;
import java.util.Map;

import oa.servlet.ServletRequestA;

/*
 * �����װ�������
 */
public class RequestObject implements ServletRequestA {
public Map<String,String[]> parameterMap=new HashMap<String,String[]>();
public RequestObject(String requestURI)
{
	/*
	 * requestURI�����¿��� /oa/user/?
	 *  				/oa/user?username=
	 *  				/oa/user?username=admin
	 *  				/oa/user?username=admin&gender=1
	 *  				/oa/user?username=admin&gender=1&interest=food
	 *  				/oa/user?username=admin&gender=1&interest=food&interest=sleep
	 *  
	 */
	/*
	 * �߼�:�ж�һ:�Ƿ���?,�Ǵ����������в���,�����ж϶�,���򲻽����κβ���
	 * -?�ж϶�:�Ƿ�ֻ��һ������,��������ж���,��������ж���,
	 * ->�ж���:�ж��Ƿ������ֵ  ->�ж���:�ж��Ƿ���&,������������ж������,�����ж���,������ж���
	 * ->�ж���:�ж��Ƿ��Ǹ�ѡ��  �ж���:�ж��Ƿ������ֵ,ûֵʱ��""��Ϊvalue,������Ϊkey
	 */
	//�ж�requestURI�Ƿ��������

	if(requestURI.contains("?"))
	{
		
		
		String[] URIAndData=requestURI.split("[?]");
		
		if (URIAndData.length!=0)
		{
			//��ȡ��������
			String data=URIAndData[1];
			
			//�ж��Ƿ��ж������
			if(data.contains("&"))
			{
				//�������
				String[] nameAndValues=data.split("&");
				//����nameAndValueAttr
				for(String nameAndValue:nameAndValues)
				{
					String[] nameAndValueAttr=nameAndValue.split("=");
					//�ж��Ƿ��Ǹ�ѡ��,��ѡ����value��Ӧ��key��һ����,����Ǵ浽parameterMap�����е�
					if(parameterMap.containsKey(nameAndValueAttr[0]))
					{
						//��ѡ��
						//��ȡ֮ǰ��ѡ���ֵ�浽values������
						String values[]=parameterMap.get(nameAndValueAttr[0]);
						//Ҳ�Ǵ�ȡ��ѡ���ֵ
						String newarray[]=new String[values.length+1];
						//���鸴��
						System.arraycopy(values, 0, newarray, 0, values.length);
						//�жϲ����Ƿ���ֵ
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
						
						//��ѡ��
						//�жϲ����Ƿ���ֵ
						if(nameAndValueAttr.length>1)
						{
							//������ֵ
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
				System.out.println("ֻ��һ������");
				//ֻ��һ������
				String[] nameAndValueAttr=data.split("=");
				//�жϲ����Ƿ���ֵ
				if(nameAndValueAttr.length>1)
				{
					System.out.println("������ֵ");
					//��Ӧ/oa/user?username=admin
					parameterMap.put(nameAndValueAttr[0], new String[]{ nameAndValueAttr[1]});
				}else
				{
					//��Ӧ/oa/user?username=
					System.out.println("������ֵ");
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
 * ��ȡ��ͨ��ǩ������ֵ
 * 
 */
public String getParameterValue(String key)
{
	String[] value=parameterMap.get(key);
	return(value !=null && value.length!=0) ? value[0] : null ;
}
/*
 * ��ȡ��ѡ���ֵ
 * @param key:��ǩname���Ե�ֵ
 * @Return String[] ��ѡ���ֵ
 */
public String[] getParameterValues(String key)
{
	return parameterMap.get(key);
}

}
