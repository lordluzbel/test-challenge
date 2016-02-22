package com.epam.challenge.json;

import org.codehaus.jettison.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import com.epam.challenge.IOneCityTest;

public class OneCityJSONTest extends JSONTest implements IOneCityTest {
	@Test
	public void testByCityName() throws Exception{
		JSONObject responseObject = getResponseObject("/weather", new String[][]{
				{"q","London,uk"}
		});

		assertCommonElements(responseObject);
		Assert.assertEquals(responseObject.get("name"), "London");
	}

	@Test
	public void testByCityId() throws Exception{
		JSONObject responseObject = getResponseObject("/weather", new String[][]{
				{"id","2172797"}
		});

		assertCommonElements(responseObject);
		Assert.assertEquals(2172797, responseObject.getInt("id"));
	}

	@Test
	public void testByGeoCoordinates() throws Exception{
		JSONObject responseObject = getResponseObject("/weather", new String[][]{
				{"lat","35"}
				,{"lon","139"}
		});

		assertCommonElements(responseObject);
		
		JSONObject coordinates = responseObject.getJSONObject("coord");
		Assert.assertEquals(35, Math.round(coordinates.getDouble("lat")));
		Assert.assertEquals(139, Math.round(coordinates.getDouble("lon")));
	}

	@Test
	public void testByZipCode() throws Exception{
		JSONObject responseObject = getResponseObject("/weather", new String[][]{
				{"zip","94040,us"}
		});
		Assert.assertNotNull(responseObject);

		assertCommonElements(responseObject);
		Assert.assertEquals("Mountain View", responseObject.get("name"));
	}
}
