package com.thoughtworks.exam.bff.adapter.client.exam;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class ExamClient {
    private RestTemplate restTemplate;

    @Value("${examService.host}")
    private String examHost;

    @Value("${examService.port}")
    private String examPort;

    public ExamClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ExamDTO create(CreateExamCommand createExamCommand) {
        return restTemplate.postForObject(examHost + ":" + examPort + "/examinations",
                createExamCommand, ExamDTO.class);
    }

    public AnswerSheetDTO createAnswerSheet(String exanId) {
        return restTemplate.postForObject(examHost + ":" + examPort + "/examinations/"+ exanId + "/answer-sheets",
                null, AnswerSheetDTO.class);
    }

    public  SubmitAnswerDTO submitAnswer(String examinationId, String answerId, SubmitAnswerCommand submitAnswerCommand) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("ContentType", Arrays.asList("application/json"));
        HttpEntity<SubmitAnswerCommand> httpEntity = new HttpEntity(submitAnswerCommand, headers);
        ResponseEntity<SubmitAnswerDTO> responseEntity = restTemplate.exchange(examHost + ":" + examPort + "/examinations/" + examinationId + "/answer-sheet/" + answerId, HttpMethod.PUT,  httpEntity, SubmitAnswerDTO.class);
        return responseEntity.getBody();
    }
}
