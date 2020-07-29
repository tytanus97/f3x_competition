package f3x.competition.F3XCompetition.service;

import f3x.competition.F3XCompetition.entity.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ImageService {
    String uploadImage(MultipartFile image,String context);
    Resource loadImage(String fileName,String context);
    void delete(Long imageId);
    Image save(Image image);
    Optional<List<Image>> findByEntityIdAndEntityType(Long entityId, String entityType);
}
