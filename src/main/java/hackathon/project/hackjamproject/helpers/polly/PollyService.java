package hackathon.project.hackjamproject.helpers.polly;

import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.model.LanguageCode;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import hackathon.project.hackjamproject.dto.question.QuestionDTO;
import hackathon.project.hackjamproject.dto.question.QuestionResponseDTO;
import hackathon.project.hackjamproject.entity.Media;
import hackathon.project.hackjamproject.helpers.openai.dtos.QuestionAIResponse;
import hackathon.project.hackjamproject.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class PollyService {
    private final AmazonPolly amazonPolly;
    private final SynthesizeSpeechRequest synthesizeSpeechRequest;
    private final AmazonS3 amazonS3;
    private final MediaRepository mediaRepository;

    public QuestionResponseDTO synthesizeFromStringAndPlay(QuestionAIResponse questionAIResponse, QuestionDTO questionDTO) throws IOException {
        LanguageCode languageCode = getLanguageCode(questionDTO.getLanguage());

        SynthesizeSpeechRequest syn = synthesizeSpeechRequest.withText(questionAIResponse.getTranslatedText()).withLanguageCode(languageCode)
                .withVoiceId(getVoiceIdForLanguage(questionDTO.getLanguage()))
                .withOutputFormat(OutputFormat.Mp3);

        SynthesizeSpeechResult synthesizeSpeech = amazonPolly.synthesizeSpeech(syn);
        InputStream inputStream = synthesizeSpeech.getAudioStream();

        String bucketName = "aidian3k-bucket-test";
        String objectKey = "file.mp3";

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("audio/mp3");

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectKey, inputStream, metadata);
        amazonS3.putObject(putObjectRequest);

        inputStream.close();

        Media savedMedia = Media
                .builder()
                .imageUrl("https://aidian3k-bucket-test.s3.amazonaws.com/" + objectKey)
                .extension("mp3")
                .build();
        mediaRepository.save(savedMedia);

        return QuestionResponseDTO
                .builder()
                .translatedResponse(questionAIResponse.getTranslatedText())
                .media(savedMedia)
                .build();
    }

    private LanguageCode getLanguageCode(String language) {
        return switch (language) {
            case "english" -> LanguageCode.EnUS;
            case "polish" -> LanguageCode.PlPL;
            case "spanish" -> LanguageCode.EsES;
            case "arabic" -> LanguageCode.ArAE;
            default -> throw new IllegalArgumentException("Unsupported language: " + language);
        };
    }

    private static String getVoiceIdForLanguage(String language) {
        return switch (language) {
            case "english" -> "Joanna";
            case "polish" -> "Ewa";
            case "spanish" -> "Penelope";
            case "arabic" -> "Zeina";
            default -> throw new IllegalArgumentException("Unsupported language: " + language);
        };
    }

}
