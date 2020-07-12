package f3x.competition.F3XCompetition.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="image")
public class Image {

    @Id
    @Column(name="image_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "image_id_generator")
    @SequenceGenerator(name="image_id_generator",initialValue = 1,sequenceName = "image_id_seq")
    @JsonProperty
    private Long imageId;

    @Column(name="image_name")
    @JsonProperty
    private String imageName;

    @Column(name = "image_size")
    @JsonProperty
    private String imageSize;
    @Column(name="image_type")
    @JsonProperty
    private String imageType;

    @Column(name="image_category")
    @JsonProperty
    private String imageCategory;

    @Column(name="image_uri")
    @JsonProperty
    private String imageURI;

    @Column(name="entity_id")
    @JsonIgnore
    private Long entityId;

    public Image() {
    }

    public Image(String imageName, String imageSize, String imageType, String imageCategory, String imageURI, Long entityId) {
        this.imageName = imageName;
        this.imageSize = imageSize;
        this.imageType = imageType;
        this.imageCategory = imageCategory;
        this.imageURI = imageURI;
        this.entityId = entityId;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public String getImageCategory() {
        return imageCategory;
    }

    public void setImageCategory(String imageCategory) {
        this.imageCategory = imageCategory;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageId=" + imageId +
                ", imageName='" + imageName + '\'' +
                ", imageSize='" + imageSize + '\'' +
                ", imageType='" + imageType + '\'' +
                ", imageURI='" + imageURI + '\'' +
                '}';
    }
}
