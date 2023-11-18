package hackathon.project.hackjamproject.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import hackathon.project.hackjamproject.entity.Media;
import hackathon.project.hackjamproject.repository.MediaRepository;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {

	private final AmazonS3 amazonS3;
	private final MediaRepository mediaRepository;

	@SneakyThrows
	public Media uploadImage(MultipartFile multipartFile) {
		File convertedImage = convertMultiPartFileToFile(multipartFile);
		String fileExtension = getFileExtension(multipartFile);

		amazonS3.putObject(
			new PutObjectRequest(
				"aidian3k-bucket-test",
				multipartFile.getOriginalFilename(),
				convertedImage
			)
		);

		return mediaRepository.save(
			Media
				.builder()
				.imageUrl("https://aidian3k-bucket-test.s3.amazonaws.com/" + multipartFile.getOriginalFilename())
				.extension(fileExtension)
				.build()
		);
	}

	@SneakyThrows
	public byte[] getFileFromS3Bucket(String fileName) {
		S3Object s3Object = amazonS3.getObject(
			new GetObjectRequest("aidian3k-bucket-test", fileName)
		);
		return s3Object.getObjectContent().readAllBytes();
	}

	private File convertMultiPartFileToFile(MultipartFile multipartFile) {
		File file = new File(
			Objects.requireNonNull(multipartFile.getOriginalFilename())
		);

		try (var fos = new FileOutputStream(file)) {
			fos.write(multipartFile.getBytes());
		} catch (IOException e) {
			throw new IllegalStateException(
				"Something happened when trying to upload photo"
			);
		}

		return file;
	}

	private String getFileExtension(MultipartFile file) {
		String originalFilename = file.getOriginalFilename();
		if (originalFilename != null) {
			int lastDotIndex = originalFilename.lastIndexOf(".");
			if (lastDotIndex != -1) {
				return originalFilename.substring(lastDotIndex + 1);
			}
		}
		return null;
	}
}
