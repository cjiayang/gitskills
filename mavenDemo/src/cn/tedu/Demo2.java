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
 * dom4j操作元素节点
 */
public class Demo2 {
	/*
	 * 1.查询第一本书的书名，并输出到控制台 
	 * 2.查询第二本书的售价，并输出到控制台 
	 * 3.给第一本书添加一个特价节点（2种方式）
	 * 4.给第二本书在作者节点前插入一个特价节点 
	 * 5.删除第二本书的特价节点（2种方式） 
	 * 6.更新第一本书的特价节点的内容为19.8元
	 */
	public static void main(String[] args) throws Exception {
		// 2.查询第二本书的售价，并输出到控制台
		// findPrice();

		// 3.给第一本书添加一个特价节点（2种方式）
		// addPrice2();
		
		// 4.给第二本书在作者节点前插入一个特价节点 
		// insertPrice2();
		
		//5.删除第二本书的特价节点（2种方式）
		//deletePrice2();
		
		
		//6.更新第一本书的特价节点的内容为19.8元
		Document dom = XMLUtils.getDocument("book.xml");
		
		dom.getRootElement().element("书").element("特价").setText("19.8元");
		
		//更新xml文件
		XMLUtils.write2Xml(dom, "book.xml");
		
		System.out.println("执行完成!");
	}

	//5.删除第二本书的特价节点（2种方式）
	private static void deletePrice2() {
		Document dom = XMLUtils.getDocument("book.xml");
		Element root = dom.getRootElement();
		
		//第一种方式
		//获取第二本书
		//List<Element> list = root.elements();
		//Element bookEle2 = list.get(1);
		
		//获取要删除的特价节点
		//Element priceEle2 = bookEle2.element("特价");
		
		//解除父子关系
		//bookEle2.remove(priceEle2);
		
		
		//第二种方式
		//获取第二本书
		List<Element> list = root.elements();
		Element bookEle2 = list.get(1);
		List list2 = bookEle2.elements();
		list2.remove(1);
		
		
		//更新xml文件
		XMLUtils.write2Xml(dom, "book.xml");
	}
	
	
	// 4.给第二本书在作者节点前插入一个特价节点 
	private static void insertPrice2() {
		Document dom = XMLUtils.getDocument("book.xml");
		Element root = dom.getRootElement();
		
		//获取第二本书
		List<Element> list = root.elements();
		Element bookEle2 = list.get(1);
		
		//创建一个特点节点
		Element priceEle2 = DocumentHelper.createElement("特价");
		priceEle2.setText("6.6元");  
		
		//将特价节点插入到指定位置
		List list2 = bookEle2.elements();
		list2.add(1, priceEle2);
		
		//更新xml文件
		XMLUtils.write2Xml(dom, "book.xml");
	}

	// 3.给第一本书添加一个特价节点（2种方式）
	private static void addPrice2() throws UnsupportedEncodingException,
			FileNotFoundException, IOException {
		// 解析xml文件, 返回document对象, 并获取根节点
		Document dom = XMLUtils.getDocument("book.xml");
		Element root = dom.getRootElement();

		// 第一种方式
		// 获取第一本书
		// Element bookEle = root.element("书");

		// 创建一个游离的特价节点
		// Element priceEle2 = DocumentHelper.createElement("特价");
		// priceEle2.setText("9.9元");

		// 将特价节点挂载到第一本书上
		// bookEle.add(priceEle2);

		// 第二种方式
		// 获取第一本书
		Element bookEle = root.element("书");
		Element priceEle2 = bookEle.addElement("特价");
		priceEle2.setText("8.8元");

		// 将更新后的document对象 再写回文件中
		// XMLWriter writer = new XMLWriter(new FileWriter(new
		// File("book.xml")));
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(
				new File("book.xml")), "utf-8");

		// 创建格式化输出器对象
		OutputFormat format = OutputFormat.createPrettyPrint();
		// OutputFormat format = OutputFormat.createCompactFormat();

		XMLWriter writer = new XMLWriter(out, format);
		writer.write(dom);
		writer.close();
	}

	// 2.查询第二本书的售价，并输出到控制台
	private static void findPrice() throws DocumentException {
		// 创建解析器
		SAXReader reader = new SAXReader();

		// 解析xml文件, 返回document对象
		Document dom = reader.read("book.xml");

		// 获取根节点
		Element root = dom.getRootElement();

		// 获取root元素下所有子元素组成的list集合
		List<Element> list = root.elements();

		// 获取list集合中的第二个元素(第二本书)
		Element bookEle2 = list.get(1);

		// 获取第二本书下的售价节点
		Element priceEle = bookEle2.element("售价");

		// 获取售价节点的内容
		System.out.println("text:" + priceEle.getText());
		System.out.println("textTrim:" + priceEle.getTextTrim());
	}
}
