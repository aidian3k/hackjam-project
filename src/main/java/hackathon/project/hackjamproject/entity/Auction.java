package hackathon.project.hackjamproject.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import hackathon.project.hackjamproject.domain.Localization;
import hackathon.project.hackjamproject.helpers.auction.AuctionCoreInformation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "auctions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Auction {

	@Id
	@GeneratedValue(
		generator = "auctions_seq",
		strategy = GenerationType.SEQUENCE
	)
	@SequenceGenerator(allocationSize = 1, name = "auctions_seq")
	private Long id;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime startDate;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime endDate;

	@Enumerated(EnumType.STRING)
	private Localization localization;

	@ManyToOne(fetch = FetchType.LAZY)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private User owner;

	@Embedded
	@NotNull
	private AuctionCoreInformation auctionCoreInformation;

	@OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Builder.Default
	private List<Tag> tags = new ArrayList<>();

	@OneToMany(mappedBy = "auction")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Builder.Default
	private List<Bid> bids = new ArrayList<>();

	@OneToOne(mappedBy = "auction")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Media media;
}
