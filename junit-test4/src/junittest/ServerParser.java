package junittest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/*
 * *解析conf/server.xml
 */
public class ServerParser {

	static int port=8080;
	//获取端口号
	public static int getPort()
	{
		//创建解析器
		SAXReader saxreader=new SAXReader();
		try {
			//解析路径,生成document对象树
			Document document =saxreader.read("conf/server.xml");
			//对应xml文档里connector:元素,port:属性(可以这样看待XML)
			Element connectorELT=(Element) document.selectSingleNode("//connector");
			 port=Integer.parseInt(connectorELT.attributeValue("port"));
			 
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return port;
	}
}
