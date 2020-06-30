package atrue;

import static org.junit.Assert.*;

import org.junit.Test;

import junittest.RequestObject;

public class RequestObjectTest {

	@Test
	public void testGetParameterValue() {
		
//		RequestObject request=new RequestObject("/oa/user/save?");
//		System.out.println(request.getParameterValue("username"));
//		RequestObject request1=new RequestObject("/oa/user/save?username=aa");
//		System.out.println(request1.getParameterValue("username"));
		RequestObject request2=new RequestObject("/oa/user/save?username=zhangsan&gender=1");
		System.out.println(request2.getParameterValue("gender"));
	}

//	@Test
//	public void testGetParameterValues() {
//		
//	}

}
