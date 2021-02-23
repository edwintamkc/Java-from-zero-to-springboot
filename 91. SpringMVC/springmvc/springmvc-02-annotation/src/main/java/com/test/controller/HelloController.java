package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("msg","hello, spring mvc annotation!");
        return "hello"; // return view name俾resolver，加上prefix suffix後變成 /WEB-INF/jsp/hello.jsp
    }

    @GetMapping("/t1")
    public String test01(@RequestParam("username") String name, Model model){
        System.out.println("name:" + name);
        model.addAttribute("msg:","name"+name);
        return "result";
    }

}
