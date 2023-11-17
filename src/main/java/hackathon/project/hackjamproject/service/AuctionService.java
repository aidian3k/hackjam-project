package hackathon.project.hackjamproject.service;

import hackathon.project.hackjamproject.dto.auction.AuctionCreationDTO;
import hackathon.project.hackjamproject.entity.Auction;
import hackathon.project.hackjamproject.entity.Media;
import hackathon.project.hackjamproject.entity.Tag;
import hackathon.project.hackjamproject.entity.User;
import hackathon.project.hackjamproject.helpers.googlelens.GoogleLensService;
import hackathon.project.hackjamproject.helpers.googlelens.GoogleResponse;
import hackathon.project.hackjamproject.helpers.openai.OpenAiService;
import hackathon.project.hackjamproject.helpers.openai.dtos.ArtificialIntelligenceResponse;
import hackathon.project.hackjamproject.repository.AuctionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuctionService {

	private final TagService tagService;
	private final AuctionRepository auctionRepository;
	private final UserService userService;
	private final ImageService imageService;
	private final GoogleLensService googleLensService;
	private final OpenAiService openAiService;

	public Auction findAuctionById(Long auctionId) {
		return auctionRepository
			.findById(auctionId)
			.orElseThrow(() ->
				new IllegalStateException("Auction with this id could not be found!")
			);
	}

	public Auction handleCreateAuction(
		AuctionCreationDTO auctionCreationDTO,
		Long userId
	) {
		List<Tag> tags = auctionCreationDTO
			.getTags()
			.stream()
			.map(tagService::handleCreateTag)
			.toList();
		User user = userService.getUserById(userId);

		Auction auction = Auction
			.builder()
			.localization(auctionCreationDTO.getLocalization())
			.auctionCoreInformation(auctionCreationDTO.getAuctionCoreInformation())
			.owner(user)
			.endDate(auctionCreationDTO.getEndDate())
			.startDate(auctionCreationDTO.getStartDate())
			.media(auctionCreationDTO.getMedia())
			.tags(tags)
			.build();

		auctionRepository.save(auction);
		user.getAuctions().add(auction);
		userService.updateCurrentUser(user);

		return auction;
	}

	public ArtificialIntelligenceResponse getAuctionCoreInformationUsingPhoto(
		Media media
	) {
		GoogleResponse googleResponse = googleLensService.getTitleAndAverageProductPrice(
			media
		);
		return openAiService.getAuctionCoreInformation(googleResponse);
	}
}
