package junittest;

import java.io.PrintWriter;

/*
 * ��װ��Ӧ���ӿ�
 */
public interface ServletResponse {
	PrintWriter getOut();
	void setOut(PrintWriter out);
}
