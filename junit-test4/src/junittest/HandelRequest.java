package junittest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

import javax.servlet.Servlet;

import oa.servlet.Loginservlet;

public class HandelRequest implements Runnable {
	 public Socket clientSocket=null;
	 //���տͻ�����Ϣ
	 public BufferedReader br=null;
	//��ȡ��Ӧ������
	 static PrintWriter out=null;
	 public HandelRequest(Socket clientSocket) {
			
			this.clientSocket= clientSocket;
			// TODO Auto-generated constructor stub
		}
	 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			//���տͻ�����Ϣ
			br=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			//�����Ӧ������
			
			out=new PrintWriter(clientSocket.getOutputStream());
		
			
//			int i=1;
//			while((temp = br.readLine()) != null)
//			{
//				System.out.println("��"+i+"��temp=="+temp);
//				i++;
//				
//			}
			
			//��ʵ��,�ͻ��˴������Ĳ����ַ�������http����
			//System.out.println("��ʵ��,�ͻ��˴������Ĳ����ַ�������http����");
			String requestLine=br.readLine();
			System.out.println("���������="+requestLine);
			String requestURI=requestLine.split(" ")[1];
			
			//�ж��Ƿ��Ǿ�̬��ҳ,��.html,.htm
			if(requestURI.contains(".html")||requestURI.contains(".htm"))
			{
				//����̬��ҳ
				responseStaticPage(requestURI,out);
				System.out.println("����̬��ҳ��....,����:"+requestURI);
				
			}
			else
			{
				System.out.println("����̬��ҳ��");
				//����̬��ҳ
				String servletPath=requestURI;
				//�ж��Ƿ���/oa/login?username=..&&password=...
				
				if(servletPath.contains("?"))
				{
					
					servletPath=servletPath.split("[?]")[0];
					
				
				}
				//ʵ���ϲ�����д����/oa/login,����Ҫ��ȡ·��
//				if(servletPath.equals("/oa/login"))
//				{
//					Loginservlet loginservlet =new Loginservlet();
//					loginservlet.service();
//				}
				
				String webAppName=servletPath.split("[/]")[1];
				System.out.println("webAppName:"+webAppName);
				//��ȡservletMaps�����е�value //��/oa/login�ҵ���oa
				Map<String,String> servletMap=WebParser.servletMaps.get(webAppName);
				//��ȡservletMap�����е�key
				String urlPattern=servletPath.substring(1+webAppName.length());
				//��ȡservletClassname
				System.out.println("urlPattern="+urlPattern);
				for(String amap:servletMap.keySet())
				{
					System.out.println("servletMapkey��key:"+amap);
					
				}
				for(String amapset:servletMap.values())
				{
					System.out.println("servletMap��value��:"+amapset);
					
				}
				String servletClassName=servletMap.get(urlPattern);
				//ͨ��������ƴ�����ҵ������
				//�жϸ�ҵ�����sevlet���Ƿ����
				if(servletClassName!=null)
				{
					//��ȡ��װ��Ӧ��������
					
					//��out�����װ����
					ResponseObject responseObject=new ResponseObject();
					
					responseObject.setOut(out);
					//��ȡ��װ�����������
					RequestObject requestObject=new RequestObject(requestURI);
					
					try {
						//�ӻ�������õ�servlet����
						Servlet servlet=ServletCache.get(urlPattern);
						if(servlet==null)
						{
							Class c = Class.forName(servletClassName);
							Object obj=c.newInstance();
							servlet=(Servlet)obj;
							//��������servlet����ŵ��������
							ServletCache.put(urlPattern, servlet);
						}
						
						//ÿ��http://localhost:8080/oa/usersave.html�ύ��ȡ����servlet�ǲ�һ����,���һ����??:�ӻ��������,û�д���,����ֱ��ʹ��
						//��ʱ��,���񿪷���Ա��֪����ô���÷���,�粻֪����ô����RequestObject����ķ���
						System.out.println("servlet����"+servlet+"\n");
						
						//servlet.service(out);
						servlet.service(responseObject,requestObject);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					StringBuilder html=new StringBuilder(); 
					html.append("HTTP/1.1 404 NotFound\n");
					html.append("Content-Type=text/html;charset=utf-8\n\n");
					html.append("<html>");
					html.append("<head>");
					html.append("<title>404-����</title>");
					html.append("<meta content='text/html charset=utf-8'/");
					html.append("</head>");
					html.append("<body>");
					html.append("<center><font size='35px' color='red'>404-Not Found</font></center>");
					html.append("</body>");
					
					html.append("</html>");
					out.print(html);
				}
				
				
			}
			//ǿ��ˢ��
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			if(br!=null)
				try
			{
					br.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			if(clientSocket!=null)
			{
				try
				{
					clientSocket.close();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}


	}

	private void responseStaticPage(String requestURI, PrintWriter out2) {
		// TODO Auto-generated method stub
		//requestURI:oa/index.html
System.out.println("��̬��ҳrequestURI="+requestURI);
		String htmlPath=requestURI.substring(1);
		//��ȡҳ��
		try {
			br=new BufferedReader(new FileReader(htmlPath));
			//StringBuilder:�޸��ַ������������µĶ���
			StringBuilder html=new StringBuilder(); 
			String temp=null;
			//ƴ����Ӧ��Ϣ
			html.append("HTTP/1.1 200 OK\n");
			html.append("Content-type=text/html charaset=utf-8\n\n");
				while((temp=br.readLine())!=null)
				{
					html.append(temp);
				}
				out.print(html);
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			StringBuilder html=new StringBuilder(); 
			html.append("HTTP/1.1 404 NotFound\n");
			html.append("Content-Type=text/html;charset=utf-8\n\n");
			html.append("<html>");
			html.append("<head>");
			html.append("<title>404-����</title>");
			html.append("<meta content='text/html charset=utf-8'/");
			html.append("</head>");
			html.append("<body>");
			html.append("<center><font size='35px' color='red'>404-Not Found</font></center>");
			html.append("</body>");
			
			html.append("</html>");
			out.print(html);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
