package hackathon.project.hackjamproject.helpers.googlelens;

import hackathon.project.hackjamproject.helpers.googlelens.dtos.GoogleLensRootResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
	name = "google-lens-connector",
	url = "https://www.searchapi.io/api/v1/search"
)
public interface GoogleLensConnector {
	@GetMapping
	GoogleLensRootResponse getGoogleLensResponseFromImage(
		@RequestParam("engine") String engine,
		@RequestParam("url") String imageUrl,
		@RequestParam("api_key") String apiKey
	);
}
