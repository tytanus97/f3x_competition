package f3x.competition.F3XCompetition.repository;

import f3x.competition.F3XCompetition.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image,Long> {
    Optional<List<Image>> findAllByEntityIdAndImageCategory(Long entityId, String imageCategory);

}
