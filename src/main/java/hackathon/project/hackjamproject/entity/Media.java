package hackathon.project.hackjamproject.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "medias")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Media {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medias_seq")
	@SequenceGenerator(name = "medias_seq", allocationSize = 1)
	private Long id;

	@NotNull
	private String imageUrl;

	@NotNull
	private String extension;

	@OneToOne
	private Auction auction;
}
