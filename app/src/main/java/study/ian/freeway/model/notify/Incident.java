package study.ian.freeway.model.notify;

import com.google.gson.annotations.SerializedName;

public class Incident {

    @SerializedName("inc_type_name")
    private String incTypeName;

    @SerializedName("inc_name")
    private String incName;

    @SerializedName("inc_time")
    private String incTime;

    @SerializedName("incidentid")
    private String incidentId;

    @SerializedName("freewayid")
    private String freewayId;

    @SerializedName("expresswayid")
    private String expresswayiId;

    @SerializedName("directionid")
    private String directionId;

    @SerializedName("inc_location")
    private String incLocation;

    @SerializedName("from_milepost")
    private int fromMilepost;

    @SerializedName("to_milepost")
    private int toMilepost;

    @SerializedName("interchange")
    private Object interchange;

    @SerializedName("inc_blockage")
    private String incBlockage;

    @SerializedName("inc_severity")
    private String incSeverity;

    @SerializedName("inc_notify_time")
    private String incNotifyTime;

    @SerializedName("inc_notify_mode")
    private String incNotifyMode;

    @SerializedName("inc_end_time")
    private String incEndTime;

    @SerializedName("inc_stepno")
    private String incStepno;

    @SerializedName("inc_step_time")
    private String incStepTime;

    @SerializedName("area")
    private String area;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    public String getIncTypeName() {
        return incTypeName;
    }

    public String getIncName() {
        return incName;
    }

    public String getIncTime() {
        return incTime;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public String getFreewayId() {
        return freewayId;
    }

    public String getExpresswayiId() {
        return expresswayiId;
    }

    public String getDirectionId() {
        return directionId;
    }

    public String getIncLocation() {
        return incLocation;
    }

    public int getFromMilepost() {
        return fromMilepost;
    }

    public int getToMilepost() {
        return toMilepost;
    }

    public Object getInterchange() {
        return interchange;
    }

    public String getIncBlockage() {
        return incBlockage;
    }

    public String getIncSeverity() {
        return incSeverity;
    }

    public String getIncNotifyTime() {
        return incNotifyTime;
    }

    public String getIncNotifyMode() {
        return incNotifyMode;
    }

    public String getIncEndTime() {
        return incEndTime;
    }

    public String getIncStepno() {
        return incStepno;
    }

    public String getIncStepTime() {
        return incStepTime;
    }

    public String getArea() {
        return area;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "incTypeName='" + incTypeName + '\'' +
                ", incName='" + incName + '\'' +
                ", incTime='" + incTime + '\'' +
                ", incidentId='" + incidentId + '\'' +
                ", freewayId='" + freewayId + '\'' +
                ", expresswayiId='" + expresswayiId + '\'' +
                ", directionId='" + directionId + '\'' +
                ", incLocation=" + incLocation +
                ", fromMilepost=" + fromMilepost +
                ", toMilepost=" + toMilepost +
                ", interchange=" + interchange +
                ", incBlockage='" + incBlockage + '\'' +
                ", incSeverity='" + incSeverity + '\'' +
                ", incNotifyTime='" + incNotifyTime + '\'' +
                ", incNotifyMode='" + incNotifyMode + '\'' +
                ", incEndTime='" + incEndTime + '\'' +
                ", incStepno='" + incStepno + '\'' +
                ", incStepTime='" + incStepTime + '\'' +
                ", area='" + area + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
