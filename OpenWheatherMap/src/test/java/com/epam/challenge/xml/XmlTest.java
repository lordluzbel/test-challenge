package com.epam.challenge.xml;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.epam.challenge.BaseTest;

public class XmlTest extends BaseTest<Document> {

	@Override
	protected Document getResponseObject(String path, String[][] parameters) {
		String response = sendRequest(path, parameters);
		Document document = null;
		
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
			StringReader reader = new StringReader(response);		
			
			document = builder.parse(new InputSource(reader));
		} catch (Exception e) {
			document = null;
		} 
		
		return document;
	}

	@Override
	protected void assertValidResponse(Document t) throws Exception {
		Node node = t.getFirstChild();
		Assert.assertTrue(node.getNodeName().equals("current"));		
		node = node.getFirstChild();
		Assert.assertTrue(node.getNodeName().equals("city"));		
		node = node.getNextSibling();
		Assert.assertTrue(node.getNodeName().equals("temperature"));
		node = node.getNextSibling();
		Assert.assertTrue(node.getNodeName().equals("humidity"));
		node = node.getNextSibling();
		Assert.assertTrue(node.getNodeName().equals("pressure"));
		node = node.getNextSibling();
		Assert.assertTrue(node.getNodeName().equals("wind"));
		node = node.getNextSibling();
		Assert.assertTrue(node.getNodeName().equals("clouds"));
		node = node.getNextSibling();
		Assert.assertTrue(node.getNodeName().equals("visibility"));
		node = node.getNextSibling();
		Assert.assertTrue(node.getNodeName().equals("precipitation"));
		node = node.getNextSibling();
		Assert.assertTrue(node.getNodeName().equals("weather"));
		node = node.getNextSibling();
		Assert.assertTrue(node.getNodeName().equals("lastupdate"));
	}

	@Override
	protected void assertValidResponseList(Document t) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
