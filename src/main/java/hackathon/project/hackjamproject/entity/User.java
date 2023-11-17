package hackathon.project.hackjamproject.entity;

import hackathon.project.hackjamproject.domain.Localization;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
	@SequenceGenerator(allocationSize = 1, name = "users_seq")
	private Long id;

	@NotNull
	@Email
	private String email;

	@NotNull
	@Size(max = 255)
	private String name;

	@NotNull
	@Size(max = 255)
	private String surname;

	@NotNull
	@Size(max = 255)
	private String password;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Localization localization;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	@Builder.Default
	private List<Auction> auctions = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@Builder.Default
	private List<Bid> bids = new ArrayList<>();
}
