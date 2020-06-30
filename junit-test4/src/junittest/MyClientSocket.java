package junittest;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClientSocket {
	 static Socket clientSocket=null;
	 static PrintWriter out=null;
public static void main(String[] args)
{
	try {
		clientSocket=new Socket("localhost",8080);
		out=new PrintWriter(clientSocket.getOutputStream());
		String msg="hello hhz";
		//out.write(msg);
		//out.print(msg);
		
		//System.out.println("客户端传过来信息:"+msg);
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		if(out!=null)
		{
			try {
				out.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
			
		if(clientSocket!=null)
		{
			try {
				clientSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
			
		
	}
}
}
