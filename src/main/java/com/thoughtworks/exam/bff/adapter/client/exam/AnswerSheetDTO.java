package com.thoughtworks.exam.bff.adapter.client.exam;

import lombok.Data;

@Data
public class AnswerSheetDTO {
    private String answerSheetId;

    @Override
    public String toString() {
        return answerSheetId;
    }
}
