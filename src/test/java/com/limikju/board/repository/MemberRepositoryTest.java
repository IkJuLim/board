package com.limikju.board.repository;

import com.limikju.board.domain.Member;
import com.limikju.board.domain.enums.MemberRole;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @PersistenceContext
    EntityManager em;

    private void clear(){
        em.flush();
        em.clear();
    }

    @AfterEach
    public void afterEach(){
        em.clear();
    }

    // 회원 저장
    @Test
    void 회원저장_성공() throws Exception {
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
        Member saveMember = memberRepository.save(member);

        // then
        Member findMember = memberRepository.findById(saveMember.getId()).orElseThrow(() -> new RuntimeException("저장된 회원이 없습니다."));

        Assertions.assertThat(findMember).isEqualTo(member);
        Assertions.assertThat(findMember).isEqualTo(saveMember);
    }

    // 회원 저장시 아이디가 없으면 오류
    @Test
    void 회원저장_실패_회원가입시_아이디가_없음() throws Exception {
        // given
        Member member = Member.builder()
                .password("1234567890")
                .name("Member1")
                .nickName("NickName1")
                .email("username@email.com")
                .role(MemberRole.USER)
                .age(22).build();

        // when

        // then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    // 회원 저장시 이름이 없으면 오류
    @Test
    void 회원저장_실패_회원가입시_이름이_없음() throws Exception {
        // given
        Member member = Member.builder()
                .username("username")
                .password("1234567890")
                .nickName("NickName1")
                .email("username@email.com")
                .role(MemberRole.USER)
                .age(22).build();

        // when

        // then
        org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    // 회원 저장시 닉네임이 없으면 오류
    @Test
    void 회원저장_실패_회원가입시_닉네임이_없음() throws Exception {
        // given
        Member member = Member.builder()
                .username("username")
                .password("1234567890")
                .name("Member1")
                .email("username@email.com")
                .role(MemberRole.USER)
                .age(22).build();

        // when

        // then
        org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    // 회원 저장시 이메일이 없으면 오류
    @Test
    void 회원저장_실패_회원가입시_이메일이_없음() throws Exception {
        // given
        Member member = Member.builder()
                .username("username")
                .password("1234567890")
                .name("Member1")
                .nickName("NickName1")
                .role(MemberRole.USER)
                .age(22).build();

        // when

        // then
        org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    // 회원 저장시 나이가 없으면 오류
    @Test
    void 회원저장_실패_회원가입시_나이가_없음() throws Exception {
        // given
        Member member = Member.builder()
                .username("username")
                .password("1234567890")
                .name("Member1")
                .nickName("NickName1")
                .email("username@email.com")
                .role(MemberRole.USER).build();

        // when

        // then
        org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    // 회원 저장시 중복된 아이디가 있으면 오류
    @Test
    void 회원저장_실패_회원가입시_아이디가_중복() throws Exception {
        // given
        Member member1 = Member.builder()
                .username("username")
                .password("1234567890")
                .name("Member1")
                .nickName("NickName1")
                .email("username@email.com")
                .role(MemberRole.USER)
                .age(22).build();

        Member member2 = Member.builder()
                .username("username")
                .password("1234567890")
                .name("Member2")
                .nickName("NickName2")
                .email("username@email.com")
                .role(MemberRole.USER)
                .age(22).build();

        // when
        memberRepository.save(member1);
        clear();

        // then
        org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> memberRepository.save(member2));
    }

    // existsByUsername이 잘 작동하는지
    @Test
    void existsByUsername_작동테스트() throws Exception {
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
        memberRepository.save(member);
        clear();

        // then
        Assertions.assertThat(memberRepository.existsByUsername(member.getUsername())).isTrue();
        Assertions.assertThat(memberRepository.existsByUsername("username1")).isFalse();
    }

    // existsByEmail이 잘 작동하는지
    @Test
    void existsByEmail_작동테스트() throws Exception {
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
        Member saveMember = memberRepository.save(member);
        clear();

        // then
        Assertions.assertThat(memberRepository.existsByEmail(member.getEmail())).isTrue();
        Assertions.assertThat(memberRepository.existsByEmail("username1@email.com")).isFalse();
    }
}