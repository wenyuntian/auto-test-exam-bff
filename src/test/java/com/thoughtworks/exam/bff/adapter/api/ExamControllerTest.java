package com.thoughtworks.exam.bff.adapter.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.thoughtworks.exam.bff.adapter.client.exam.CreateAnswerSheetCommand;
import com.thoughtworks.exam.bff.adapter.client.exam.CreateExamCommand;
import com.thoughtworks.exam.bff.adapter.client.exam.SubmitAnswerCommand;
import com.thoughtworks.exam.bff.adapter.client.exam.SubmitAnswerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureStubRunner()
@AutoConfigureMockMvc
class ExamControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    protected WebApplicationContext wac;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void should_create_quizzes_successfully() throws Exception {
        CreateExamCommand createExamCommand = CreateExamCommand.builder()
                .teacherId("9043inol9f4ifnflmakmfdas09fd4ifnflma")
                .paperId("9043ino9f4if-flmakttfdas09fd4-fnflma")
                .time(60)
                .build();

        String json = objectMapper.writeValueAsString(createExamCommand);
        mockMvc.perform(post("/examinations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void should_create_answer_sheets_successfully() throws Exception {

        ResultActions resultActions = mockMvc.perform(post("/examinations/9idk4-lokfu-jr874j3-h8d9j4-hor82kd71/answer-sheets")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String responseString = resultActions.andReturn().getResponse().getContentAsString();

        CreateAnswerSheetCommand response = objectMapper.readValue(responseString, CreateAnswerSheetCommand.class);
        assertThat(response.getAnswerSheetId()).matches("[a-zA-Z-0-9]{36}");
    }

    @Test
    public void should_submit_answer_successfully() throws Exception {
        SubmitAnswerCommand submitAnswerCommand = SubmitAnswerCommand.builder()
                .studentId("9043inol9f4ifnflmakmfdas09fd4ifnflma")
                .answer("a,b,c")
                .startTime(LocalDateTime.parse("2020-07-04T00:00:00"))
                .submitTime(LocalDateTime.parse("2020-07-04T01:00:00"))
                .build();
        ResultActions resultActions = mockMvc.perform(put("/examinations/9idk4-lokfu-jr874j3-h8d9j4-hor82kd77/answer-sheet/9idk4-lokfu-jr874j3-u8d9j4-hor82kd77")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(submitAnswerCommand)))
                .andExpect(status().isOk());

        String responseString = resultActions.andReturn().getResponse().getContentAsString();

        SubmitAnswerDTO response = objectMapper.readValue(responseString, SubmitAnswerDTO.class);
        assertThat(response.getAnswer()).isEqualTo("a,b,c");
    }

    @Test
    public void should_return_bad_request_status_when_expired() throws Exception {
        SubmitAnswerCommand submitAnswerCommand = SubmitAnswerCommand.builder()
                .studentId("9043inol9f4ifnflmakmfdas09fd4ifnflma")
                .answer("a,b,c")
                .startTime(LocalDateTime.parse("2020-07-04T00:00:00"))
                .submitTime(LocalDateTime.parse("2020-07-07T02:00:00"))
                .build();
        mockMvc.perform(put("/examinations/9idk4-lokfu-jr874j3-h8d9j4-hor82kd77/answer-sheet/9idk4-lokfu-jr874j3-u8d9j4-hor82kd77")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(submitAnswerCommand)))
                .andExpect(status().isBadRequest());
    }
}
