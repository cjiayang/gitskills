package cn.tedu;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * dom4j快速入门程序
 */
public class Demo1 {
	public static void main(String[] args) throws Exception {
		//1.创建解析器
		SAXReader reader = new SAXReader();
		
		//2.调用解析器上的方法解析xml文件, 
		Document dom = reader.read("book.xml");
		
		//3.根据document对象获取根节点
		Element root = dom.getRootElement();
		
		//4.获取第一本书节点
		Element bookEle = root.element("书");
		
		//5.获取第一个书名节点
		Element bookNameEle = bookEle.element("书名");
		
		//6.根据书名节点获取内部的文本
		String text = bookNameEle.getText();
		System.out.println("第一个书名:"+text);
		
	}
}
