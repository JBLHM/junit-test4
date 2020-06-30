package oa.servlet;
/*
 * 负责封装请求参数
 */
public interface ServletRequestA {
	String getParameterValue(String key);
	String[] getParameterValues(String key);

}
