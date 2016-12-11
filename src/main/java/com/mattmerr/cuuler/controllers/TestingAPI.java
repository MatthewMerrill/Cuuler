package com.mattmerr.cuuler.controllers;

import static spark.Spark.*;

/**
 * Created by merrillm on 12/10/16.
 */
public class TestingAPI {
    
    @API(value = "TestingAPI", mode = RuntimeMode.DEV)
    public static void deploy() {
        get("/test", (req, res) -> "hello world!");
    }
    
}
