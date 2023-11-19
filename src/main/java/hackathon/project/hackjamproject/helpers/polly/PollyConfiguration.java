package hackathon.project.hackjamproject.helpers.polly;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PollyConfiguration {
    @Value("${app.api.key.aws-access-key}")
    private String awsAccessKey;

    @Value("${app.api.key.aws-secret-key-access}")
    private String awsSecretAccessKey;

    @Bean
    public AmazonPolly amazonPolly() {
        return AmazonPollyClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(awsAccessKey, awsSecretAccessKey)))
                .withRegion(Regions.EU_WEST_1).build();
    }

    @Bean
    public SynthesizeSpeechRequest synthesizeSpeechRequest() {
        return new SynthesizeSpeechRequest();
    }
}
