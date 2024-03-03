package com.limikju.board.domain.dto.MemberDTO;

import com.limikju.board.domain.Member;
import com.limikju.board.domain.enums.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

        public Member toEntity(){
            Member member = Member.builder()
                    .username(username)
                    .password(password)
                    .name(name)
                    .nickName(nickName)
                    .email(email)
                    .role(role)
                    .age(age).build();
            return member;
        }
    }
}