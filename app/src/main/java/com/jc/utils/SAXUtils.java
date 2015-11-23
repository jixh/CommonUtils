package com.jc.utils;

import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXUtils extends DefaultHandler{

    private SAXParser sp = null;

    public SAXUtils(String str){
        try {
            SAXParserFactory sf = SAXParserFactory.newInstance();
            SAXParser sp = sf.newSAXParser();
            sp.parse(new InputSource(str), this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    //获取根节点下某节点的值
    public String getNode(String tagName){
        String node = "";
        try {
            node = ""+sp.getXMLReader().getProperty(tagName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return node;
    }
    
    //获取根节点下某节点nodeName的数量
    public int getNodeLength(String nodeName){
        return  2;
    }
    //获取根节点下某节点nodeName >> 子节点tagName 的值
    public String getNodeByTagName(String nodeName,int index,String tagName){
        String node = "";

        return node;
    }

}
