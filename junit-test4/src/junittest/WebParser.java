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
 * ����web.xml
 */
public class WebParser {

	/*
	 * ��������Ӧ�ó���Ķ��web.xml,���Ӧ�ö�Ӧ�ж��web.xml
	 */
	//ע��:����new HashMap<String,HashMap<String,String>>();
	public static Map<String,Map<String,String>> servletMaps=new HashMap<String,Map<String,String>>();
	static Document document=null;
	//webAppName:����������Ӧ������
	public static void parser(String[] webAppNames) throws DocumentException
	{
		for(String webAppName:webAppNames)
		{
			Map<String,String> servletMap=parser(webAppName);
			servletMaps.put(webAppName, servletMap);
		}
		
	}
	//һ�������xml����
	/*
	 * һ��Ӧ�ö�Ӧһ��web.xml
	 */
	public static Map<String,String> parser(String webAppName) throws DocumentException
	{
		//����webӦ��ȷ����Ӧ��web.xml·��
		String webPath=webAppName+"/WEB-INF/web.xml";
		//����xml������
		SAXReader saxReader=new SAXReader();
		 document=saxReader.read(new File(webPath));
		//·��web-app->servlet,��list����
		List<Element> servletNodes=document.selectNodes("/web-app/servlet");
		Map<String,String> servletInfoMap=new HashMap<String,String>();
		for(Element servletNode:servletNodes)
		{
			//��ȡservlet-nameԪ�ض���
			Element servletNameElt=(Element) servletNode.selectSingleNode("servlet-name");
			//��ȡservlet-nameԪ�ض����ֵ
			String servletName=servletNameElt.getStringValue();
			//��ȡservlet-nameԪ�ض���
			Element servletClassNameElt=(Element) servletNode.selectSingleNode("servlet-class");
			String servletClassName=servletClassNameElt.getStringValue();
			servletInfoMap.put(servletName,servletClassName);
		}
		//�ҵ�·��web-app-servlet-Mapping
		List<Element> servletMappingNodes=document.selectNodes("/web-app/servlet-mapping");
		Map<String,String> servletMappingInfoMap=new HashMap<String,String>();
		//����web-app-servlet-Mapping��ǩ����
		for(Element servletMappingNode:servletMappingNodes )
		{
			//��ȡservlet-nameԪ�ض���
			Element servletNameElt=(Element) servletMappingNode.selectSingleNode("servlet-name");
			//��ȡservlet-nameԪ�ض����ֵ
			String servletName=servletNameElt.getStringValue();
			//��ȡurl-patternԪ�ض���
			Element urlPatternElt=(Element) servletMappingNode.selectSingleNode("url-pattern");
			////��ȡurl-patternԪ�ض����ֵ
			String urlPattern=urlPatternElt.getStringValue();
			servletMappingInfoMap.put(servletName,urlPattern);
		}
		//��ȡservletMappingInfoMap��servletInfoMap�������key(������key����servlet-name)��Ϊ��map��key
		Set<String> servletNames=servletInfoMap.keySet();
		Map<String,String> servletMap=new HashMap<String,String>();
		
		//����servletNames
		for(String   servletName:servletNames)
		{
			//��ȡservletInfoMap��value
			String servletClassName=servletInfoMap.get(servletName);
			//��ȡservletInfoMap��value
			String urlPattern=servletMappingInfoMap.get(servletName);
			//urlPattern��ΪservletMap��key,ervletClassName��ΪservletMap��value
			servletMap.put(urlPattern, servletClassName);
			
		}
		return servletMap;
		
	}
}
