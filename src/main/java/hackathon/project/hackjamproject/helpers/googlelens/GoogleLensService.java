package hackathon.project.hackjamproject.helpers.googlelens;

import hackathon.project.hackjamproject.entity.Media;
import hackathon.project.hackjamproject.helpers.googlelens.dtos.GoogleLensRootResponse;
import hackathon.project.hackjamproject.helpers.googlelens.dtos.KnowledgeGraph;
import hackathon.project.hackjamproject.helpers.googlelens.dtos.ShoppingResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleLensService {

	private final GoogleLensConnector googleLensConnector;

	@Value("${app.api.key.google-lens}")
	private String googleLensApiKey;

	public GoogleResponse getTitleAndAverageProductPrice(Media media) {
		GoogleLensRootResponse googleLensRootResponse = googleLensConnector.getGoogleLensResponseFromImage(
			"google_lens",
			"pl",
			media.getImageUrl(),
			googleLensApiKey
		);
		String mediaTitle = googleLensRootResponse
			.getKnowledge_graphs()
			.stream()
			.map(KnowledgeGraph::getTitle)
			.findFirst()
			.orElseThrow(() ->
				new IllegalStateException("Could not find any good title")
			);
		Long averagePrice = getAveragePriceFromKnowledgeGraph(
			googleLensRootResponse.getKnowledge_graphs()
		);

		return GoogleResponse
			.builder()
			.title(mediaTitle)
			.price(averagePrice)
			.build();
	}

	private Long getAveragePriceFromKnowledgeGraph(
		List<KnowledgeGraph> knowledgeGraphs
	) {
		if (knowledgeGraphs.get(0).getShopping_results() == null) {
			return null;
		}

		List<ShoppingResult> shoppingResults = knowledgeGraphs
			.stream()
			.flatMap(knowledgeGraph -> knowledgeGraph.getShopping_results().stream())
			.toList();

		long totalPriceSum = shoppingResults
			.stream()
			.mapToLong(ShoppingResult::getExtracted_price)
			.sum();

		return totalPriceSum / (long) shoppingResults.size();
	}
}
