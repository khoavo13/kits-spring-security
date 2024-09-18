package com.example.security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    @GetMapping("")
    public String index() {
        return "list category";
    }

    @GetMapping("/new")
    public String login() {
        return "login category";
    }
}
