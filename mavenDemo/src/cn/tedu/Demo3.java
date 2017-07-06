package cn.tedu;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * dom4j�������Խڵ�
 */
public class Demo3 {
	/*
	1.����һ�������һ�����ԣ��磺������="�廪��ѧ������"(2�ַ�ʽ)
	2.�ڿ���̨�ϴ�ӡ�����һ����ĳ��������Ե�ֵ,���������Ե�ֵΪ����������硱(3�ַ�ʽ)
	3.ɾ����һ����ĳ���������(2�ַ�ʽ)
	 */
	public static void main(String[] args) {
		//addAttr();
		
		//2.�ڿ���̨�ϴ�ӡ�����һ����ĳ��������Ե�ֵ,���������Ե�ֵΪ����������硱(3�ַ�ʽ)
		//findAndUpdateAttr();
		
		//3.ɾ����һ����ĳ���������(2�ַ�ʽ)
		Document dom = XMLUtils.getDocument("book.xml");
		Element root = dom.getRootElement();
		//��ȡ��һ����
		Element bookEle = root.element("��");
		
		//��һ�ַ�ʽ
		//��ȡҪ��ɾ���ĳ���������
		//Attribute attr = bookEle.attribute("������");
		
		//������ӹ�ϵ
		//bookEle.remove(attr);
		
		//�ڶ��ַ�ʽ  
		//�����һ��������ֵ, �÷������������
		//�����һ��nullֵ, ����ɾ��������
		bookEle.addAttribute("���", null);
		
		//����dom��xml�ļ�
		XMLUtils.write2Xml(dom, "book.xml");
		
		
		System.out.println("ִ�����!");
	}
	
	
	//2.�ڿ���̨�ϴ�ӡ�����һ����ĳ��������Ե�ֵ,���������Ե�ֵΪ����������硱(3�ַ�ʽ)
	private static void findAndUpdateAttr() {
		Document dom = XMLUtils.getDocument("book.xml");
		Element root = dom.getRootElement();
		//��һ�ַ�ʽ
		Attribute attr = root.element("��").attribute(0);
		
		//�ڶ��ַ�ʽ
		//Attribute attr = root.element("��").attribute("���");
		
		System.out.println(attr.getName()+":"+attr.getValue());
		
		//�����ַ�ʽ
		//String value = root.element("��").attributeValue("������");
		//System.out.println(value);
		
		attr.setValue("���������");
		
		//����dom��xml�ļ�
		XMLUtils.write2Xml(dom, "book.xml");
	}

	private static void addAttr() {
		//1.����һ�������һ�����ԣ��磺������="�廪��ѧ������"(2�ַ�ʽ)
		Document dom = XMLUtils.getDocument("book.xml");
		Element root = dom.getRootElement();
		
		//��һ�ַ�ʽ
		//��ȡ��һ����
		//Element bookEle = root.element("��");
		
		//����һ�����Խڵ�
		//Attribute attr = DocumentHelper.createAttribute(bookEle, "������", "�廪��ѧ������");
		
		//�����Խڵ���ص���һ������
		//bookEle.add(attr);
		
		//�ڶ��ַ�ʽ
		//��ȡ��һ����
		Element bookEle = root.element("��");
		bookEle.addAttribute("���", "1.0");
		
		//����dom��xml�ļ�
		XMLUtils.write2Xml(dom, "book.xml");
	}
}
