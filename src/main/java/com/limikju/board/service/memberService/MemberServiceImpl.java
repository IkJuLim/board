package com.limikju.board.service.memberService;

import com.limikju.board.apiPayload.code.status.ErrorStatus;
import com.limikju.board.apiPayload.exception.handler.MemberHandler;
import com.limikju.board.converter.MemberConverter;
import com.limikju.board.domain.Member;
import com.limikju.board.domain.dto.MemberDTO.MemberRequestDTO;
import com.limikju.board.repository.MemberRepository;
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

    /**
     * front 에서 정보 전달
     * @param joinMemberDTO
     * @return
     */
    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinMemberDTO joinMemberDTO) {

        Member member = joinMemberDTO.toEntity();

        if(memberRepository.existsByEmail(member.getEmail()) || memberRepository.existsByUsername(member.getUsername())){
            return memberRepository.findById(member.getId()).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        }

        return memberRepository.save(member);
    }
}
