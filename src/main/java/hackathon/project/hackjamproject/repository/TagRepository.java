package hackathon.project.hackjamproject.repository;

import hackathon.project.hackjamproject.entity.Tag;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
	Optional<Tag> findTagByName(String name);
}
