package hackathon.project.hackjamproject.dto.auction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeLeft {
    private long days;
    private long hours;
    private long minutes;
    private long seconds;
}
