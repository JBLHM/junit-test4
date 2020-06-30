package junittest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.dom4j.DocumentException;


public class BootStrap {

 static BufferedReader br=null;
 static Socket clientSocket=null;
 static ServerSocket serverSocket=null;
	public static void main(String[] args)
	{
		
		start();
	}
	public static void start() 
	{
		try {
			//获取系统端口号
			int port=ServerParser.getPort();
			//获取系统时间
			Long start=System.currentTimeMillis();
			//解析web.xml
			String[] webAppNames={"oa"};
			WebParser.parser(webAppNames);
			System.out.println("httpServer start"+"\n"+"port:"+port);
			//logger.info("httpServer start");
			//创建服务器套接字
		   serverSocket=new ServerSocket(port);
		 
		   Long end=System.currentTimeMillis();
			//logger.info("httpServer start"+(end-start)+"ms");
			System.out.println("httpServer start-end :"+(end-start)+"ms");
			
			//与客户端建立连接
			while(true)
			{
				clientSocket=serverSocket.accept();
				new Thread(new HandelRequest(clientSocket)).start();
//				if(clientSocket==null)
//				{
//					System.out.println("建立连接失败");
//				}
//				else
//				{
//					System.out.println("建立连接成功");
//					
//				}
//				
//				br=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//				//System.out.println("br="+br.toString());
//				String temp=br.readLine();
//				
//				System.out.println("读取来自客户端信息="+temp);
//				while((temp = br.readLine()) != null)
//				{
//					System.out.println("temp="+temp);
//					
//				}
				
				
				
				
			}
				
				  
			
		
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(DocumentException e)
		{
			e.printStackTrace();
		}
	}
}
