package atrue;

import junittest.RequestObject;

public class RequestObjectMy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RequestObject request2=new RequestObject("/oa/user/save?username=zhangsan&gender=1&food=2&food=3");
		System.out.println("只返回一个结果:"+request2.getParameterValue("food")+"\n");
		RequestObject request3=new RequestObject("/oa/user/save?username=zhangsan&gender=1&food=2&food=3");
		String[] result=request3.getParameterValues("food");
		
		for(String aa:result)
		{
			System.out.println(aa);
		}
		
	}

}
