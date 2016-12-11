package com.mattmerr.cuuler.controllers;

import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static spark.Spark.*;

/**
 * Created by merrillm on 12/10/16.
 */
public class RESTv1 {
    
    private static final String PREFIX = "/api/v1";
   
    @API(value = "RESTv1")
    public static void deploy() {
        deployGets();
        deployPosts();
    }
    
    private static void deployGets() {
        get(PREFIX+"/:type", RESTv1::getModel);
    }
    
    private static void deployPosts() {
        post(PREFIX+"/:type", RESTv1::postModel);
    }
    
    private static String getModel(Request req, Response res) {
        String type = req.params("type");
        
        if (type.equals("messages")) {
            return "[{\"name\":\"Matt\",\"message\":\"Hello World!\"}," +
                    "{\"name\":\"MrFizzBuzz\",\"message\":\"Fizz!\"}," +
                    "{\"name\":\"Sally\",\"message\":\"I'm selling seashells by the seashore!\"}]";
        }
        
        return type;
    }
    
    private static List<String> messages = new ArrayList<>();
    private static String postModel(Request req, Response res) {
        String type = req.params("type");
        
        if (type.equals("messages")) {
            messages.add(req.body());
            String ret = messages.stream()
                    .collect(joining(",","[","]"));
            
            System.out.println(ret);
            return ret;
        }
        
        return type;
    }
    
}
