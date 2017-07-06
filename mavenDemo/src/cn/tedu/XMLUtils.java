package cn.tedu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLUtils {
	private XMLUtils(){}
	
	/**
	 * ��document����д��ָ�����ļ���
	 * @param dom 
	 * @param xmlpath
	 */
	public static void write2Xml(Document dom, String xmlpath){
		try {
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(
					new File(xmlpath)), "utf-8");
			// ������ʽ�����������
			OutputFormat format = OutputFormat.createPrettyPrint();
			// OutputFormat format = OutputFormat.createCompactFormat();

			XMLWriter writer = new XMLWriter(out, format);
			writer.write(dom);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * ����xml�ļ�, ����document����
	 * @param xmlpath
	 * @return
	 */
	public static Document getDocument(String xmlpath){
		try {
			SAXReader reader = new SAXReader();
			Document dom = reader.read(xmlpath);
			return dom;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
