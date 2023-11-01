package com.PPA_Spring_Boot.projects.miniproject3.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class TestAPI {
    @RequestMapping(method = RequestMethod.GET, path = "/getAll", produces = "application/json")
    public ResponseEntity<String> getAllItems() {
        System.out.println("get all items request");

        return ResponseEntity.ok().body("Data Fetch Success");
    }
}
