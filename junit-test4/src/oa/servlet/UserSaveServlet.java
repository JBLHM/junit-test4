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
 * ��ȡ�û���,�����
 */
public class UserSaveServlet implements Servlet{

	

	@Override
	public void service(ServletResponse responseObject, ServletRequestA requestObject)  {
		// TODO Auto-generated method stub
	
		String username=requestObject.getParameterValue("username");
		String gender=requestObject.getParameterValue("gender");
		
		
		System.out.println("�û���:"+username);
		System.out.println("�Ա�:"+gender);
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
		System.out.println("��Ȥ:"+aa);
		}
		System.out.println("��Ȥinterests:"+interests);
		PrintWriter out=responseObject.getOut();
		out.print("HTTP/1.1 200 OK\n");
		out.print("Content-Type=text/html;charset=utf-8\n\n");
		out.print("<html>");
		out.print("<head>");
		out.print("<title>�û���Ϣ</title>");
		out.print("<meta content='text/html charset=utf-8'/");
		out.print("</head>");
		out.print("<body>");
		out.print("<center><font size='35px' color='blue'>�û���Ϣ</font></center>");
		out.print("�û���:"+username+"<br>");
		out.print("�Ա�:"+gender+"<br>");
		out.print("��Ȥ:"+interests+"<br>");
		out.print("</body>");
		out.print("</html>");
	}


}
