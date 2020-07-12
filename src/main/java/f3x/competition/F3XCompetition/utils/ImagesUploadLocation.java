package f3x.competition.F3XCompetition.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="file")
public class ImagesUploadLocation {
    private String uploadDir;

    public ImagesUploadLocation(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
