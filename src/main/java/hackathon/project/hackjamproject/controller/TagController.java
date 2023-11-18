package hackathon.project.hackjamproject.controller;
import hackathon.project.hackjamproject.service.TagService;

import hackathon.project.hackjamproject.dto.tag.TagCreationDTO;
import hackathon.project.hackjamproject.entity.Tag;
import hackathon.project.hackjamproject.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/")
class TagController {
    private final TagService tagService;

    @PostMapping("/tag/")
    Tag createTag(@RequestBody TagCreationDTO tagCreationDTO) {
        return tagService.handleCreateTag(tagCreationDTO);
    }

    @GetMapping("/tag/{name}")
    Tag getByName(@PathVariable String name) {
        return tagService.findTagByName(name);
    }

}
