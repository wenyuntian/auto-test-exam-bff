
package com.thoughtworks.exam.bff.adapter.client.quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CreateQuizCommand {
    private String teacherId;
    private String question;
    private Integer score;
    private String referenceAnswer;
}
