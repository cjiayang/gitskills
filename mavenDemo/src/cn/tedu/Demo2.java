package cn.tedu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
/**
 * dom4j����Ԫ�ؽڵ�
 */
public class Demo2 {
	/*
	 * 1.��ѯ��һ����������������������̨ 
	 * 2.��ѯ�ڶ�������ۼۣ������������̨ 
	 * 3.����һ�������һ���ؼ۽ڵ㣨2�ַ�ʽ��
	 * 4.���ڶ����������߽ڵ�ǰ����һ���ؼ۽ڵ� 
	 * 5.ɾ���ڶ�������ؼ۽ڵ㣨2�ַ�ʽ�� 
	 * 6.���µ�һ������ؼ۽ڵ������Ϊ19.8Ԫ
	 */
	public static void main(String[] args) throws Exception {
		// 2.��ѯ�ڶ�������ۼۣ������������̨
		// findPrice();

		// 3.����һ�������һ���ؼ۽ڵ㣨2�ַ�ʽ��
		// addPrice2();
		
		// 4.���ڶ����������߽ڵ�ǰ����һ���ؼ۽ڵ� 
		// insertPrice2();
		
		//5.ɾ���ڶ�������ؼ۽ڵ㣨2�ַ�ʽ��
		//deletePrice2();
		
		
		//6.���µ�һ������ؼ۽ڵ������Ϊ19.8Ԫ
		Document dom = XMLUtils.getDocument("book.xml");
		
		dom.getRootElement().element("��").element("�ؼ�").setText("19.8Ԫ");
		
		//����xml�ļ�
		XMLUtils.write2Xml(dom, "book.xml");
		
		System.out.println("ִ�����!");
	}

	//5.ɾ���ڶ�������ؼ۽ڵ㣨2�ַ�ʽ��
	private static void deletePrice2() {
		Document dom = XMLUtils.getDocument("book.xml");
		Element root = dom.getRootElement();
		
		//��һ�ַ�ʽ
		//��ȡ�ڶ�����
		//List<Element> list = root.elements();
		//Element bookEle2 = list.get(1);
		
		//��ȡҪɾ�����ؼ۽ڵ�
		//Element priceEle2 = bookEle2.element("�ؼ�");
		
		//������ӹ�ϵ
		//bookEle2.remove(priceEle2);
		
		
		//�ڶ��ַ�ʽ
		//��ȡ�ڶ�����
		List<Element> list = root.elements();
		Element bookEle2 = list.get(1);
		List list2 = bookEle2.elements();
		list2.remove(1);
		
		
		//����xml�ļ�
		XMLUtils.write2Xml(dom, "book.xml");
	}
	
	
	// 4.���ڶ����������߽ڵ�ǰ����һ���ؼ۽ڵ� 
	private static void insertPrice2() {
		Document dom = XMLUtils.getDocument("book.xml");
		Element root = dom.getRootElement();
		
		//��ȡ�ڶ�����
		List<Element> list = root.elements();
		Element bookEle2 = list.get(1);
		
		//����һ���ص�ڵ�
		Element priceEle2 = DocumentHelper.createElement("�ؼ�");
		priceEle2.setText("6.6Ԫ");  
		
		//���ؼ۽ڵ���뵽ָ��λ��
		List list2 = bookEle2.elements();
		list2.add(1, priceEle2);
		
		//����xml�ļ�
		XMLUtils.write2Xml(dom, "book.xml");
	}

	// 3.����һ�������һ���ؼ۽ڵ㣨2�ַ�ʽ��
	private static void addPrice2() throws UnsupportedEncodingException,
			FileNotFoundException, IOException {
		// ����xml�ļ�, ����document����, ����ȡ���ڵ�
		Document dom = XMLUtils.getDocument("book.xml");
		Element root = dom.getRootElement();

		// ��һ�ַ�ʽ
		// ��ȡ��һ����
		// Element bookEle = root.element("��");

		// ����һ��������ؼ۽ڵ�
		// Element priceEle2 = DocumentHelper.createElement("�ؼ�");
		// priceEle2.setText("9.9Ԫ");

		// ���ؼ۽ڵ���ص���һ������
		// bookEle.add(priceEle2);

		// �ڶ��ַ�ʽ
		// ��ȡ��һ����
		Element bookEle = root.element("��");
		Element priceEle2 = bookEle.addElement("�ؼ�");
		priceEle2.setText("8.8Ԫ");

		// �����º��document���� ��д���ļ���
		// XMLWriter writer = new XMLWriter(new FileWriter(new
		// File("book.xml")));
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(
				new File("book.xml")), "utf-8");

		// ������ʽ�����������
		OutputFormat format = OutputFormat.createPrettyPrint();
		// OutputFormat format = OutputFormat.createCompactFormat();

		XMLWriter writer = new XMLWriter(out, format);
		writer.write(dom);
		writer.close();
	}

	// 2.��ѯ�ڶ�������ۼۣ������������̨
	private static void findPrice() throws DocumentException {
		// ����������
		SAXReader reader = new SAXReader();

		// ����xml�ļ�, ����document����
		Document dom = reader.read("book.xml");

		// ��ȡ���ڵ�
		Element root = dom.getRootElement();

		// ��ȡrootԪ����������Ԫ����ɵ�list����
		List<Element> list = root.elements();

		// ��ȡlist�����еĵڶ���Ԫ��(�ڶ�����)
		Element bookEle2 = list.get(1);

		// ��ȡ�ڶ������µ��ۼ۽ڵ�
		Element priceEle = bookEle2.element("�ۼ�");

		// ��ȡ�ۼ۽ڵ������
		System.out.println("text:" + priceEle.getText());
		System.out.println("textTrim:" + priceEle.getTextTrim());
	}
}
