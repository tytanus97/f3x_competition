package f3x.competition.F3XCompetition.serviceImpl;


import f3x.competition.F3XCompetition.entity.Image;
import f3x.competition.F3XCompetition.exceptions.FileNotFoundException;
import f3x.competition.F3XCompetition.exceptions.FileStorageException;
import f3x.competition.F3XCompetition.repository.ImageRepository;
import f3x.competition.F3XCompetition.service.ImageService;
import f3x.competition.F3XCompetition.utils.ImagesUploadLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl  implements ImageService {

    private final Path fileStorageLocation;
    private final ImagesUploadLocation imagesUploadLocation;
    private final ImageRepository imageRepository;


    @Autowired
    public ImageServiceImpl(ImagesUploadLocation imagesUploadLocation, ImageRepository imageRepository) {
        this.imagesUploadLocation = imagesUploadLocation;
        this.imageRepository = imageRepository;

        this.fileStorageLocation = Paths.get(this.imagesUploadLocation.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            System.out.println(this.fileStorageLocation);
            Files.createDirectories(this.fileStorageLocation);
        } catch(Exception exc) {
            throw new FileStorageException("Could not make directory");
        }

    }
    @Override
    public String uploadImage(MultipartFile image,String context)  {

        String fileName = StringUtils.cleanPath(image.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("File name is incorrect");
            }
            Path targetLocation = this.fileStorageLocation.resolve(context + "/" + fileName);
            Files.createDirectories(targetLocation);
            Files.copy(image.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }catch(IOException exc) {
          throw new FileStorageException("Could not store file");
        }
    }

    @Override
    public Resource loadImage(String fileName,String context) {
        try {
            Path filePath = this.fileStorageLocation.resolve(context + "/" + fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if(resource.exists()) return resource;
            else throw new FileNotFoundException("File doesn't exist");
        } catch(MalformedURLException exc) {
            throw new FileNotFoundException("Could not find file: "+ fileName);
        }
    }

    @Override
    public void delete(Long imageId)
    {
        this.imageRepository.deleteById(imageId);
    }

    @Override
    public Image save(Image image) {
        return this.imageRepository.save(image);
    }

    @Override
    public Optional<List<Image>> findByEntityIdAndEntityType(Long entityId, String entityType) {
        return imageRepository.findAllByEntityIdAndEntityType(entityId,entityType);
    }
}
