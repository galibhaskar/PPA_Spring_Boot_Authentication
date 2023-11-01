package com.PPA_Spring_Boot.projects.miniproject3.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthorizationTest {

    @RequestMapping(method = RequestMethod.GET, path="/{id}", produces = "application/json")
    public String getBook(@PathVariable int id, @RequestParam(value = "num") Optional<Integer> num) {

        System.out.println(num);

        return "hello";
    }
}
