package hackathon.project.hackjamproject.dto.auction;

import hackathon.project.hackjamproject.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BidAuctionInfo {
    private Long highestBid;
    private long numberOfBidders;
    private List<UserAuctionDTO> topBiddersInfo;
}
