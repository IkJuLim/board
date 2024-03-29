package com.limikju.board.apiPayload.code.status;

import com.limikju.board.apiPayload.code.BaseCode;
import com.limikju.board.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 일반적인 응답
    _OK(HttpStatus.OK, "COMMON200", "성공입니다."),

    // 멤버 관련 응답
    MEMBER_FOUND(HttpStatus.OK,"MEMBER2001", "회원을 조회했습니다."),
    MEMBER_UPDATE(HttpStatus.OK, "MEMBER2002", "회원정보를 업데이트 했습니다. "),
    MEMBER_DELETE(HttpStatus.OK, "MEMBER2003", "회원 탈퇴 성공"),
    MEMBER_JOIN(HttpStatus.OK, "MEMBER2004", "회원가입 성공"),
    MEMBER_LOGIN(HttpStatus.OK, "MEMBER2005", "로그인 성공")
    ;


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
