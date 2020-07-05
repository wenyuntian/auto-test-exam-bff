package com.thoughtworks.exam.bff.adapter.api;

import com.thoughtworks.exam.bff.adapter.client.exam.AnswerSheetDTO;
import com.thoughtworks.exam.bff.adapter.client.exam.CreateAnswerSheetCommand;
import com.thoughtworks.exam.bff.adapter.client.exam.CreateExamCommand;
import com.thoughtworks.exam.bff.adapter.client.exam.ExamDTO;
import com.thoughtworks.exam.bff.adapter.client.exam.SubmitAnswerCommand;
import com.thoughtworks.exam.bff.adapter.client.exam.SubmitAnswerDTO;
import com.thoughtworks.exam.bff.adapter.client.quiz.CreateQuizCommand;
import com.thoughtworks.exam.bff.adapter.client.exam.ExamClient;
import com.thoughtworks.exam.bff.adapter.client.quiz.QuizClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/examinations")
public class ExamController {
    private final ExamClient examClient;


    public ExamController(ExamClient examClient) {
        this.examClient = examClient;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExamDTO create(@RequestBody CreateExamCommand command) {
        return examClient.create(command);
    }

    @PostMapping("/{examId}/answer-sheets")
    @ResponseStatus(HttpStatus.CREATED)
    public AnswerSheetDTO createAnswerSheet(@PathVariable("examId")String examId) {
        return examClient.createAnswerSheet(examId);
    }

    @PutMapping("/{examinationId}/answer-sheet/{answerId}")
    @ResponseStatus(HttpStatus.OK)
    public SubmitAnswerDTO submitAnswer(@PathVariable("examinationId")String examinationId, @PathVariable("answerId")String answerId,
                                        @RequestBody SubmitAnswerCommand submitAnswerCommand){
        return examClient.submitAnswer(examinationId, answerId, submitAnswerCommand);
    }
}
