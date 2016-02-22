package com.epam.challenge;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

import com.sun.istack.logging.Logger;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public abstract class BaseTest<T> extends JerseyTest{
	private static final String BASE_URL_PROP = "base.url";
	private static final String APPID_PROP = "app.id";
	private static final String CONFIG_PROPERTIES = "/config.properties";

	private static WebResource webResource;
	private static Properties config;
	private static String appId;
	private static String baseUrl;

	//Each specific test for each response format must implement the below two methods
	protected abstract T getResponseObject(String path, String[][] parameters);
	protected abstract void assertValidResponse(T t) throws Exception;
	protected abstract void assertValidResponseList(T t) throws Exception;
	protected abstract String[] getAccept();

	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}

	@BeforeClass
	public static void classSetup() throws IOException{
		//Loading configuration properties
		InputStream inputStream = BaseTest.class.getResourceAsStream(CONFIG_PROPERTIES);	
		Assert.assertNotNull(inputStream);

		config = new Properties();
		config.load(inputStream);

		baseUrl = config.getProperty(BASE_URL_PROP);
		Assert.assertFalse(null == baseUrl || baseUrl.isEmpty());

		appId = config.getProperty(APPID_PROP);
		Assert.assertFalse(null == appId || appId.isEmpty());
	}

	@Before
	public void setup() throws IOException{
		//Initialize rest service
		if (null == webResource){
			webResource = client().resource(baseUrl);			
		}
	}

	/**
	 * Set a request to the rest service and return the response.
	 * @param path
	 * @param params
	 * @return
	 */
	protected synchronized String sendRequest(String path, String[] ... params){
		String response = null;
		try{
			webResource.accept(getAccept());
			response = webResource					
					.path(path)					
					.queryParams(convertToParameterMap(params))
					.get(String.class);
		}catch(RuntimeException e){
			Logger.getLogger(BaseTest.class).log(Level.SEVERE, 
					e.getCause() != null? String.format("%s caused by %s", e.getMessage(), e.getCause().getMessage()): e.getMessage());
		}

		Assert.assertTrue(null!= response && !response.isEmpty());
		return response;
	}
	
	/**
	 * Convert a bidimentional array in a multivaluedmap to send as query parameters to rest sercive
	 * @param params
	 * @return
	 */
	private MultivaluedMapImpl convertToParameterMap(String[]... params) {
		MultivaluedMapImpl multivaluedMap = new MultivaluedMapImpl();
		multivaluedMap.add("appid", appId);

		for (String[] param : params){
			multivaluedMap.add(param[0], param[1]);
		}
		return multivaluedMap;
	}
}
