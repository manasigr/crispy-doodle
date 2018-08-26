package com.validic.demo;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.validic.config.ClientConfig;
import com.validic.rest.client.JobDetails;
import com.validic.rest.client.JobServiceClient;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ClientConfig.class)
public class JobServiceClientTest {
	 
	final ObjectMapper objectMapper = new ObjectMapper();
	    
	    @Autowired
	    JobServiceClient githubJobClient;
	 
	   @Test
	    public void whenCallingGetJob() 
	      throws Exception {
	    	String jsonArray = this.githubJobClient.getJobDetails("new+york", "java", "full_time", "0");
	    	List<JobDetails> asList = objectMapper.readValue(
	    		      jsonArray, new TypeReference<List<JobDetails>>() { });
	    	System.out.println("Found "+asList.size() + " jobs");
	    	assertThat(asList.get(0), instanceOf(JobDetails.class));			
	    }
	   

}
