package com.mattmerr.cuuler.controllers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by merrillm on 12/10/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface API {
    
    String value();
    RuntimeMode mode() default RuntimeMode.PRODUCTION;
    
}
