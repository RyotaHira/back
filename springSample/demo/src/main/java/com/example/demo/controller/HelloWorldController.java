package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HelloWorldController {
    @RequestMapping(value = "/", method = RequestMethod.GET) //value：url　method：受け取り方
    public String helloWorld(Model model) {
        model.addAttribute("message", "Hello World!!"); // htmlに渡すキー：message
        return "index"; // 表示する画面
    }
}
