package com.jktaihe.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
/**
 * Created by jktaihe on 2016/7/24.
 * email:jktaihe@gmail.com
 * blog:jktaihe.top
 * https://github.com/jixh
 */
public class DomUtils {

    public static final String CHARSET_NAME = "UTF-8";
    public Element element = null;

    public DomUtils(String str){
        try {
            InputStream inStream = new ByteArrayInputStream(str.getBytes(CHARSET_NAME));
            //DOM文件创建工厂
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //DOM创建对象
            DocumentBuilder builder = factory.newDocumentBuilder();
            //获取XML的DOM
            Document document = builder.parse(inStream);
            //获取根结点对象
            element = document.getDocumentElement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    //获取根节点下某节点的值
    public String getNode(String tagName){
        String node = "";
        Element childElement = null;
        try {
            NodeList resultNodes = element.getElementsByTagName(tagName);
            childElement = (Element)resultNodes.item(0);
            node = childElement.getTextContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return node;
    }
    
    //获取根节点下某节点nodeName的数量
    public int getNodeLength(String nodeName){
        NodeList resultNodes = element.getElementsByTagName(nodeName);
        return resultNodes.getLength();
    }
    
    //获取根节点下某节点nodeName >> 子节点tagName 的值
    public String getNodeByTagName(String nodeName,int index,String tagName){
        String node = "";
        //根节点的子节点list
        NodeList resultNodes = element.getElementsByTagName(nodeName);
        if(index<resultNodes.getLength()){
        	Element childElement = (Element) resultNodes.item(index);
        	//获取当前节点的子节点
        	NodeList childNodes = childElement.getChildNodes();
        	for(int j=0;j<childNodes.getLength();j++){
                if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE){
                    if(tagName.equals(childNodes.item(j).getNodeName())){
                        node = childNodes.item(j).getFirstChild()==null?"":childNodes.item(j).getFirstChild().getNodeValue();
                    }
                }
            }//end for j
        }
        return node;
    }

}
