package hackathon.project.hackjamproject.service;

import hackathon.project.hackjamproject.dto.tag.TagCreationDTO;
import hackathon.project.hackjamproject.entity.Tag;
import hackathon.project.hackjamproject.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagService {

	private final TagRepository tagRepository;

	public Tag handleCreateTag(TagCreationDTO tagCreationDTO) {
		Tag creationTag = Tag.builder().name(tagCreationDTO.getName()).build();

		tagRepository.save(creationTag);

		return creationTag;
	}

	public Tag findTagByName(String name) {
		return tagRepository
			.findTagByName(name)
			.orElseThrow(() ->
				new IllegalStateException("Tag with this id could not be found!")
			);
	}
}
