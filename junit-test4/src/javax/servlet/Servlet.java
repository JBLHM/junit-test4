package javax.servlet;


import junittest.ServletResponse;
import oa.servlet.ServletRequestA;

/*
 * ���÷�����Ľӿ�
 */
public interface Servlet {

	/*
	 * ����ҵ����ķ���
	 */
	
	

	void service(ServletResponse  responseObject, ServletRequestA  requestObject);
}
