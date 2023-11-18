package hackathon.project.hackjamproject.service;

import hackathon.project.hackjamproject.dto.auction.AuctionCreationDTO;
import hackathon.project.hackjamproject.dto.auction.BidAuctionInfo;
import hackathon.project.hackjamproject.dto.auction.MainPageAuctionDTO;
import hackathon.project.hackjamproject.dto.auction.TimeLeft;
import hackathon.project.hackjamproject.dto.auction.UserAuctionDTO;
import hackathon.project.hackjamproject.entity.Auction;
import hackathon.project.hackjamproject.entity.Bid;
import hackathon.project.hackjamproject.entity.Media;
import hackathon.project.hackjamproject.entity.Tag;
import hackathon.project.hackjamproject.entity.User;
import hackathon.project.hackjamproject.repository.AuctionRepository;
import hackathon.project.hackjamproject.repository.MediaRepository;
import hackathon.project.hackjamproject.repository.TagRepository;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuctionService {

	private final TagService tagService;
	private final AuctionRepository auctionRepository;
	private final UserService userService;
	private final Clock clock;
	private final MediaRepository mediaRepository;
	private final TagRepository tagRepository;

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
		User user = userService.getUserById(userId);
		Auction auction = Auction
			.builder()
			.localization(auctionCreationDTO.getLocalization())
			.auctionCoreInformation(auctionCreationDTO.getAuctionCoreInformation())
			.owner(user)
			.endDate(auctionCreationDTO.getEndDate())
			.startDate(auctionCreationDTO.getStartDate())
			.build();

		Auction savedAuction = auctionRepository.save(auction);
		//Media savedMedia = mediaRepository.save(auctionCreationDTO.getMedia());
		//savedMedia.setAuction(savedAuction);
		auctionCreationDTO.getMedia().setAuction(savedAuction);
		//savedAuction.setMedia(savedMedia);
		savedAuction.setMedia(auctionCreationDTO.getMedia());
		savedAuction = auctionRepository.save(auction);
		//		List<Tag> tags = auctionCreationDTO
		//			.getTags()
		//			.stream()
		//			.map(tagService::handleCreateTag)
		//			.peek(tag -> tag.setAuction(savedAuction))
		//			.peek(Hibernate.initialize(tag);)
		//			.map(tagRepository::save)
		//			.toList();
		//savedAuction.setTags(tags);
		//Auction secondSave = auctionRepository.save(auction);

		return savedAuction;
	}

	public MainPageAuctionDTO getMainPageAuctionDTO(Long auctionId) {
		Auction auction = findAuctionById(auctionId);

		return MainPageAuctionDTO
			.builder()
			.id(auction.getId())
			.imageUrl(auction.getMedia().getImageUrl())
			.title(auction.getAuctionCoreInformation().getTitle())
			.description(auction.getAuctionCoreInformation().getDescription())
			.timeLeft(getAuctionTimeLeft(auction.getEndDate()))
			.bidAuctionInfo(getBidAuctionInfo(auction))
			.build();
	}

	private BidAuctionInfo getBidAuctionInfo(Auction auction) {
		List<Bid> bids = auction.getBids();
		int numberOfBidders = bids.size();
		int maximumNumberOfMaxBidders = 6;
		Long highestBid;

		if (bids.size() == 0) {
			highestBid = auction.getAuctionCoreInformation().getPrice();
			return BidAuctionInfo
				.builder()
				.numberOfBidders(numberOfBidders)
				.highestBid(highestBid)
				.topBiddersInfo(Collections.emptyList())
				.build();
		}

		List<UserAuctionDTO> topBidders = bids
			.stream()
			.sorted(Comparator.comparing(Bid::getBidPrice))
			.map(bid ->
				userService.getUserInfoForAuction(
					bid.getUser().getId(),
					bid.getBidPrice()
				)
			)
			.limit(maximumNumberOfMaxBidders)
			.toList();
		highestBid = topBidders.get(0).getBidPrice();

		return BidAuctionInfo
			.builder()
			.numberOfBidders(numberOfBidders)
			.highestBid(highestBid)
			.topBiddersInfo(topBidders)
			.build();
	}

	public List<MainPageAuctionDTO> getAllAuctions() {
		return auctionRepository
			.findAll()
			.stream()
			.map(auction -> getMainPageAuctionDTO(auction.getId()))
			.toList();
	}

	private TimeLeft getAuctionTimeLeft(LocalDateTime endTime) {
		Duration duration = Duration.between(LocalDateTime.now(clock), endTime);
		return TimeLeft
			.builder()
			.days(duration.toDays())
			.hours(duration.toHours() % 24)
			.minutes(duration.toMinutes() % 60)
			.seconds(duration.toSeconds() % 60)
			.build();
	}
}
