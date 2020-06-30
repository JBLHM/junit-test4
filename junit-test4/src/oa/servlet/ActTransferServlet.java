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
		//��ȡҳ���������
		String actFrom= requestObject.getParameterValue("actFrom");
		double balance= Double.parseDouble(requestObject.getParameterValue("balance"));
		String actTo= requestObject.getParameterValue("actTo");
		//����������ת��
		
		if(actFrom.contains("%"))
		{
			try {
//				actFrom=URLEncoder.encode(actFrom, "gbk");
				//�������±���ο�����:https://blog.csdn.net/ITBigGod/article/details/83750336
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
		
		System.out.println("ת���˺�:"+actFrom+"ת�����:"+balance+"ת���˺�:"+actTo);
		//�������ݿ�
		Connection conn=null;
		PreparedStatement ps=null;
		int count=0;
		try
		{
			//ע������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ���ݿ�����
			String url="jdbc:mysql://localhost:3306/jserver?serverTimezone=UTC&amp&characterEncoding=UTF-8&amp&useSSl=true";
			String user="root";
			String password="root";
			conn=DriverManager.getConnection(url, user, password);
			//��������
			//�ر��Զ��ύ
			conn.setAutoCommit(false);
			

			//����sql���Կ��
			String sql_from="update t_act set balance=balance-? where actno= ?";
			//sql����Ԥ����
			ps=conn.prepareStatement(sql_from);
			//���и�ֵ
			ps.setDouble(1, balance);
			ps.setString(2, actFrom);
			//ִ��sql���
			count=ps.executeUpdate();
			System.out.println("һ��ʼcount="+count);

			//����sql���Կ��
			String sql_to="update t_act set balance=balance+? where actno= ?";
			//sql����Ԥ����
			ps=conn.prepareStatement(sql_to);
			
			//���и�ֵ
			ps.setDouble(1, balance);
			ps.setString(2, actTo);
			count=count+ps.executeUpdate();
			
			
			//�ж��Ƿ񳬶�,�������<0������쳣,Ȼ��ع�
			String sql_check="select balance from t_act where actno=? and balance>0";	
			ps=conn.prepareStatement(sql_check);
			ps.setString(1, actFrom);
			int getbalance=ps.executeUpdate();
			if(getbalance<0)
			{
				System.out.println("����,��ʼ�ع�");
				count=0;
				conn.rollback();
				
				
			}
			
			
			
			
			//�ύ
			conn.commit();
			System.out.println("���count="+count);
		}catch(Exception e)
		{
			try {
				System.out.println("������,��ʼ�ع�");
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
			out.print("<title>�����˻�-ת�˽��</title>");
			out.print("<meta content='text/html charset=utf-8'/");
			out.print("</head>");
			out.print("<body>");
			out.print("<center><font size='35px' color='green'>ת�˳ɹ�!</font></center>");
			out.print("</body>");
			out.print("</html>");
				
			}
			else
			{	out.print("HTTP/1.1 200 OK\n");
				out.print("Content-Type=text/html;charset=utf-8\n\n");
				out.print("<html>");
				out.print("<head>");
				out.print("<title>�����˻�-ת�˽��</title>");
				out.print("<meta content='text/html charset=utf-8'/");
				out.print("</head>");
				out.print("<body>");
				out.print("<center><font size='35px' color='red'>ת��ʧ��!</font></center>");
				out.print("</body>");
				out.print("</html>");
					
			}
			
			
		}
	}

}
