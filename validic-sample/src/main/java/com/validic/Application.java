package com.validic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import com.validic.config.ClientConfig;
import com.validic.rest.client.JobDetails;
import com.validic.rest.client.JobServiceClient;

@PropertySource("classpath:application.properties")
public class Application {
	@Autowired
	static Environment env;
    
    public static void main(String args[]) {
    	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ClientConfig.class);
    	
    	List<String> locations = Arrays.asList(ctx.getBean(Environment.class).getProperty("listOfLocations").split(","));
    	List<String> codes = Arrays.asList(ctx.getBean(Environment.class).getProperty("listOfCodes").split(","));
    	List<String> types = Arrays.asList(ctx.getBean(Environment.class).getProperty("listOfTypes").split(","));
    	JobServiceClient myService = ctx.getBean(JobServiceClient.class);
    	int count = 0;
    	Map<JobDetails, String> jobs = new HashMap<JobDetails,String>();
    	for(String location:locations){
    		for(String code:codes){
    			for(String type:types){
    				count = myService.countJobDetails(location, code, type);
    				jobs.put(new JobDetails(location,code,type), String.valueOf(count));
    			}
    		}
    	}
    	//System.out.println(Arrays.toString(jobs.entrySet().toArray()));
    	for (Map.Entry<JobDetails, String> entry : jobs.entrySet()) {
    	    System.out.println(entry.getKey()+" : "+entry.getValue());
    	}
    	System.out.println("Sourced: "+myService.countJobDetails(null, null, null)+" job postings");
    	
    }
    
    

}
