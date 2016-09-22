package com.example.myutil;


import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.File;

import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * 使用该类创建解析工厂
 * Created by Administrator on 2016/6/3.
 */
public class SaxService  {

    public static List<Map<String,String>> saxReadXML(BufferedReader reader, String startNodeName){

        try {
        	
            // 创建一个解析xml的工厂对象  //见Java API
            SAXParserFactory spf = SAXParserFactory.newInstance();  //获取 SAXParserFactory 的一个新实例。创建一个新的工厂
            // 解析xml//获取此类的实例之后，
            //将可以从各种输入源解析 XML。这些输入源为 InputStream、File、URL 和 SAX InputSource。
            SAXParser parser = spf.newSAXParser();
 
            SaxHandler handler = new SaxHandler(startNodeName);

            InputSource is = new InputSource(reader);
            is.setEncoding("utf-8");
            //使用指定的 DefaultHandler 将给定 InputSource 的内容解析为 XML。
            parser.parse(is,handler);

            reader.close();
            return handler.getList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Map<String,String>> saxReadXML(File file, String startNodeName){
        try {
            // 创建一个解析xml的工厂对象  //见Java API
            SAXParserFactory spf = SAXParserFactory.newInstance();  //获取 SAXParserFactory 的一个新实例。创建一个新的工厂
            // 解析xml//获取此类的实例之后，
            //将可以从各种输入源解析 XML。这些输入源为 InputStream、File、URL 和 SAX InputSource。
            SAXParser parser = spf.newSAXParser();

            SaxHandler handler = new SaxHandler(startNodeName);

            //使用指定的 DefaultHandler 将给定 InputSource 的内容解析为 XML。
            parser.parse(file,handler);
            return handler.getList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}

