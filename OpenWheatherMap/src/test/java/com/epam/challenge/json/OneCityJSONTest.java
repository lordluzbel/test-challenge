package com.epam.challenge.json;

import org.codehaus.jettison.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class OneCityJSONTest extends JSONTest {
	@Test
	public void testByCityName() throws Exception{
		JSONObject responseObject = getResponseObject("/weather", new String[][]{
			{"q","London,uk"}
		});
		
		assertValidResponse(responseObject);
	}
	
	@Test
	public void testByCityId() throws Exception{
		JSONObject responseObject = getResponseObject("/weather", new String[][]{
			{"id","2172797"}
		});
		
		assertValidResponse(responseObject);
	}
	
	@Test
	public void testByGeoCoordinates() throws Exception{
		JSONObject responseObject = getResponseObject("/weather", new String[][]{
			{"lat","35"},
			{"lon","139"}
		});
		
		assertValidResponse(responseObject);
	}
	
	@Test
	public void testByZipCode() throws Exception{
		JSONObject responseObject = getResponseObject("/weather", new String[][]{
			{"zip","94040,us"}
		});
		Assert.assertNotNull(responseObject);
		
		assertValidResponse(responseObject);
	}
}
