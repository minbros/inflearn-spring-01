package com.example.inflearnspring01.controller;

import com.example.inflearnspring01.domain.Member;
import com.example.inflearnspring01.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    // 생성자에 @Autowired가 있으면 스프링이 스프링 컨테이너에 있는 MemberService를 가져와 연결시켜준다.
    // (따로 new MemberService()를 하지 않아도 됨)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    // Get 방식으로 mapping할 때 사용됨(get은 주로 조회하는 기능)
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    // Post 방식으로 mapping할 때 사용됨(post는 주로 어떤 데이터를 등록하는 기능)
    public String create(MemberForm form) {
        // form.name에 해당 페이지에서 등록한 이름이 저장됨
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
