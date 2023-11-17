package hackathon.project.hackjamproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "bids")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Bid {

	@Id
	@GeneratedValue(generator = "bids_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "bids_seq", allocationSize = 1)
	private Long id;

	@NotNull
	@Positive
	private Long bidPrice;

	@NotNull
	private String currency;

	@ManyToOne
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private User user;

	@ManyToOne
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Auction auction;
}
