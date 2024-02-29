package com.example.inflearnspring01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        // 이 MVC 방식은 view로 넘어가기 때문에 해당 페이지의 소스를 확인하면 해당 view의 html 소스가 나타남
    }

    @GetMapping("hello-string")
    @ResponseBody // http의 body부에 이 데이터를 직접 넣어주겠다는 뜻
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello " + name을 http의 body부에 직접 넣어서 반환
        // 그런데 이 방식은 view가 없이 바로 데이터를 반환하기 때문에 view의 소스를 확인할 수 없음(그냥 문자열 한 줄만 뜸)
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체를 반환하면 JSON 방식으로 반환됨
        // {"name":"이름"} 이런 식으로 반환됨
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
