package hackathon.project.hackjamproject.helpers.googlelens.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeGraph {

	private String title;
	private String subtitle;
	private String link;
	private List<Image> images;
	private List<ShoppingResult> shopping_results;
}
