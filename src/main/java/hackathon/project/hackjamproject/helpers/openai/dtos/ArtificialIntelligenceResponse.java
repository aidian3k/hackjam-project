package hackathon.project.hackjamproject.helpers.openai.dtos;

import hackathon.project.hackjamproject.helpers.auction.AuctionCoreInformation;
import java.util.List;
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
@Builder
@Jacksonized
public class ArtificialIntelligenceResponse {

	private AuctionCoreInformation auctionCoreInformation;
	private List<String> tagNames;
}
