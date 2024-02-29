package com.example.inflearnspring01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring!!"); // model에 data라는 이름으로 spring!!을 담아서 view로 넘김
        return "hello"; // templates/hello.html로 이동
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { // name이라는 파라미터를 받아서 model에 담아서 view로 넘김
        // RequestParam은 웹 주소 상에서 받고, 웹 주소 뒤에 ?name=이름 형식으로 받음
        model.addAttribute("name", name);
        return "hello-template";
    }
}
