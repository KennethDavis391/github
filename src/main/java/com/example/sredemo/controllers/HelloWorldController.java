package com.example.sredemo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hw")
public class HelloWorldController {

    @GetMapping
    public String getHelloWorld() {
        return "Hello, World!";
    }
}
