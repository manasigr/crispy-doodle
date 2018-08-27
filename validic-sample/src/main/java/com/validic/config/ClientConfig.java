package com.validic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.validic.rest.client.JobServiceClient;

@Configuration
@PropertySource("classpath:application.properties")
public class ClientConfig {
	@Bean(name="githubJobClient")
    public JobServiceClient helloWorld() {
        return new JobServiceClient();
    }
	  

}
