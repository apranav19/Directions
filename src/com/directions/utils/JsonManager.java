package com.directions.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.directions.models.Route;
import com.directions.models.RouteLeg;
import com.directions.models.RouteStep;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonManager {
	
	private GsonBuilder builder;
	private InputStream directionStream;
	
	public JsonManager(InputStream stream){
		this.builder = new GsonBuilder();
		this.directionStream = stream;
		this.registerDeserializers();
	}
	
	private void registerDeserializers(){
		this.builder.registerTypeAdapter(Route.class, new RouteDeserializer());
		this.builder.registerTypeAdapter(Route.class, new RouteDeserializer());
		this.builder.registerTypeAdapter(RouteLeg.class, new RouteLegDeserializer());
		this.builder.registerTypeAdapter(RouteStep.class, new RouteStepDeserializer());
	}
	
	public Route getRoute(){
		final Reader reader = new InputStreamReader(this.directionStream);
		final Gson gson = this.builder.create();
		
		final Route route = gson.fromJson(reader, Route.class);
		return route;
	}
	
	public void closeStreams() throws IOException{
		this.directionStream.close();
	}
}
