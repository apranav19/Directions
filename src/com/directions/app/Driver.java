package com.directions.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;

import com.directions.http.HttpManager;
import com.directions.models.Route;
import com.directions.models.RouteLeg;
import com.directions.models.RouteStep;
import com.directions.utils.JsonManager;
import com.directions.utils.RouteDeserializer;
import com.directions.utils.RouteLegDeserializer;
import com.directions.utils.RouteStepDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
		InputStream directionStream = manager.fetchDirections();
		JsonManager jsonManager = new JsonManager(directionStream);
		
		Route route = jsonManager.getRoute();
		
		System.out.println(route.toString());
	}
}
