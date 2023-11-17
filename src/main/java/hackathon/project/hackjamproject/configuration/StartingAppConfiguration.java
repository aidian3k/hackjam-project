package hackathon.project.hackjamproject.configuration;

import hackathon.project.hackjamproject.domain.Localization;
import hackathon.project.hackjamproject.dto.user.UserCreationDTO;
import hackathon.project.hackjamproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class StartingAppConfiguration {

	private final UserService userService;

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
			userService.createUser(sampleUser);
		};
	}
}
