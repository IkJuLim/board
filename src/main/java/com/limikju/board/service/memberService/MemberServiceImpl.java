package com.limikju.board.service.memberService;

import com.limikju.board.apiPayload.code.status.ErrorStatus;
import com.limikju.board.apiPayload.exception.handler.MemberHandler;
import com.limikju.board.converter.MemberConverter;
import com.limikju.board.domain.Member;
import com.limikju.board.domain.dto.MemberDTO.MemberRequestDTO;
import com.limikju.board.repository.MemberRepository;
import com.limikju.board.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Member joinMember(Member member){
        if(memberRepository.existsByEmail(member.getEmail()) || memberRepository.existsByUsername(member.getUsername())){
            return memberRepository.findById(member.getId())
                    .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        }

        return memberRepository.save(member);
    }

    @Override
    public String login(String userName, String password) {
        // username 없음
        Member selectedMember = memberRepository.findByUsername(userName)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        // password 불일치
        if(!passwordEncoder.matches(password, selectedMember.getPassword())){
            throw new MemberHandler(ErrorStatus.MEMBER_INVALID_PASSWORD);
        }

        // 성공 - 토큰 발행
        String token = JwtTokenUtil.createToken(selectedMember.getUsername());
        return token;
    }
}
