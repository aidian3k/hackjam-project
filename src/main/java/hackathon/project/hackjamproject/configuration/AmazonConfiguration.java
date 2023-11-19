package hackathon.project.hackjamproject.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfiguration {
	@Value("${app.api.key.aws-access-key}")
	private String awsAccessKey;

	@Value("${app.api.key.aws-secret-key-access}")
	private String awsSecretAccessKey;
	@Bean
	public AmazonS3 amazonS3Service() {
		var credentialsProvider = new BasicAWSCredentials(
			awsAccessKey,
			awsSecretAccessKey
		);

		return AmazonS3ClientBuilder
			.standard()
			.withCredentials(new AWSStaticCredentialsProvider(credentialsProvider))
			.withRegion(Regions.EU_WEST_1)
			.build();
	}
}
