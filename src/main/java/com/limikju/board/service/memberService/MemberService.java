package com.limikju.board.service.memberService;

import com.limikju.board.domain.Member;
import com.limikju.board.domain.dto.MemberDTO.MemberRequestDTO;

public interface MemberService {
    Member joinMember(MemberRequestDTO.JoinMemberDTO joinMemberDTO);
}
