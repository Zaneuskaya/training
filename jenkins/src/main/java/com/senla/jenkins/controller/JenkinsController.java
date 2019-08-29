package com.senla.jenkins.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/jenkins")
public class JenkinsController {

    @GetMapping("/entities")
    public List<String> getEntities() {
        List<String> entities = Arrays.asList("a", "b", "c");
        return entities;
    }
}
