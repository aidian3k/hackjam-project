package hackathon.project.hackjamproject.dto.user;

import hackathon.project.hackjamproject.domain.Localization;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class UserCreationDTO {

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
}
