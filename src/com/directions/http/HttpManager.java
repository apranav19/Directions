package com.directions.http;

import java.io.InputStream;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.directions.models.Route;

/**
 * This class represents the HttpManager. The primary role of thsi class is to
 * handle communication with the Google Maps Directions web services.
 * @author Pranav Angara
 *
 */
public final class HttpManager {
	
	private URIBuilder uriBuilder;
	private CloseableHttpClient httpClient;
	private final String MAPS_URI = "http://maps.googleapis.com/maps/api/directions/json?origin=&destination=&sensor=false";
	private String completeURI;
	private JsonManager jsonManager;
	
	/**
	 * Constructor that instantiates a URI Builder & a HTTP client
	 * @throws URISyntaxException
	 */
	public HttpManager() throws URISyntaxException{
		this.jsonManager = new JsonManager();
		this.uriBuilder = new URIBuilder(this.MAPS_URI);
		this.httpClient = HttpClients.createDefault();
	}
	
	/**
	 * Given an array of addresses, this method adds the addresses to the MAPS_URI string.
	 * @param addresses - The array that contains the start & end addresses
	 * @throws URISyntaxException
	 */
	private void setAddresses(String [] addresses) throws URISyntaxException{
		//Assuming first element represents start address
		this.uriBuilder = this.uriBuilder.setParameter("origin", addresses[0]).setParameter("destination", addresses[1]);
		this.setCompleteURI();
	}
	
	/**
	 * Sets the value of completeURI such that it contains the embedded addresses.
	 * @throws URISyntaxException
	 */
	private void setCompleteURI() throws URISyntaxException{
		this.completeURI = this.uriBuilder.build().toString();
	}
	
	/**
	 * Returns completeURI if it is not null.
	 * @return a String representing the request URI with the addresses embedded in.
	 * @throws Exception 
	 */
	public String getCompleteURI() throws Exception{
		if(this.completeURI == null){
			throw new Exception("The URI is incomplete, enter a pair of addresses first");
		}
		return this.completeURI;
	}
	
	/**
	 * Returns an InputStream that contains the contents of the HttpResponse
	 * @return an InputStream object
	 * @throws Exception
	 */
	public Route fetchDirections(String [] addresses) throws Exception{
		this.setAddresses(addresses);
		
		if(this.completeURI == null){
			throw new Exception("Cannot fetch directions. Please enter a pair of addresses first");
		}
		
		final HttpGet httpGet = new HttpGet(this.completeURI);
		final HttpResponse httpResponse = this.httpClient.execute(httpGet);
		final int httpStatusCode = httpResponse.getStatusLine().getStatusCode();
		
		if(httpStatusCode != 200){
			throw new Exception("Something went wrong! The HTTP status code is: " + httpStatusCode);
		}
		
		final InputStream directionStream = httpResponse.getEntity().getContent();
		this.jsonManager.setStream(directionStream);
		
		final Route route = this.jsonManager.getRoute();
		return route;
	}
}
