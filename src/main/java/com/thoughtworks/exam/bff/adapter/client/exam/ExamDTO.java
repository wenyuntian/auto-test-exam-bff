package com.thoughtworks.exam.bff.adapter.client.exam;

import lombok.Data;

@Data
public class ExamDTO {
    private String examId;

    @Override
    public String toString() {
        return examId;
    }
}
