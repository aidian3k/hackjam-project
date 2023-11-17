package hackathon.project.hackjamproject.repository;

import hackathon.project.hackjamproject.entity.Media;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface MediaRepository extends JpaRepository<Media, Long> {}
