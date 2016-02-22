package com.epam.challenge.xml;

import java.io.StringReader;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.junit.Assert;

import com.epam.challenge.BaseTest;

public class XmlTest extends BaseTest<Document> {

	@Override
	protected Document getResponseObject(String path, String[][] parameters) {
		String response = sendRequest(path, parameters);
		Document document = null;
		
		try {
			StringReader reader = new StringReader(response);
			SAXBuilder builder = new SAXBuilder();
			document = builder.build(reader);
		} catch (Exception e) {
			document = null;
		} 
		
		return document;
	}

	@Override
	protected void assertValidResponse(Document t) throws Exception {
		Element root = t.getRootElement();
		Assert.assertTrue(root.getName().equals("current"));
				
		Assert.assertNotNull(root.getChild("city"));		
		Assert.assertNotNull(root.getChild("temperature"));		
		Assert.assertNotNull(root.getChild("humidity"));
		Assert.assertNotNull(root.getChild("pressure"));		
		Assert.assertNotNull(root.getChild("wind"));		
		Assert.assertNotNull(root.getChild("clouds"));		
		Assert.assertNotNull(root.getChild("visibility"));		
		Assert.assertNotNull(root.getChild("precipitation"));		
		Assert.assertNotNull(root.getChild("weather"));		
		Assert.assertNotNull(root.getChild("lastupdate"));
	}

	@Override
	protected void assertValidResponseList(Document t) throws Exception {
		// TODO Auto-generated method stub		
	}

	@Override
	protected String[] getAccept() {
		return new String[] {"application/xhtml+xml", "application/xml"};
	}

}
