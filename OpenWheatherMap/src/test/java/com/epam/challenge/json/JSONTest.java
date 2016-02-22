package com.epam.challenge.json;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Assert;

import com.epam.challenge.BaseTest;

public class JSONTest extends BaseTest<JSONObject> {	
	@Override
	protected JSONObject getResponseObject(String path, String[][] parameters) {		
		String response = sendRequest(path, parameters);
		
		JSONObject json;
		try {
			json = new JSONObject(response);
		} catch (JSONException e) {
			json = null;
		}		
		
		return json;
	}

	@Override
	protected void assertValidResponse(JSONObject t) throws Exception{
		Assert.assertNotNull(t);
		
		Assert.assertNotNull(t.get("id"));
		Assert.assertNotNull(t.get("cod"));
		Assert.assertNotNull(t.get("coord"));
		Assert.assertNotNull(t.get("name"));	
		Assert.assertNotNull(t.get("dt"));
		Assert.assertNotNull(t.get("clouds"));		
		Assert.assertNotNull(t.get("wind"));
		Assert.assertNotNull(t.get("main"));
		Assert.assertNotNull(t.get("weather"));
		Assert.assertNotNull(t.get("sys"));		
	}

	@Override
	protected void assertValidResponseList(JSONObject t) throws Exception {
		JSONArray array = t.getJSONArray("list");
		Assert.assertNotNull(array);
		Assert.assertNotSame(array.length(), 0);
		
		int i = 0;
		do {
			JSONObject jsonObject = array.getJSONObject(i);
			
			Assert.assertNotNull(jsonObject.get("id"));			
			Assert.assertNotNull(jsonObject.get("coord"));
			Assert.assertNotNull(jsonObject.get("name"));	
			Assert.assertNotNull(jsonObject.get("dt"));
			Assert.assertNotNull(jsonObject.get("clouds"));		
			Assert.assertNotNull(jsonObject.get("wind"));
			Assert.assertNotNull(jsonObject.get("main"));
			Assert.assertNotNull(jsonObject.get("weather"));
			i ++;
		} while(i < array.length());
	}

	@Override
	protected String[] getAccept() {
		return new String[] {"application/json"};
	}

	

	
}
