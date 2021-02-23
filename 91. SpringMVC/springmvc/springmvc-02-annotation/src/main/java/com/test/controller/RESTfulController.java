package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RESTfulController {
    @GetMapping("/add/{a}/{b}")
    public String test01(@PathVariable int a, @PathVariable int b, Model model){
        int ans = a+b;
        model.addAttribute("msg", "GET result:"+ ans);
        return "result";
    }
    @PostMapping("/add/{a}/{b}")
    public String test02(@PathVariable int a, @PathVariable int b, Model model){
        int ans = a+b;
        model.addAttribute("msg", "POST result:"+ ans);
        return "result";
    }

}
