package com.limikju.board.controller;

import com.limikju.board.apiPayload.ApiResponse;
import com.limikju.board.apiPayload.code.status.SuccessStatus;
import com.limikju.board.converter.MemberConverter;
import com.limikju.board.domain.Member;
import com.limikju.board.domain.dto.MemberDTO.MemberRequestDTO;
import com.limikju.board.domain.dto.MemberDTO.MemberResponseDTO;
import com.limikju.board.service.memberService.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberRestController {
    private final MemberService memberService;
    private final MemberConverter memberConverter;

    @PostMapping("/join")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> joinMember(@RequestBody MemberRequestDTO.JoinMemberDTO joinMemberDTO){
        Member member = memberConverter.toMember(joinMemberDTO);
        Member joinMember = memberService.joinMember(member);
        return ApiResponse.of(SuccessStatus.MEMBER_JOIN, memberConverter.toJoinResultDTO(joinMember));
    }

    @PostMapping("/login")
    public ApiResponse<MemberResponseDTO.LoginResultDTO> login(@RequestBody MemberRequestDTO.LoginMemberDTO loginMemberDTO){
        String token = memberService.login(loginMemberDTO.getUsername(), loginMemberDTO.getPassword());
        return ApiResponse.of(SuccessStatus.MEMBER_LOGIN, memberConverter.toLoginResultDTO(token));
    }
}

