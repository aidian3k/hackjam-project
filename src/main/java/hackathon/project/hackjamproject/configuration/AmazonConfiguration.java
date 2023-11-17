package hackathon.project.hackjamproject.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfiguration {

	private static final String AWS_ACCESS_KEY = "AKIATHQ6EE6XHWPL5KFG";
	private static final String AWS_SECRET_ACCESS_KEY =
		"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";

	@Bean
	public AmazonS3 amazonS3Service() {
		var credentialsProvider = new BasicAWSCredentials(
			AWS_ACCESS_KEY,
			AWS_SECRET_ACCESS_KEY
		);

		return AmazonS3ClientBuilder
			.standard()
			.withCredentials(new AWSStaticCredentialsProvider(credentialsProvider))
			.withRegion(Regions.EU_WEST_1)
			.build();
	}
}
