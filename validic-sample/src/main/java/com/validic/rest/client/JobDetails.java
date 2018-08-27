package com.validic.rest.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobDetails {
	private String location;
	private String type;
	private String search;
	public JobDetails() {
	}
	public JobDetails(String location2, String code, String type2) {
		this.location = location2;
		this.search = code;
		this.type = type2;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
    public String toString() {
        return location + ":\n"+
                " - "+ search +"\n"+
                " - "+ type  
                ;
    }
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
	
}
