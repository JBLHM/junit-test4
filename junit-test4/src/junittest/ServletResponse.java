package junittest;

import java.io.PrintWriter;

/*
 * 封装响应流接口
 */
public interface ServletResponse {
	PrintWriter getOut();
	void setOut(PrintWriter out);
}
