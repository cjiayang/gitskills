package cn.tedu;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * dom4j操作属性节点
 */
public class Demo3 {
	/*
	1.给第一本书添加一个属性，如：出版社="清华大学出版社"(2种方式)
	2.在控制台上打印输出第一本书的出版社属性的值,并更新属性的值为“人民出版社”(3种方式)
	3.删除第一本书的出版社属性(2种方式)
	 */
	public static void main(String[] args) {
		//addAttr();
		
		//2.在控制台上打印输出第一本书的出版社属性的值,并更新属性的值为“人民出版社”(3种方式)
		//findAndUpdateAttr();
		
		//3.删除第一本书的出版社属性(2种方式)
		Document dom = XMLUtils.getDocument("book.xml");
		Element root = dom.getRootElement();
		//获取第一本书
		Element bookEle = root.element("书");
		
		//第一种方式
		//获取要被删除的出版社属性
		//Attribute attr = bookEle.attribute("出版社");
		
		//解除父子关系
		//bookEle.remove(attr);
		
		//第二种方式  
		//如果给一个正常的值, 该方法是添加属性
		//如果给一个null值, 将会删除该属性
		bookEle.addAttribute("版次", null);
		
		//更新dom到xml文件
		XMLUtils.write2Xml(dom, "book.xml");
		
		
		System.out.println("执行完成!");
	}
	
	
	//2.在控制台上打印输出第一本书的出版社属性的值,并更新属性的值为“人民出版社”(3种方式)
	private static void findAndUpdateAttr() {
		Document dom = XMLUtils.getDocument("book.xml");
		Element root = dom.getRootElement();
		//第一种方式
		Attribute attr = root.element("书").attribute(0);
		
		//第二种方式
		//Attribute attr = root.element("书").attribute("版次");
		
		System.out.println(attr.getName()+":"+attr.getValue());
		
		//第三种方式
		//String value = root.element("书").attributeValue("出版社");
		//System.out.println(value);
		
		attr.setValue("人民出版社");
		
		//更新dom到xml文件
		XMLUtils.write2Xml(dom, "book.xml");
	}

	private static void addAttr() {
		//1.给第一本书添加一个属性，如：出版社="清华大学出版社"(2种方式)
		Document dom = XMLUtils.getDocument("book.xml");
		Element root = dom.getRootElement();
		
		//第一种方式
		//获取第一本书
		//Element bookEle = root.element("书");
		
		//创建一个属性节点
		//Attribute attr = DocumentHelper.createAttribute(bookEle, "出版社", "清华大学出版社");
		
		//将属性节点挂载到第一本书上
		//bookEle.add(attr);
		
		//第二种方式
		//获取第一本书
		Element bookEle = root.element("书");
		bookEle.addAttribute("版次", "1.0");
		
		//更新dom到xml文件
		XMLUtils.write2Xml(dom, "book.xml");
	}
}
