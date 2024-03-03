package com.limikju.board.apiPayload.exception.handler;

import com.limikju.board.apiPayload.code.BaseErrorCode;
import com.limikju.board.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
