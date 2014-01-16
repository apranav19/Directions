package com.directions.utils;

import java.lang.reflect.Type;

import org.jsoup.Jsoup;

import com.directions.models.RouteStep;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;


public class RouteStepDeserializer implements JsonDeserializer<RouteStep> {
	
	@Override
	public RouteStep deserialize(JsonElement element, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		
		final JsonObject stepObject = element.getAsJsonObject();
		
		final String instr = stepObject.get("html_instructions").getAsString();
		final String clean_instr = Jsoup.parse(instr).text();
		final long duration = stepObject.getAsJsonObject("duration").get("value").getAsLong();
		final long distance = stepObject.getAsJsonObject("distance").get("value").getAsLong();
		
		final RouteStep rs = new RouteStep();
		rs.setDistance(distance);
		rs.setDuration(duration);
		rs.setInstructions(clean_instr);
		
		return rs;
	}

}
