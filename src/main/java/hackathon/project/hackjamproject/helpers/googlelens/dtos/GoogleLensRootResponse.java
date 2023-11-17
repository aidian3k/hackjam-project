package hackathon.project.hackjamproject.helpers.googlelens.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
@Builder
@Getter
@Setter
public class GoogleLensRootResponse {

	private List<KnowledgeGraph> knowledge_graphs;
}
