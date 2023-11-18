package hackathon.project.hackjamproject.configuration;

import hackathon.project.hackjamproject.domain.Localization;
import hackathon.project.hackjamproject.dto.auction.AuctionCreationDTO;
import hackathon.project.hackjamproject.dto.tag.TagCreationDTO;
import hackathon.project.hackjamproject.dto.user.UserCreationDTO;
import hackathon.project.hackjamproject.entity.Media;
import hackathon.project.hackjamproject.entity.User;
import hackathon.project.hackjamproject.helpers.auction.AuctionCoreInformation;
import hackathon.project.hackjamproject.service.AuctionService;
import hackathon.project.hackjamproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
class StartingAppConfiguration {

	private final UserService userService;
	private final AuctionService auctionService;

	@Bean
	public CommandLineRunner configureInitialApp() {
		return args -> {
			UserCreationDTO sampleUser = UserCreationDTO
				.builder()
				.name("Adrian")
				.surname("Nowosielski")
				.localization(Localization.PL)
				.email("adrian@wp.pl")
				.password("some-passwd")
				.build();
			User user = userService.createUser(sampleUser);
			auctionService.handleCreateAuction(createSampleAuction(), user.getId());
		};
	}

	private static AuctionCreationDTO createSampleAuction() {
		AuctionCoreInformation auctionCoreInformation = AuctionCoreInformation.builder()
				.title("Apple Iphone 12")
				.description("Apple iphone 12 is some things")
				.price(120L)
				.build();

		// Create sample tags (assuming you have a TagCreationDTO class)
		List<TagCreationDTO> tags = new ArrayList<>();
		TagCreationDTO tag1 = TagCreationDTO.builder()
				.name("some")
				.build();
		tags.add(tag1);

		TagCreationDTO tag2 = TagCreationDTO.builder()
				.name("some")
				.build();
		tags.add(tag2);

		Media media = Media.builder()
				.imageUrl("some")
				.extension("jpg")
				.build();

		return AuctionCreationDTO.builder()
				.startDate(LocalDateTime.now())
				.endDate(LocalDateTime.now().plusDays(7))
				.auctionCoreInformation(auctionCoreInformation)
				.tags(tags)
				.localization(Localization.PL)
				.media(media)
				.build();
	}
}
