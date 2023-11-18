package hackathon.project.hackjamproject.dto.question;


import hackathon.project.hackjamproject.entity.Media;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@Builder
public class QuestionResponseDTO {
    private String translatedResponse;
    private Media media;
}
