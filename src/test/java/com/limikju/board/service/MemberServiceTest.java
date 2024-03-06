package com.limikju.board.service;

import com.limikju.board.domain.Member;
import com.limikju.board.domain.enums.MemberRole;
import com.limikju.board.service.memberService.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired private MemberService memberService;

    @Test
    void join_성공() {
        // given
        Member member = Member.builder()
                .username("username")
                .password("1234567890")
                .name("Member1")
                .nickName("NickName1")
                .email("username@email.com")
                .role(MemberRole.USER)
                .age(22).build();

        // when
        Member joinMember = memberService.joinMember(member);

        // then
        Assertions.assertThat(joinMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(joinMember.getPassword()).isEqualTo(member.getPassword());
        Assertions.assertThat(joinMember.getName()).isEqualTo(member.getName());
        Assertions.assertThat(joinMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(joinMember.getNickName()).isEqualTo(member.getNickName());
        Assertions.assertThat(joinMember.getEmail()).isEqualTo(member.getEmail());
        Assertions.assertThat(joinMember.getRole()).isEqualTo(member.getRole());
        Assertions.assertThat(joinMember.getAge()).isEqualTo(member.getAge());
        Assertions.assertThat(joinMember).isEqualTo(member);
    }

    @Test
    void join_실패_username_중복() {
        // given
        Member member1 = Member.builder()
                .username("username")
                .password("1234567890")
                .name("Member1")
                .nickName("NickName1")
                .email("username1@email.com")
                .role(MemberRole.USER)
                .age(22).build();

        Member member2 = Member.builder()
                .username("username")
                .password("1234567890")
                .name("Member1")
                .nickName("NickName1")
                .email("username2@email.com")
                .role(MemberRole.USER)
                .age(22).build();


        // when
        Member joinMember = memberService.joinMember(member1);

        // then
        org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> memberService.joinMember(member2));
    }

    @Test
    void join_실패_email_중복() {
        // given
        Member member1 = Member.builder()
                .username("username1")
                .password("1234567890")
                .name("Member1")
                .nickName("NickName1")
                .email("username@email.com")
                .role(MemberRole.USER)
                .age(22).build();

        Member member2 = Member.builder()
                .username("username2")
                .password("1234567890")
                .name("Member1")
                .nickName("NickName1")
                .email("username@email.com")
                .role(MemberRole.USER)
                .age(22).build();


        // when
        Member joinMember = memberService.joinMember(member1);

        // then
        org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> memberService.joinMember(member2));
    }
}