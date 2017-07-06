package cn.tedu;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * dom4j�������ų���
 */
public class Demo1 {
	public static void main(String[] args) throws Exception {
		//1.����������
		SAXReader reader = new SAXReader();
		
		//2.���ý������ϵķ�������xml�ļ�, 
		Document dom = reader.read("book.xml");
		
		//3.����document�����ȡ���ڵ�
		Element root = dom.getRootElement();
		
		//4.��ȡ��һ����ڵ�
		Element bookEle = root.element("��");
		
		//5.��ȡ��һ�������ڵ�
		Element bookNameEle = bookEle.element("����");
		
		//6.���������ڵ��ȡ�ڲ����ı�
		String text = bookNameEle.getText();
		System.out.println("��һ������:"+text);
		
	}
}
