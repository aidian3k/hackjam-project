package hackathon.project.hackjamproject.helpers.openai;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.models.ChatChoice;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatMessage;
import com.azure.ai.openai.models.ChatRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import hackathon.project.hackjamproject.helpers.auction.AuctionCoreInformation;
import hackathon.project.hackjamproject.helpers.googlelens.GoogleResponse;
import hackathon.project.hackjamproject.helpers.openai.dtos.ArtificialIntelligenceResponse;
import hackathon.project.hackjamproject.helpers.openai.dtos.OpenAiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenAiService {

	private final OpenAIClient openAIClient;
	private final ObjectMapper objectMapper;

	@SneakyThrows
	public ArtificialIntelligenceResponse getAuctionCoreInformation(
		GoogleResponse googleResponse
	) {
		List<ChatMessage> chatMessages = List.of(
			new ChatMessage(
				ChatRole.SYSTEM,
				String.format(MESSAGE_TO_CHAT, googleResponse.getTitle())
			)
		);
		ChatChoice chatChoice = openAIClient
			.getChatCompletions(
				"gpt-3.5-turbo",
				new ChatCompletionsOptions(chatMessages)
			)
			.getChoices()
			.stream()
			.findAny()
			.orElseThrow(() ->
				new IllegalStateException("Something went wrong with open-ai")
			);

		String openAiContent = chatChoice.getMessage().getContent();
		OpenAiResponse openAiResponse = objectMapper.readValue(
			openAiContent,
			OpenAiResponse.class
		);

		return ArtificialIntelligenceResponse
			.builder()
			.auctionCoreInformation(
				AuctionCoreInformation
					.builder()
						.title(googleResponse.getTitle())
					.description(openAiResponse.getDescription())
					.price(googleResponse.getPrice())
					.build()
			)
			.tagNames(openAiResponse.getTagNames())
			.build();
	}

	private static final String MESSAGE_TO_CHAT =
		"Generate a json containnig string description and string[] tags from given name of product to be put on auction. Description should be shorter than 51 words, it should  describe only most important features of the product and contain information that will be true for all kinds of products that can be sold under this name. Tags list should contain from 0 to 5 tags describing broad categories like \"education\", \"fashion\",  and so on. Its okay to create short description and tag list if title does not provide enough data. I want the response to be only the json and it should meet this json: {tagNames: List<string>, description: string}. The product names is: %s - Only json please";
}
