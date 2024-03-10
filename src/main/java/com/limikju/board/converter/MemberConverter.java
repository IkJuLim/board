package com.limikju.board.converter;

import com.limikju.board.domain.Member;
import com.limikju.board.domain.dto.MemberDTO.MemberRequestDTO;
import com.limikju.board.domain.dto.MemberDTO.MemberResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberConverter {

    private final BCryptPasswordEncoder passwordEncoder;

    public MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .member_id(member.getId())
                .created_at(member.getCreatedAt())
                .build();
    }

    public Member toMember(MemberRequestDTO.JoinMemberDTO joinMemberDTO){
        Member member = Member.builder()
                .username(joinMemberDTO.getUsername())
                .password(passwordEncoder.encode(joinMemberDTO.getPassword()))
                .name(joinMemberDTO.getName())
                .nickName(joinMemberDTO.getNickName())
                .email(joinMemberDTO.getEmail())
                .role(joinMemberDTO.getRole())
                .age(joinMemberDTO.getAge()).build();
        return member;
    }

    public MemberResponseDTO.LoginResultDTO toLoginResultDTO(String token) {
        return MemberResponseDTO.LoginResultDTO.builder()
                .token(token)
                .build();
    }
}
