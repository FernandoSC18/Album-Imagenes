package com.example.demo.controller;
 
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler; 

/*
 * Request Exception handling methods
 * reference : https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
 */ 

@ControllerAdvice
class GlobalControllerExceptionHandler {
    

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex, 
                    HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> map = new HashMap<String, Object>(); 
  
        if (ex != null && ex instanceof NullPointerException) {
            ex.printStackTrace(); 
            map.put("code", HttpStatus.INTERNAL_SERVER_ERROR );
            map.put("error", "BAD REQUEST. posible null? " + ex.getMessage());
            map.put("message", null);
            map.put("result", ex.getStackTrace()); 
            return new ResponseEntity<Object> (map, HttpStatus.BAD_REQUEST );
        }  

        if (ex != null && (ex instanceof SQLException || ex instanceof DataAccessException)) { 
            ex.printStackTrace();
            map.put("code", HttpStatus.INTERNAL_SERVER_ERROR );
            map.put("error", "Database Error. " + ex.getMessage());
            map.put("message", null);
            map.put("result", ex.getStackTrace()); 
            return new ResponseEntity<Object> (map, HttpStatus.INTERNAL_SERVER_ERROR );
        }
 
        if (ex != null && ex instanceof Exception) { 
            ex.printStackTrace();
            map.put("code", HttpStatus.INTERNAL_SERVER_ERROR );
            map.put("error", ex.getMessage());
            map.put("message", null);
            map.put("result", ex.getStackTrace());
            return new ResponseEntity<Object> (map, HttpStatus.INTERNAL_SERVER_ERROR );
        } 

        map.put("code", HttpStatus.resolve(response.getStatus()) );
        map.put("error", null);
        map.put("message", response.getStatus());
        map.put("result", response);
            
        return new ResponseEntity<Object> (map, HttpStatus.resolve(response.getStatus()));
        
    }
}