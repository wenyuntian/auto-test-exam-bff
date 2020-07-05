
package com.thoughtworks.exam.bff.adapter.client.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CreateAnswerSheetCommand {
    private String answerSheetId;
    private String studentId;
    private String examId;
    private String teacherId;
    private String paperId;
    private int time;
}
