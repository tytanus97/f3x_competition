package f3x.competition.F3XCompetition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import f3x.competition.F3XCompetition.entity.Image;

import java.util.List;

public class PlaneDTO {


    @JsonProperty
    private Long planeId;

    @JsonProperty
    private float planeWingSpan;

    @JsonProperty
    private String planeColor;

    @JsonProperty(defaultValue = "0")
    private float planeWeight;

    @JsonProperty
    private String planeName;

    @JsonProperty
    private List<Image> imageList;

    public PlaneDTO() {
    }

    public PlaneDTO(Long planeId, float planeWingSpan, String planeColor, float planeWeight, String planeName, List<Image> imageList) {
        this.planeId = planeId;
        this.planeWingSpan = planeWingSpan;
        this.planeColor = planeColor;
        this.planeWeight = planeWeight;
        this.planeName = planeName;
        this.imageList = imageList;
    }

    public Long getPlaneId() {
        return planeId;
    }

    public void setPlaneId(Long planeId) {
        this.planeId = planeId;
    }

    public float getPlaneWingSpan() {
        return planeWingSpan;
    }

    public void setPlaneWingSpan(float planeWingSpan) {
        this.planeWingSpan = planeWingSpan;
    }

    public String getPlaneColor() {
        return planeColor;
    }

    public void setPlaneColor(String planeColor) {
        this.planeColor = planeColor;
    }

    public float getPlaneWeight() {
        return planeWeight;
    }

    public void setPlaneWeight(float planeWeight) {
        this.planeWeight = planeWeight;
    }

    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }
}
