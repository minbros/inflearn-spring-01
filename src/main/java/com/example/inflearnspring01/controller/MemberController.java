package com.example.inflearnspring01.controller;

import com.example.inflearnspring01.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    // 생성자에 @Autowired가 있으면 스프링이 스프링 컨테이너에 있는 MemberService를 가져와 연결시켜준다.
    // (따로 new MemberService()를 하지 않아도 됨)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
