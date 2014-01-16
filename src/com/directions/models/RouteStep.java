package com.directions.models;

public class RouteStep {
	
	private long duration;
	private long distance;
	
	private String instructions;
	
	public void setInstructions(String instructions){
		this.instructions = instructions;
	}
	
	public void setDistance(long distance){
		this.distance = distance;
	}
	
	public void setDuration(long duration){
		this.duration = duration;
	}
	
	public long getDistance(){
		return this.distance;
	}
	
	public long getDuration(){
		return this.duration;
	}
	
	public String getInstructions(){
		return this.instructions;
	}
	
	public String getFormattedStep(){
		return this.instructions + " (Distance: " + this.distance + "; Duration: " + this.duration + ")";
	}
}
