package oa.servlet;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.Servlet;

import junittest.ServletResponse;

public class ActTransferServlet implements Servlet{

	@Override
	public void service(ServletResponse responseObject, ServletRequestA requestObject) {
		// TODO Auto-generated method stub
		//获取页面请求参数
		String actFrom= requestObject.getParameterValue("actFrom");
		double balance= Double.parseDouble(requestObject.getParameterValue("balance"));
		String actTo= requestObject.getParameterValue("actTo");
		//若是中文则转码
		
		if(actFrom.contains("%"))
		{
			try {
//				actFrom=URLEncoder.encode(actFrom, "gbk");
				//解码重新编码参考资料:https://blog.csdn.net/ITBigGod/article/details/83750336
				actFrom=URLDecoder.decode(actFrom,"utf-8");
			} catch (UnsupportedEncodingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		if(actTo.contains("%"))
		{
			try {
//				actTo=URLEncoder.encode(actTo, "gbk");
				actTo=URLDecoder.decode(actTo,"utf-8");
			} catch (UnsupportedEncodingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		
		System.out.println("转出账号:"+actFrom+"转出金额:"+balance+"转入账号:"+actTo);
		//连接数据库
		Connection conn=null;
		PreparedStatement ps=null;
		int count=0;
		try
		{
			//注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取数据库连接
			String url="jdbc:mysql://localhost:3306/jserver?serverTimezone=UTC&amp&characterEncoding=UTF-8&amp&useSSl=true";
			String user="root";
			String password="root";
			conn=DriverManager.getConnection(url, user, password);
			//开启事务
			//关闭自动提交
			conn.setAutoCommit(false);
			

			//定义sql语言框架
			String sql_from="update t_act set balance=balance-? where actno= ?";
			//sql语言预编译
			ps=conn.prepareStatement(sql_from);
			//进行赋值
			ps.setDouble(1, balance);
			ps.setString(2, actFrom);
			//执行sql语句
			count=ps.executeUpdate();
			System.out.println("一开始count="+count);

			//定义sql语言框架
			String sql_to="update t_act set balance=balance+? where actno= ?";
			//sql语言预编译
			ps=conn.prepareStatement(sql_to);
			
			//进行赋值
			ps.setDouble(1, balance);
			ps.setString(2, actTo);
			count=count+ps.executeUpdate();
			
			
			//判断是否超额,若是余额<0则产生异常,然后回滚
			String sql_check="select balance from t_act where actno=? and balance>0";	
			ps=conn.prepareStatement(sql_check);
			ps.setString(1, actFrom);
			int getbalance=ps.executeUpdate();
			if(getbalance<0)
			{
				System.out.println("余额不足,开始回滚");
				count=0;
				conn.rollback();
				
				
			}
			
			
			
			
			//提交
			conn.commit();
			System.out.println("最后count="+count);
		}catch(Exception e)
		{
			try {
				System.out.println("出错了,开始回滚");
				count=0;
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally
		{
			if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
							}
			PrintWriter out=responseObject.getOut();
			if(count==2)
			{
			out.print("HTTP/1.1 200 OK\n");
			out.print("Content-Type=text/html;charset=utf-8\n\n");
			out.print("<html>");
			out.print("<head>");
			out.print("<title>银行账户-转账结果</title>");
			out.print("<meta content='text/html charset=utf-8'/");
			out.print("</head>");
			out.print("<body>");
			out.print("<center><font size='35px' color='green'>转账成功!</font></center>");
			out.print("</body>");
			out.print("</html>");
				
			}
			else
			{	out.print("HTTP/1.1 200 OK\n");
				out.print("Content-Type=text/html;charset=utf-8\n\n");
				out.print("<html>");
				out.print("<head>");
				out.print("<title>银行账户-转账结果</title>");
				out.print("<meta content='text/html charset=utf-8'/");
				out.print("</head>");
				out.print("<body>");
				out.print("<center><font size='35px' color='red'>转账失败!</font></center>");
				out.print("</body>");
				out.print("</html>");
					
			}
			
			
		}
	}

}
