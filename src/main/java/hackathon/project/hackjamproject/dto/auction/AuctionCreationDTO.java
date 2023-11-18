package hackathon.project.hackjamproject.dto.auction;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import hackathon.project.hackjamproject.domain.Localization;
import hackathon.project.hackjamproject.dto.tag.TagCreationDTO;
import hackathon.project.hackjamproject.entity.Media;
import hackathon.project.hackjamproject.helpers.auction.AuctionCoreInformation;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Jacksonized
public class AuctionCreationDTO {

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime startDate;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime endDate;

	@Embedded
	private AuctionCoreInformation auctionCoreInformation;

	@NotNull
	private List<TagCreationDTO> tags;

	@Enumerated(EnumType.STRING)
	private Localization localization;

	@NotNull
	private Media media;
}
