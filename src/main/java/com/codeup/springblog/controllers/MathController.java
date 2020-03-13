package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{a}/and/{b}")
    @ResponseBody
    public String add(@PathVariable int a,@PathVariable int b){
        return ""+(a+b);
    }

    @GetMapping("/subtract/{a}/from/{b}")
    @ResponseBody
    public String subtract(@PathVariable int a,@PathVariable int b){
        return ""+(b-a);
    }

    @GetMapping("/multiply/{a}/and/{b}")
    @ResponseBody
    public String multiply(@PathVariable int a,@PathVariable int b){
        return ""+(a*b);
    }

    @GetMapping("/divide/{a}/by/{b}")
    @ResponseBody
    public double divide(@PathVariable double a,@PathVariable double b){
        return (a/b);
    }
}
