package junittest;

import java.io.PrintWriter;

/*
 * 复制封装响应对象参数
 * @author:zzh
 * @version 1.1
 * @since 1.0
 * 
 */
public class ResponseObject implements ServletResponse  {

	private PrintWriter out=null;

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}
	
}
