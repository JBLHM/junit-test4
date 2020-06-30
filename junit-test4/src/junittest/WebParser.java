package junittest;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/*
 * 解析web.xml
 */
public class WebParser {

	/*
	 * 解析服务应用程序的多个web.xml,多个应用对应有多个web.xml
	 */
	//注意:不是new HashMap<String,HashMap<String,String>>();
	public static Map<String,Map<String,String>> servletMaps=new HashMap<String,Map<String,String>>();
	static Document document=null;
	//webAppName:服务中所有应用名称
	public static void parser(String[] webAppNames) throws DocumentException
	{
		for(String webAppName:webAppNames)
		{
			Map<String,String> servletMap=parser(webAppName);
			servletMaps.put(webAppName, servletMap);
		}
		
	}
	//一个服务的xml解析
	/*
	 * 一个应用对应一个web.xml
	 */
	public static Map<String,String> parser(String webAppName) throws DocumentException
	{
		//根据web应用确定对应的web.xml路径
		String webPath=webAppName+"/WEB-INF/web.xml";
		//创建xml解析器
		SAXReader saxReader=new SAXReader();
		 document=saxReader.read(new File(webPath));
		//路径web-app->servlet,用list接收
		List<Element> servletNodes=document.selectNodes("/web-app/servlet");
		Map<String,String> servletInfoMap=new HashMap<String,String>();
		for(Element servletNode:servletNodes)
		{
			//获取servlet-name元素对象
			Element servletNameElt=(Element) servletNode.selectSingleNode("servlet-name");
			//获取servlet-name元素对象的值
			String servletName=servletNameElt.getStringValue();
			//获取servlet-name元素对象
			Element servletClassNameElt=(Element) servletNode.selectSingleNode("servlet-class");
			String servletClassName=servletClassNameElt.getStringValue();
			servletInfoMap.put(servletName,servletClassName);
		}
		//找到路径web-app-servlet-Mapping
		List<Element> servletMappingNodes=document.selectNodes("/web-app/servlet-mapping");
		Map<String,String> servletMappingInfoMap=new HashMap<String,String>();
		//遍历web-app-servlet-Mapping标签内容
		for(Element servletMappingNode:servletMappingNodes )
		{
			//获取servlet-name元素对象
			Element servletNameElt=(Element) servletMappingNode.selectSingleNode("servlet-name");
			//获取servlet-name元素对象的值
			String servletName=servletNameElt.getStringValue();
			//获取url-pattern元素对象
			Element urlPatternElt=(Element) servletMappingNode.selectSingleNode("url-pattern");
			////获取url-pattern元素对象的值
			String urlPattern=urlPatternElt.getStringValue();
			servletMappingInfoMap.put(servletName,urlPattern);
		}
		//获取servletMappingInfoMap和servletInfoMap结果集的key(两个的key都是servlet-name)作为新map的key
		Set<String> servletNames=servletInfoMap.keySet();
		Map<String,String> servletMap=new HashMap<String,String>();
		
		//遍历servletNames
		for(String   servletName:servletNames)
		{
			//获取servletInfoMap的value
			String servletClassName=servletInfoMap.get(servletName);
			//获取servletInfoMap的value
			String urlPattern=servletMappingInfoMap.get(servletName);
			//urlPattern作为servletMap的key,ervletClassName作为servletMap的value
			servletMap.put(urlPattern, servletClassName);
			
		}
		return servletMap;
		
	}
}
