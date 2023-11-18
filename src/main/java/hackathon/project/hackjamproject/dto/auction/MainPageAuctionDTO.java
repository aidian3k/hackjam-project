package hackathon.project.hackjamproject.dto.auction;


import com.azure.core.annotation.Get;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainPageAuctionDTO {
    private String imageUrl;
    private String title;
    private String description;
    private TimeLeft timeLeft;
    private int biggestBid;
    private BidAuctionInfo bidAuctionInfo;
}
