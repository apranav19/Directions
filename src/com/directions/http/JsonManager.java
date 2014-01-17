package com.directions.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.directions.models.Route;
import com.directions.models.RouteLeg;
import com.directions.models.RouteStep;
import com.directions.utils.RouteDeserializer;
import com.directions.utils.RouteLegDeserializer;
import com.directions.utils.RouteStepDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonManager {
	
	private GsonBuilder builder;
	private Reader reader;
	
	private InputStream directionStream;
	
	public JsonManager(){
		this.builder = new GsonBuilder();
		this.registerDeserializers();
	}
	
	private void registerDeserializers(){
		this.builder.registerTypeAdapter(Route.class, new RouteDeserializer());
		this.builder.registerTypeAdapter(RouteLeg.class, new RouteLegDeserializer());
		this.builder.registerTypeAdapter(RouteStep.class, new RouteStepDeserializer());
	}
	
	void setStream(InputStream stream){
		this.reader = new InputStreamReader(stream);
	}
	
	public Route getRoute(){
		final Gson gson = this.builder.create();
		final Route route = gson.fromJson(this.reader, Route.class);
		return route;
	}
	
	public void closeStreams() throws IOException{
		this.directionStream.close();
	}
}
