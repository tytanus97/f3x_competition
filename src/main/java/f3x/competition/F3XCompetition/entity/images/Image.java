package f3x.competition.F3XCompetition.entity.images;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="images")
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

    @Column(name="image_size")
    @JsonProperty
    private Long imageSize;

    @Column(name="image_type")
    private String imageType;

    @Column(name="image_uri")
    @JsonProperty
    private String imageURI;

    @Column(name="entity_id")
    @JsonIgnore
    private Long entityId;

    @Column(name="entity_type")
    @JsonIgnore
    private String entityType;

    public Image() {
    }
    public Image(String imageName, Long imageSize, String imageType, String imageURI) {
        this.imageName = imageName;
        this.imageSize = imageSize;
        this.imageType = imageType;
        this.imageURI = imageURI;
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

    public Long getImageSize() {
        return imageSize;
    }

    public void setImageSize(Long imageSize) {
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

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
}

