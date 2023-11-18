package hackathon.project.hackjamproject.controller;

import hackathon.project.hackjamproject.dto.question.QuestionDTO;
import hackathon.project.hackjamproject.dto.question.QuestionResponseDTO;
import hackathon.project.hackjamproject.helpers.openai.OpenAiService;
import hackathon.project.hackjamproject.helpers.polly.PollyService;
import hackathon.project.hackjamproject.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
class QuestionController {
    private final QuestionService questionService;

    @PostMapping("/question")
    public QuestionResponseDTO handleAudioQuestion(@RequestBody QuestionDTO questionDTO) throws IOException {
        return questionService.handleQuestionResponse(questionDTO);
    }
}
