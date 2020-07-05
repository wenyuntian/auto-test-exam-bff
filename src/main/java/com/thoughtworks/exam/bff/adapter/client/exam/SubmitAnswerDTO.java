package com.thoughtworks.exam.bff.adapter.client.exam;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubmitAnswerDTO {
    private String answerSheetId;
    private String examinationId;
    private String studentId;
    private String answer;
    private LocalDateTime starTime;
    private LocalDateTime submitTime;
}
