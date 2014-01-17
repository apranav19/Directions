package com.directions.app;

import com.directions.http.HttpManager;
import com.directions.models.Route;

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
		Route route = manager.fetchDirections(new String[]{START_ADDRESS, END_ADDRESS});
		System.out.println(route.toString());
	}
}
