package com.directions.models;

import java.util.List;

/**
 * This class represents a single route between two addresses
 * @author Pranav Angara
 */
public class Route {
	private String routeSummary;
	private List<RouteLeg> routeLegs;
	
	/**
	 * Sets the summary of the route
	 * @param summary - A String representing the route summary
	 */
	public void setSummary(String summary){
		this.routeSummary = summary;
	}
	
	
	public void setLegs(List<RouteLeg> routeLegs){
		this.routeLegs = routeLegs;
	}
	
	public List<RouteLeg> getLegs(){
		return this.routeLegs;
	}
	
	public String getSummary(){
		return this.routeSummary;
	}
	
	public String toString(){
		String summary = "Summary: " + this.routeSummary + "\n";
		for(RouteLeg leg: this.routeLegs){
			summary += leg.toString() + "\n";
		}
		return summary;
	}
}
