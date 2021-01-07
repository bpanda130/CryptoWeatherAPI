package com.qa.client;

import java.net.URI;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;


/**
 * RestClient contains all overloaded RestMethod like Get/Post with Header and without Header information. 
 * @author Bishnu
 *
 */
public class RestClient {

	// 1. Get Method without Header
	public CloseableHttpResponse getMethod(URI uri) throws Exception {
		// creating a client connection with handling invalid SSL certificate
		HttpClient httpClient = HttpClients
	            .custom()
	            .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
	            .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
	            .build();
				
		// create a get connection with the required url
		HttpGet httpGet = new HttpGet(uri);
		//Executing the Get Request and type casting the HttpResponse into CloseableHttpResponse.
		CloseableHttpResponse closableHttpResponse = (CloseableHttpResponse) httpClient.execute(httpGet);
		return closableHttpResponse;
	}
}
