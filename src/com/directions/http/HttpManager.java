package com.directions.http;

import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public final class HttpManager {
	
	private URIBuilder uriBuilder;
	private	CloseableHttpClient httpClient;
	private final String MAPS_URI = "http://maps.googleapis.com/maps/api/directions/json?origin=&destination=&sensor=false";
	private String completeURI;
	
	public HttpManager() throws URISyntaxException{
		this.uriBuilder = new URIBuilder(this.MAPS_URI);
		this.completeURI = null;
		this.httpClient = HttpClients.createDefault();
	}
	
	public void setAddresses(String [] addresses) throws URISyntaxException{
		//Assuming first element represents start address
		this.uriBuilder = this.uriBuilder.setParameter("origin", addresses[0]).setParameter("destination", addresses[1]);
		this.setCompleteURI();
	}
	
	private void setCompleteURI() throws URISyntaxException{
		this.completeURI = this.uriBuilder.build().toString();
	}
	
	public String getCompleteURI() throws URISyntaxException{
		if(this.completeURI == null){
			return "The URI is incomplete, enter a pair of addresses first";
		}
		return this.completeURI;
	}
	
	public HttpResponse fetchDirections() throws Exception{
		if(this.completeURI == null){
			throw new Exception("Cannot fetch directions. Please enter a pair of addresses first");
		}
		
		final HttpGet httpGet = new HttpGet(this.completeURI);
		final HttpResponse httpResponse = this.httpClient.execute(httpGet);
		
		return httpResponse;
	}
}
