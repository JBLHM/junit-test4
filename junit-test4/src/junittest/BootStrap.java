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
			//��ȡϵͳ�˿ں�
			int port=ServerParser.getPort();
			//��ȡϵͳʱ��
			Long start=System.currentTimeMillis();
			//����web.xml
			String[] webAppNames={"oa"};
			WebParser.parser(webAppNames);
			System.out.println("httpServer start"+"\n"+"port:"+port);
			//logger.info("httpServer start");
			//�����������׽���
		   serverSocket=new ServerSocket(port);
		 
		   Long end=System.currentTimeMillis();
			//logger.info("httpServer start"+(end-start)+"ms");
			System.out.println("httpServer start-end :"+(end-start)+"ms");
			
			//��ͻ��˽�������
			while(true)
			{
				clientSocket=serverSocket.accept();
				new Thread(new HandelRequest(clientSocket)).start();
//				if(clientSocket==null)
//				{
//					System.out.println("��������ʧ��");
//				}
//				else
//				{
//					System.out.println("�������ӳɹ�");
//					
//				}
//				
//				br=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//				//System.out.println("br="+br.toString());
//				String temp=br.readLine();
//				
//				System.out.println("��ȡ���Կͻ�����Ϣ="+temp);
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
