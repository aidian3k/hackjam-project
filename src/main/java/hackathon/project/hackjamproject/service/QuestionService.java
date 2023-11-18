package hackathon.project.hackjamproject.service;

import hackathon.project.hackjamproject.dto.question.QuestionDTO;
import hackathon.project.hackjamproject.dto.question.QuestionResponseDTO;
import hackathon.project.hackjamproject.helpers.openai.OpenAiService;
import hackathon.project.hackjamproject.helpers.openai.dtos.QuestionAIResponse;
import hackathon.project.hackjamproject.helpers.polly.PollyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {
    private final OpenAiService openAiService;
    private final PollyService pollyService;

    public QuestionResponseDTO handleQuestionResponse(QuestionDTO questionDTO) throws IOException {
        QuestionAIResponse questionAIResponse = openAiService.getTranslatedResponse(questionDTO, questionDTO.getAuctionId());
        return pollyService.synthesizeFromStringAndPlay(questionAIResponse, questionDTO);
    }
}
