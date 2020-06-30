package oa.servlet;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;

import junittest.RequestObject;
import junittest.ResponseObject;
import junittest.ServletResponse;
/*
 * 获取用户名,密码等
 */
public class UserSaveServlet implements Servlet{

	

	@Override
	public void service(ServletResponse responseObject, ServletRequestA requestObject)  {
		// TODO Auto-generated method stub
	
		String username=requestObject.getParameterValue("username");
		String gender=requestObject.getParameterValue("gender");
		
		
		System.out.println("用户名:"+username);
		System.out.println("性别:"+gender);
		if(username!=null)
		{
			if(username.contains("%"))
				try {
					username=URLDecoder.decode(username,"utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		else
		{
			
		}
		
		String[] interest=requestObject.getParameterValues("interest");
		StringBuilder interests=new StringBuilder();
		for(String aa:interest)
		{
			interests.append(aa).append(" ");
		System.out.println("兴趣:"+aa);
		}
		System.out.println("兴趣interests:"+interests);
		PrintWriter out=responseObject.getOut();
		out.print("HTTP/1.1 200 OK\n");
		out.print("Content-Type=text/html;charset=utf-8\n\n");
		out.print("<html>");
		out.print("<head>");
		out.print("<title>用户信息</title>");
		out.print("<meta content='text/html charset=utf-8'/");
		out.print("</head>");
		out.print("<body>");
		out.print("<center><font size='35px' color='blue'>用户信息</font></center>");
		out.print("用户名:"+username+"<br>");
		out.print("性别:"+gender+"<br>");
		out.print("兴趣:"+interests+"<br>");
		out.print("</body>");
		out.print("</html>");
	}


}
