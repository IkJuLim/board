package com.limikju.board.domain.dto.MemberDTO;

import com.limikju.board.domain.Member;
import com.limikju.board.domain.enums.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MemberRequestDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JoinMemberDTO {

        private String username;
        private String password;
        private String name;
        private String nickName;
        private String email;
        private MemberRole role;
        private Integer age;
    }
}