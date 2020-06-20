package com.thoughtworks.exam.bff.adapter.client.quiz;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuizDTO {
    private String quizId;

    @Override
    public String toString() {
        return quizId;
    }
}
