package com.directions.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;

import com.directions.http.HttpManager;

/**
 * This class demonstrates how to use this mini application to fetch driving directions
 * using the Google Maps Directions API.
 * @author Pranav Angara
 *
 */
public class Driver {

	private final static String START_ADDRESS = "340 University Ave, Palo Alto, CA";
	private final static String END_ADDRESS = "601 Nelson Rd, Stanford, CA";
	
	public static void main(String[] args) throws Exception {
		
		HttpManager manager = new HttpManager();
		
		// Create an array of addresses & feed them to the manager
		String [] addresses = new String[]{START_ADDRESS, END_ADDRESS};
		manager.setAddresses(addresses);
		
		// Fetch the contents (JSON) of the response
		InputStream directionStream= manager.fetchDirections();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(directionStream));
		String line;
		
		while((line = reader.readLine()) != null){
			System.out.println(line);
		}
		reader.close();
	}
}
