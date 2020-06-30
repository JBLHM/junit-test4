package junittest;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.Servlet;
/*
 * servlet对象连接池
 */
public class ServletCache {
private static Map<String,Servlet> servletMap=new HashMap<String,Servlet>();
public static void put(String urlPattern,Servlet servlet) 
{
	servletMap.put(urlPattern, servlet);
}
public static Servlet get(String urlPattern)
{
	return servletMap.get(urlPattern);
}
}
