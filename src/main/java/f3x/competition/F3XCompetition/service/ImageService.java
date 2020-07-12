package f3x.competition.F3XCompetition.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(MultipartFile image);
    Resource loadImage(String fileName);
    void persistInDb(String imageURI);
    void deleteFromDB(Long imageId);
}
