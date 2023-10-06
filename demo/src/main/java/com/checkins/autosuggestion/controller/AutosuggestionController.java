package com.checkins.autosuggestion.controller;

import com.checkins.autosuggestion.model.Location;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/suggestion")
public class AutosuggestionController {

    @GetMapping("/search/{query}")
    public List<Location> getSuggestion(@PathVariable("query") String query){
        return null;
    }
}
