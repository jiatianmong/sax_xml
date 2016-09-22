package com.example.myutil;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/3.
 */
public class SaxHandler extends DefaultHandler {

	private Map<String, String> mapSingle = null;// 存储单个解析的完整对象
	private List<Map<String, String>> listAll = null;// 存储所有的解析对象
	private String currentTag = null;// 正在解析的元素的标签
	private String currentValue = null;// 解析当前元素的值
	private String nodeName = null;// 解析当前的节点名称

	public SaxHandler(String nodeName) {
		this.nodeName = nodeName;

	}

	// 文档开头
	@Override
	public void startDocument() throws SAXException {
		// 当读到文档开头的时候，会触发这个方法//接收文档开始的通知
		listAll = new ArrayList<Map<String, String>>();// 初始化list,用于装载整个文档
	}

	@Override
	public void endDocument() throws SAXException {
		// 读到文档末尾的时候调用
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (qName.equals(nodeName)) {
			mapSingle = new HashMap<String, String>();
		}
		if (attributes != null && mapSingle != null) {
			for (int i = 0; i < attributes.getLength(); i++) {
				mapSingle.put(attributes.getQName(i), attributes.getValue(i));
			}
		}
		currentTag = qName;// 设置好当前的节点
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// 这个方法是用来处理xml文件所读取到的内容 //接收元素中字符数据的通知。
		if (currentTag != null && mapSingle != null) {
			currentValue = new String(ch, start, length);
			if (currentValue != null && !currentValue.trim().equals("")
					&& !currentValue.equals("\n")) {
				mapSingle.put(currentTag, currentValue);
			}
		}
		currentValue = null;// 把当前节点的值和标签设置为空
		currentTag = null;
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equals(nodeName)) {
			listAll.add(mapSingle);
			mapSingle = null;
		}
	}

	public List<Map<String, String>> getList() {
		return listAll;
	}

}
