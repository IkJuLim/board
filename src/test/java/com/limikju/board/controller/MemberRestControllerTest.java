package com.limikju.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.limikju.board.apiPayload.code.status.ErrorStatus;
import com.limikju.board.apiPayload.exception.handler.MemberHandler;
import com.limikju.board.converter.MemberConverter;
import com.limikju.board.domain.dto.MemberDTO.MemberRequestDTO;
import com.limikju.board.domain.enums.MemberRole;
import com.limikju.board.service.memberService.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberRestController.class)
@MockBean(JpaMetamodelMappingContext.class)
class MemberRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MemberService memberService;

    @MockBean
    MemberConverter memberConverter;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser
    void 회원가입_성공() throws Exception {
        // given
        MemberRequestDTO.JoinMemberDTO memberDto = new MemberRequestDTO.JoinMemberDTO(
                "Username",
                "1234567890",
                "Member1",
                "NickName1",
                "Username@email.com",
                MemberRole.USER,
                20
        );

        // when, then
        mockMvc.perform(post("/api/v1/members/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDto))
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void 회원가입_실패_중복() throws Exception {
        // given
        MemberRequestDTO.JoinMemberDTO memberDto = new MemberRequestDTO.JoinMemberDTO(
                "Username",
                "1234567890",
                "Member1",
                "NickName1",
                "Username@email.com",
                MemberRole.USER,
                20
        );

        // when
        when(memberService.joinMember(any()))
                .thenThrow(new MemberHandler(ErrorStatus.MEMBER_ALREADY_EXIST));

        // then
        mockMvc.perform(post("/api/v1/members/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDto))
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}