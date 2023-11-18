package hackathon.project.hackjamproject.helpers.polly;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PollyConfiguration {
    private static final String AWS_ACCESS_KEY = "AKIATHQ6EE6XHWPL5KFG";
    private static final String AWS_SECRET_ACCESS_KEY =
            "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";
    @Bean
    public AmazonPolly amazonPolly() {
        return AmazonPollyClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_ACCESS_KEY)))
                .withRegion(Regions.EU_WEST_1).build();
    }

    @Bean
    public SynthesizeSpeechRequest synthesizeSpeechRequest() {
        return new SynthesizeSpeechRequest();
    }
}
