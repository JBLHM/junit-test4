package oa.servlet;

import java.io.PrintWriter;

import javax.servlet.Servlet;


import junittest.ServletResponse;
/*
 * �����½�߼�����
 */
public class Loginservlet implements Servlet {

	//����ҵ����ķ���
	


	

	

	@Override
	public void service(ServletResponse responseObject, ServletRequestA requestObject) {
		// TODO Auto-generated method stub
		System.out.println("����Loginservlet");
		PrintWriter out=responseObject.getOut();
		out.print("HTTP/1.1 200 OK\n");
		out.print("Content-Type=text/html;charset=utf-8\n\n");
		out.print("<html>");
		out.print("<head>");
		out.print("<title>������֤</title>");
		out.print("<meta content='text/html charset=utf-8'/");
		out.print("</head>");
		out.print("<body>");
		out.print("<center><font size='35px' color='blue'>������֤��,���Ե�</font></center>");
		out.print("</body>");
		out.print("</html>");
	}



	
}
