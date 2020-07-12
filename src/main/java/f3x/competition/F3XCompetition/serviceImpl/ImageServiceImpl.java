package f3x.competition.F3XCompetition.serviceImpl;


import f3x.competition.F3XCompetition.service.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl  implements ImageService {


    public ImageServiceImpl() {

    }
    @Override
    public String uploadImage(MultipartFile image) {
        return null;
    }

    @Override
    public Resource loadImage(String fileName) {
        return null;
    }

    @Override
    public void persistInDb(String imageURI) {

    }

    @Override
    public void deleteFromDB(Long imageId) {

    }
}
