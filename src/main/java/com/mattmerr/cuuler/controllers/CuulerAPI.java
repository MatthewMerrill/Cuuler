package com.mattmerr.cuuler.controllers;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import spark.Spark;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by merrillm on 12/10/16.
 */
public class CuulerAPI {
    
    public static void main(String[] args) {
        deployAnnotations();
    }
    
    public static void deployAnnotations() {
        Spark.staticFileLocation("/public/app");
        
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("com.mattmerr"))
                .setScanners(new MethodAnnotationsScanner())
        );
        Set<Method> methodSet = reflections.getMethodsAnnotatedWith(API.class);
        
        for (Method method : methodSet) {
            API api = method.getAnnotation(API.class);
            
            try {
                method.invoke(null);
            } catch (Exception e) {
                System.err.printf("Error in calling API deployment \"%s\": \n",
                        api.value());
                e.printStackTrace();
            }
        }
        
        System.out.println("All deployments have been called.");
    }
}
