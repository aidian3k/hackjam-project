package hackathon.project.hackjamproject.configuration;

import java.time.Clock;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class ClockConfiguration {

	@Bean
	public Clock handleConfigureClock() {
		Clock clock = Clock.systemUTC();
		log.debug("Current time with clock is: [{}]", LocalDateTime.now(clock));

		return clock;
	}
}
