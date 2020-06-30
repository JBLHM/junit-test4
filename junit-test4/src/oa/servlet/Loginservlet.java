package oa.servlet;

import java.io.PrintWriter;

import javax.servlet.Servlet;


import junittest.ServletResponse;
/*
 * 处理登陆逻辑方法
 */
public class Loginservlet implements Servlet {

	//处理业务核心方法
	


	

	

	@Override
	public void service(ServletResponse responseObject, ServletRequestA requestObject) {
		// TODO Auto-generated method stub
		System.out.println("进入Loginservlet");
		PrintWriter out=responseObject.getOut();
		out.print("HTTP/1.1 200 OK\n");
		out.print("Content-Type=text/html;charset=utf-8\n\n");
		out.print("<html>");
		out.print("<head>");
		out.print("<title>正在验证</title>");
		out.print("<meta content='text/html charset=utf-8'/");
		out.print("</head>");
		out.print("<body>");
		out.print("<center><font size='35px' color='blue'>正在验证中,请稍等</font></center>");
		out.print("</body>");
		out.print("</html>");
	}



	
}
