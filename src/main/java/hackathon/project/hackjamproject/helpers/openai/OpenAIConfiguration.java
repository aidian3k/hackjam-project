package hackathon.project.hackjamproject.helpers.openai;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.KeyCredential;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfiguration {

	private static final String OPEN_AI_API_KEY =
		"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";

	@Bean
	public OpenAIClient configureOpenAIClient() {
		return new OpenAIClientBuilder()
			.credential(new KeyCredential(OPEN_AI_API_KEY))
			.buildClient();
	}
}
