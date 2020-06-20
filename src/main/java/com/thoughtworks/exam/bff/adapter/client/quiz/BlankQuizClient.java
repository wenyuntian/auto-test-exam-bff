package com.thoughtworks.exam.bff.adapter.client.quiz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlankQuizClient {
    private RestTemplate restTemplate;

    @Value("${examService.host}")
    private String examHost;

    @Value("${examService.port}")
    private String examPort;

    public BlankQuizClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String create(CreateQuizCommand createQuizCommand) {
        return restTemplate.postForObject(examHost + ":" + examPort + "/quizzes", createQuizCommand, BlankQuizDTO.class).toString();
    }
}