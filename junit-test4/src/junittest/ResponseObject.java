package junittest;

import java.io.PrintWriter;

/*
 * ���Ʒ�װ��Ӧ�������
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
