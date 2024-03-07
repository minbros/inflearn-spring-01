package com.example.inflearnspring01.service;

import com.example.inflearnspring01.domain.Member;
import com.example.inflearnspring01.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    // 이건 근데 왜 필요한 건지 모르겠음. MemoryMemberRepository를 생성하고 MemberService에 넣어주는 건 알겠는데
    // 왜 beforeEach에서 하는지 모르겠음. 그냥 필드에다가 선언해도 되는 거 아닌가?

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // IllegalStateException을 던지는 코드가 실행되면 성공

/*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // 에러 메시지가 "이미 존재하는 회원입니다."와 같으면 성공

        // then
    }

    @Test
    void findOne() {
    }

    @Test
    void findMembers() {
    }
}