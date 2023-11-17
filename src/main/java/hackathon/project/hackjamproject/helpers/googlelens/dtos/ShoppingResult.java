package hackathon.project.hackjamproject.helpers.googlelens.dtos;

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
public class ShoppingResult {

	private String source;
	private String link;
	private String price;
	private int extracted_price;
	private String snippet;
	private String source_favicon;
}
