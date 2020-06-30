package junittest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/*
 * *����conf/server.xml
 */
public class ServerParser {

	static int port=8080;
	//��ȡ�˿ں�
	public static int getPort()
	{
		//����������
		SAXReader saxreader=new SAXReader();
		try {
			//����·��,����document������
			Document document =saxreader.read("conf/server.xml");
			//��Ӧxml�ĵ���connector:Ԫ��,port:����(������������XML)
			Element connectorELT=(Element) document.selectSingleNode("//connector");
			 port=Integer.parseInt(connectorELT.attributeValue("port"));
			 
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return port;
	}
}
