package javax.servlet;


import junittest.ServletResponse;
import oa.servlet.ServletRequestA;

/*
 * 调用服务处理的接口
 */
public interface Servlet {

	/*
	 * 处理业务核心方法
	 */
	
	

	void service(ServletResponse  responseObject, ServletRequestA  requestObject);
}
