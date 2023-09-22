package com.construe.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private Security security;
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("apikey", "your unique API key");
        return "index";
    }
    @GetMapping("new")
    public String getApiKey(Model model){
        model.addAttribute("apikey", security.generateApiKey());
        return "index";
    }
}
