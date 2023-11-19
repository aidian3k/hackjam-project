package hackathon.project.hackjamproject.helpers.openai;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.KeyCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfiguration {

	@Value("${app.api.key.open-ai}")
	private String openAiApiKey;

	@Bean
	public OpenAIClient configureOpenAIClient() {
		return new OpenAIClientBuilder()
			.credential(new KeyCredential(openAiApiKey))
			.buildClient();
	}
}
