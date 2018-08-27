package com.validic.rest.client;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JobServiceClient {
	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;
	
	@Value( "${endpoint}" )
	private String url;
	 
    public JobServiceClient() {
        restTemplate = new RestTemplate();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }
 
    private UriComponentsBuilder buildUri(String location, String search, String type){
    	UriComponentsBuilder builder = UriComponentsBuilder
	    	    .fromUriString(url)
	    	    // Add query parameter
	    	    .queryParam("location",location)
	    	    .queryParam("search", search);
    	if(type != null){
    		builder.queryParam(type, "true");
    	}
    	return builder;
    }
    public  String getJobDetails(String location, String search, String type, String page) {
    	String uri = buildUri(location,search,type).queryParam("page", page).toUriString();
        return restTemplate.getForObject(uri, String.class);
    }
    
    public int countJobDetails(String location, String search, String type){
    	int page = 0; int count = 0;List<JobDetails> list;
    	do {
        	list = convertjsonArrayToEntityList(getJobDetails(location, search, type, String.valueOf(page)));
        	count = count + list.size();
    		page++;
        } while (!list.isEmpty());
    	return count;
    }
    
    private List<JobDetails> convertjsonArrayToEntityList(String  jsonArray){
    	List<JobDetails> asList = Collections.emptyList();
		try {
			asList = objectMapper.readValue(
			      jsonArray, new TypeReference<List<JobDetails>>() { });
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return asList;
    }

}
