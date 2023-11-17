package hackathon.project.hackjamproject.helpers.googlelens;

import hackathon.project.hackjamproject.entity.Media;
import hackathon.project.hackjamproject.helpers.googlelens.dtos.GoogleLensRootResponse;
import hackathon.project.hackjamproject.helpers.googlelens.dtos.KnowledgeGraph;
import hackathon.project.hackjamproject.helpers.googlelens.dtos.ShoppingResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleLensService {

	private final GoogleLensConnector googleLensConnector;
	private static final String GOOGLE_API_KEY = "UgXWw3s4tcnQPKpemrhXh3G7";

	public GoogleResponse getTitleAndAverageProductPrice(Media media) {
		GoogleLensRootResponse googleLensRootResponse = googleLensConnector.getGoogleLensResponseFromImage(
			"google_lens",
			media.getImageUrl(),
			GOOGLE_API_KEY
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
