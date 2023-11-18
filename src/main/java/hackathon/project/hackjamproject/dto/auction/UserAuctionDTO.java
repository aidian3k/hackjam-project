package hackathon.project.hackjamproject.dto.auction;

import com.azure.core.annotation.Get;
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
public class UserAuctionDTO {
    private String name;
    private String surname;
    private Long bidPrice;
}
