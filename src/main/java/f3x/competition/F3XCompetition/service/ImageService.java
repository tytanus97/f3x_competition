package f3x.competition.F3XCompetition.service;

import f3x.competition.F3XCompetition.entity.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(MultipartFile image,String context);
    Resource loadImage(String fileName,String context);
    void delete(Long imageId);
    Image save(Image image);
}
