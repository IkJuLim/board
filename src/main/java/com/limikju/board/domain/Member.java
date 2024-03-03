package com.limikju.board.domain;

import com.limikju.board.domain.common.BaseEntity;
import com.limikju.board.domain.enums.MemberRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Table(name = "MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

    // pk
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    // 아이디
    @Column(nullable = false, length = 30, unique = true)
    private String username;

    // 비밀번호
    private String password;

    // 실명
    @Column(nullable = false, length = 30)
    private String name;

    // 별명
    @Column(nullable = false, length = 30)
    private String nickName;

    // 이메일
    @Column(nullable = false, length = 30)
    private String email;

    // 나이
    @Column(nullable = false)
    private Integer age;

    // 권한 -> USER, ADMIN
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    public void updatePassword(PasswordEncoder passwordEncoder, String password){
        this.password = passwordEncoder.encode(password);
    }

    public void updateName(String name){
        this.name = name;
    }

    public void updateNickName(String nickName){
        this.nickName = nickName;
    }

    public void updateAge(int age){
        this.age = age;
    }

    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }
}