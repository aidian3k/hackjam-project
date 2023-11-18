package hackathon.project.hackjamproject.controller;

import hackathon.project.hackjamproject.dto.auction.AuctionCreationDTO;
import hackathon.project.hackjamproject.dto.auction.MainPageAuctionDTO;
import hackathon.project.hackjamproject.entity.Auction;
import hackathon.project.hackjamproject.entity.Media;
import hackathon.project.hackjamproject.helpers.openai.OpenAiService;
import hackathon.project.hackjamproject.helpers.openai.dtos.ArtificialIntelligenceResponse;
import hackathon.project.hackjamproject.service.AuctionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
class AuctionController {

	private final AuctionService auctionService;
	private final OpenAiService openAiService;

	@GetMapping("/auction/{id}")
	public MainPageAuctionDTO getAuctionById(@PathVariable Long id) {
		return auctionService.getMainPageAuctionDTO(id);
	}

	@PostMapping("/auction/{userId}")
	public Auction create(
		@RequestBody AuctionCreationDTO auction,
		@PathVariable Long userId
	) {
		return auctionService.handleCreateAuction(auction, userId);
	}

	@PostMapping("/auction/ai")
	public ArtificialIntelligenceResponse gArtificialIntelligenceResponse(
			@RequestBody Media media
	) {
		return openAiService.getAuctionCoreInformationUsingPhoto(media);
	}

	@GetMapping("/auctions")
	public List<MainPageAuctionDTO> getAllAuctions() {
		return auctionService.getAllAuctions();
	}
}
