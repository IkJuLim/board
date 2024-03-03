package com.limikju.board.service;

import com.limikju.board.domain.dto.MemberDTO.MemberRequestDTO;
import com.limikju.board.domain.enums.MemberRole;
import com.limikju.board.repository.MemberRepository;
import com.limikju.board.service.memberService.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @InjectMocks private MemberServiceImpl memberService;

    @Mock private MemberRepository memberRepository;

    @Test
    void join() {
        MemberRequestDTO.JoinMemberDTO memberDto = new MemberRequestDTO.JoinMemberDTO(
                "Username1",
                "1234567890",
                "Member",
                "NickName",
                "Username1@email.com",
                MemberRole.USER,
                20
        );

        when(memberRepository.save(any())).thenReturn(memberDto.toEntity());
        MemberRequestDTO.JoinMemberDTO memberDto2 = new MemberRequestDTO.JoinMemberDTO(
                "Username2",
                "1234567890",
                "Member",
                "NickName",
                "Username2@email.com",
                MemberRole.USER,
                20
        );
        when(memberRepository.save(any())).thenReturn(memberDto2.toEntity());

        // when
        memberService.joinMember(memberDto);
        memberService.joinMember(memberDto);
    }
}