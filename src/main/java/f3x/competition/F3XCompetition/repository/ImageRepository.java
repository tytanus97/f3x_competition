package f3x.competition.F3XCompetition.repository;

import f3x.competition.F3XCompetition.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image,Long> {

    @Query("SELECT i FROM Image i WHERE i.entityId = ?1 AND i.entityType = ?2")
    Optional<List<Image>> findAllByEntityIdAndEntityType(Long entityId,String entityType);


}
