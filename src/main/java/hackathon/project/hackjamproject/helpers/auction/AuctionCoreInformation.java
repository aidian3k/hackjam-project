package hackathon.project.hackjamproject.helpers.auction;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Embeddable
public class AuctionCoreInformation {

	@Size(max = 1024)
	@Column(length = 1024)
	@NotNull
	private String title;

	@Size(max = 1024)
	@Column(length = 1024)
	@NotNull
	private String description;

	@NotNull
	@Positive
	private Long price;
}
