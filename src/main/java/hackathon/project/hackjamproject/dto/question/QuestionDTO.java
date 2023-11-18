package hackathon.project.hackjamproject.dto.question;

import com.azure.core.annotation.Get;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Jacksonized
public class QuestionDTO {
    private String question;
    private String language;
    private Long auctionId;
}
