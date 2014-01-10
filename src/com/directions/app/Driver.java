package com.directions.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;

import com.directions.http.HttpManager;

public class Driver {

	private final static String START_ADDRESS = "340 University Ave, Palo Alto, CA";
	private final static String END_ADDRESS = "601 Nelson Rd, Stanford, CA";
	
	public static void main(String[] args) throws Exception {
		
		HttpManager manager = new HttpManager();
		// Create an array of addresses
		String [] addresses = new String[]{START_ADDRESS, END_ADDRESS};
		manager.setAddresses(addresses);
		
		HttpResponse response = manager.fetchDirections();
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line;
		
		while((line = reader.readLine()) != null){
			System.out.println(line);
		}
		reader.close();
	}
}
