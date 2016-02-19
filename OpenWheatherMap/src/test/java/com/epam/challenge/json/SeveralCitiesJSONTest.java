package com.epam.challenge.json;

import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

public class SeveralCitiesJSONTest extends JSONTest {
	@Test
	public void byRectZoneTest() throws Exception {
		JSONObject responseObject = getResponseObject("/box/city", new String[][]{
			{"bbox", "12,32,15,37,10"},
			{"cluster","yes"}
		});
		
		assertValidResponseList(responseObject);
	} 
	
	@Test
	public void byCityIdsTest() throws Exception {
		JSONObject responseObject = getResponseObject("/group", new String[][]{
			{"id","524901,703448,2643743"},
			{"units","metric"}
		});
		
		assertValidResponseList(responseObject);
	} 
	
	@Test
	public void byCitiesCycleTest() throws Exception {
		JSONObject responseObject = getResponseObject("/find", new String[][]{
			{"lat","55.5"},
			{"lon","37.5"},
			{"cnt","10"},
		});
		
		assertValidResponseList(responseObject);
	}
}
