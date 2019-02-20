package study.ian.freeway.model.road;

import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("id")
    private int id;

    @SerializedName("locationid")
    private String locationId;

    @SerializedName("directionid")
    private String directionId;

    @SerializedName("end_location")
    private String endLocation;

    @SerializedName("end_milepost")
    private int endMilepost;

    @SerializedName("expresswayid")
    private String expresswayId;

    @SerializedName("freewayid")
    private String freewayId;

    @SerializedName("from_location")
    private String fromLocation;

    @SerializedName("from_milepost")
    private int fromMilepost;

    @SerializedName("lanes")
    private int lanes;

    @SerializedName("section_average_speed")
    private int sectionAverageSpeed;

    @SerializedName("travel_time")
    private int travelTime;

    @SerializedName("display_start_mile")
    private int displayStartMile;

    @SerializedName("display_end_mile")
    private int displayEndMile;

    @SerializedName("speed")
    private Integer speed;

    public int getId() {
        return id;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getDirectionId() {
        return directionId;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public int getEndMilepost() {
        return endMilepost;
    }

    public String getExpresswayId() {
        return expresswayId;
    }

    public String getFreewayId() {
        return freewayId;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public int getFromMilepost() {
        return fromMilepost;
    }

    public int getLanes() {
        return lanes;
    }

    public int getSectionAverageSpeed() {
        return sectionAverageSpeed;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public int getDisplayStartMile() {
        return displayStartMile;
    }

    public int getDisplayEndMile() {
        return displayEndMile;
    }

    public Integer getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", locationId='" + locationId + '\'' +
                ", directionId='" + directionId + '\'' +
                ", endLocation='" + endLocation + '\'' +
                ", endMilepost=" + endMilepost +
                ", expresswayId='" + expresswayId + '\'' +
                ", freewayId='" + freewayId + '\'' +
                ", fromLocation='" + fromLocation + '\'' +
                ", fromMilepost=" + fromMilepost +
                ", lanes=" + lanes +
                ", sectionAverageSpeed=" + sectionAverageSpeed +
                ", travelTime=" + travelTime +
                ", displayStartMile=" + displayStartMile +
                ", displayEndMile=" + displayEndMile +
                ", speed=" + speed +
                '}';
    }
}
