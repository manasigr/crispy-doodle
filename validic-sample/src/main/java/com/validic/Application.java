package com.validic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
    	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        System.out.println("hello world");
    }

}
