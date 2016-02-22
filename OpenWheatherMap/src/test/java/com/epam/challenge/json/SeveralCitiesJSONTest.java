package com.epam.challenge.json;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import com.epam.challenge.ISeveralCitiesTest;

public class SeveralCitiesJSONTest extends JSONTest implements ISeveralCitiesTest {
	@Test
	public void byRectZoneTest() throws Exception {
		JSONObject responseObject = getResponseObject("/box/city", new String[][]{
			{"bbox", "12,32,15,37,10"},
			{"cluster","yes"}
		});
		
		assertCommonElementByCity(responseObject);
		
		int counter = responseObject.getInt("cnt");
		JSONArray array = responseObject.getJSONArray("list");
		
		Assert.assertTrue(counter > 0);
		Assert.assertEquals(counter, array.length());
	} 
	
	@Test
	public void byCityIdsTest() throws Exception {
		JSONObject responseObject = getResponseObject("/group", new String[][]{
			{"id","524901,703448,2643743"},
			{"units","metric"}
		});
		
		assertCommonElementByCity(responseObject);
		
		int counter = responseObject.getInt("cnt");
		JSONArray array = responseObject.getJSONArray("list");
		
		Assert.assertTrue(counter > 0);
		Assert.assertEquals(counter, array.length());
	} 
	
	@Test
	public void byCitiesCycleTest() throws Exception {
		JSONObject responseObject = getResponseObject("/find", new String[][]{
			{"lat","55.5"},
			{"lon","37.5"},
			{"cnt","10"},
		});
		
		assertCommonElementByCity(responseObject);
		
		int counter = responseObject.getInt("count");
		JSONArray array = responseObject.getJSONArray("list");
		
		Assert.assertTrue(counter > 0);
		Assert.assertEquals(counter, array.length());
	}
}
