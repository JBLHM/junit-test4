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
	 //接收客户端消息
	 public BufferedReader br=null;
	//获取响应流对象
	 static PrintWriter out=null;
	 public HandelRequest(Socket clientSocket) {
			
			this.clientSocket= clientSocket;
			// TODO Auto-generated constructor stub
		}
	 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			//接收客户端消息
			br=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			//获得响应流对象
			
			out=new PrintWriter(clientSocket.getOutputStream());
		
			
//			int i=1;
//			while((temp = br.readLine()) != null)
//			{
//				System.out.println("第"+i+"个temp=="+temp);
//				i++;
//				
//			}
			
			//事实上,客户端传过来的不是字符串而是http请求
			//System.out.println("事实上,客户端传过来的不是字符串而是http请求");
			String requestLine=br.readLine();
			System.out.println("发起的请求="+requestLine);
			String requestURI=requestLine.split(" ")[1];
			
			//判断是否是静态网页,如.html,.htm
			if(requestURI.contains(".html")||requestURI.contains(".htm"))
			{
				//处理静态网页
				responseStaticPage(requestURI,out);
				System.out.println("处理静态网页中....,请求:"+requestURI);
				
			}
			else
			{
				System.out.println("处理动态网页中");
				//处理动态网页
				String servletPath=requestURI;
				//判断是否是/oa/login?username=..&&password=...
				
				if(servletPath.contains("?"))
				{
					
					servletPath=servletPath.split("[?]")[0];
					
				
				}
				//实际上不可能写死成/oa/login,所以要获取路径
//				if(servletPath.equals("/oa/login"))
//				{
//					Loginservlet loginservlet =new Loginservlet();
//					loginservlet.service();
//				}
				
				String webAppName=servletPath.split("[/]")[1];
				System.out.println("webAppName:"+webAppName);
				//获取servletMaps集合中的value //如/oa/login找的是oa
				Map<String,String> servletMap=WebParser.servletMaps.get(webAppName);
				//获取servletMap集合中的key
				String urlPattern=servletPath.substring(1+webAppName.length());
				//获取servletClassname
				System.out.println("urlPattern="+urlPattern);
				for(String amap:servletMap.keySet())
				{
					System.out.println("servletMapkey集key:"+amap);
					
				}
				for(String amapset:servletMap.values())
				{
					System.out.println("servletMap的value集:"+amapset);
					
				}
				String servletClassName=servletMap.get(urlPattern);
				//通过反射机制创建该业务处理类
				//判断该业务处理的sevlet类是否存在
				if(servletClassName!=null)
				{
					//获取封装响应参数对象
					
					//把out对象封装起来
					ResponseObject responseObject=new ResponseObject();
					
					responseObject.setOut(out);
					//获取封装请求参数对象
					RequestObject requestObject=new RequestObject(requestURI);
					
					try {
						//从缓存池中拿到servlet对象
						Servlet servlet=ServletCache.get(urlPattern);
						if(servlet==null)
						{
							Class c = Class.forName(servletClassName);
							Object obj=c.newInstance();
							servlet=(Servlet)obj;
							//将创建的servlet对象放到缓存池中
							ServletCache.put(urlPattern, servlet);
						}
						
						//每次http://localhost:8080/oa/usersave.html提交获取到的servlet是不一样的,如何一样呢??:从缓存池中找,没有创建,有则直接使用
						//这时候,服务开发人员不知道怎么调用方法,如不知道怎么调用RequestObject类里的方法
						System.out.println("servlet对象"+servlet+"\n");
						
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
					html.append("<title>404-错误</title>");
					html.append("<meta content='text/html charset=utf-8'/");
					html.append("</head>");
					html.append("<body>");
					html.append("<center><font size='35px' color='red'>404-Not Found</font></center>");
					html.append("</body>");
					
					html.append("</html>");
					out.print(html);
				}
				
				
			}
			//强制刷新
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
System.out.println("静态网页requestURI="+requestURI);
		String htmlPath=requestURI.substring(1);
		//读取页面
		try {
			br=new BufferedReader(new FileReader(htmlPath));
			//StringBuilder:修改字符串而不创建新的对象
			StringBuilder html=new StringBuilder(); 
			String temp=null;
			//拼接响应信息
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
			html.append("<title>404-错误</title>");
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
