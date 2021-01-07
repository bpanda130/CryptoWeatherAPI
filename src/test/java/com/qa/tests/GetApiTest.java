package com.qa.tests;

import java.net.URISyntaxException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.Util.TestUtil;
import com.qa.base.TestBase;
import com.qa.client.RestClient;


/**
 * GetApiTest test class contains all Get Test and the required Assertions.
 * @author Bishnu
 *
 */
public class GetApiTest extends TestBase{
	TestBase testBase;
	String serviceURL;
	String apiURL;
	String URI;
	RestClient rs;
	CloseableHttpResponse closableHttpResponse;
	URIBuilder builder;
	String humadityPlace, dataType, lang;
	
	@BeforeMethod
	public void setup() throws URISyntaxException{
		testBase = new TestBase();
		//Getting the required properties from config file.
		serviceURL = prop.getProperty("URL");
		apiURL = prop.getProperty("serviceURL");
		humadityPlace = prop.getProperty("humadityPlace");
		dataType = prop.getProperty("dataType");
		lang = prop.getProperty("lang");
		//Building URI based on config value.
		URI = serviceURL + apiURL;
		builder = new URIBuilder(URI);
		//Setting the Query Parameter
		builder.setParameter("dataType", dataType).setParameter("lang", lang);
	}

	@Test
	public void getAPITestWithOutHeaders() throws Exception{
		rs = new RestClient();
		closableHttpResponse = rs.getMethod(builder.build());
		
		//Getting the Status Code and Verifying with required value.
		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ------> " + statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Staus is not 200");
		
		//To get the response message from Response body
		String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
		
		//convert the response string into JSON Object
		JSONObject responsejson = new JSONObject(responseString);
		System.out.println("Response JSON from API ------> " + responsejson);
		
		//Getting the place and verifying with expected value.
		String place = TestUtil.getValueByJPath(responsejson, "/humidity/data[0]/place");
		System.out.println(place);
		Assert.assertEquals(place, humadityPlace);
		
		//Verifying the required Nodes are appearing in the Response JSON.
		Assert.assertTrue(responsejson.has("rainfall"),"Required rainfall node is not appearing.");
		Assert.assertTrue(responsejson.has("icon"),"Required icon node is not appearing.");
		Assert.assertTrue(responsejson.has("iconUpdateTime"),"Required iconUpdateTime node is not appearing.");
		Assert.assertTrue(responsejson.has("temperature"),"Required temperature node is not appearing.");
		Assert.assertTrue(responsejson.has("humidity"),"Required humidity node is not appearing.");
		
		//this will get all Header information of the Response.
		Header[] headerArray = closableHttpResponse.getAllHeaders();
		
		//Getting the all the Response Header
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for(Header header: headerArray){
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array ---------> "+ allHeaders);
		//Verifying the required Response Header.
		Assert.assertEquals(allHeaders.get("Content-Type"), "application/json; charset=utf-8");
	}
}
