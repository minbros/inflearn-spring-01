package com.example.inflearnspring01;

import com.example.inflearnspring01.repository.MemberRepository;
import com.example.inflearnspring01.repository.MemoryMemberRepository;
import com.example.inflearnspring01.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    // 스프링 빈에 등록
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
