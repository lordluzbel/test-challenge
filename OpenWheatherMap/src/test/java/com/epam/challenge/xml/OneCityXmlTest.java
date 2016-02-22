package com.epam.challenge.xml;


import org.jdom2.Document;
import org.junit.Assert;
import org.junit.Test;
import com.epam.challenge.IOneCityTest;

public class OneCityXmlTest extends XmlTest implements IOneCityTest{
	@Test
	public void testByCityName() throws Exception{
		Document responseObject = getResponseObject("/weather", new String[][]{
				{"q","London,uk"}
				,{"mode", "xml"}
		});
		Assert.assertNotNull(responseObject);

		assertCommonElements(responseObject);
	}

	@Test
	public void testByCityId() throws Exception {
		Document responseObject = getResponseObject("/weather", new String[][]{
				{"id","2172797"}
				,{"mode", "xml"}
		});
		Assert.assertNotNull(responseObject);

		assertCommonElements(responseObject);
	}

	@Test
	public void testByGeoCoordinates() throws Exception {
		Document responseObject = getResponseObject("/weather", new String[][]{
				{"lat","35"}
				,{"lon","139"}
				,{"mode", "xml"}
		});
		Assert.assertNotNull(responseObject);

		assertCommonElements(responseObject);
	}

	@Test
	public void testByZipCode() throws Exception {
		Document responseObject = getResponseObject("/weather", new String[][]{
				{"zip","94040,us"}
				,{"mode", "xml"}
		});
		Assert.assertNotNull(responseObject);

		assertCommonElements(responseObject);
	}
}
