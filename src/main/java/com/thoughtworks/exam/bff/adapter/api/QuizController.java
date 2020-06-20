package com.thoughtworks.exam.bff.adapter.api;

import com.thoughtworks.exam.bff.adapter.client.quiz.QuizClient;
import com.thoughtworks.exam.bff.adapter.client.quiz.CreateQuizCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quizzes")
public class QuizController {
    private final QuizClient quizClient;


    public QuizController(QuizClient quizClient) {
        this.quizClient = quizClient;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody CreateQuizCommand command) {
        return quizClient.create(command);
    }
}