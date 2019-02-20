package study.ian.freeway.model.notify;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Notify {

    @SerializedName("status")
    private boolean status;

    @SerializedName("action")
    private String action;

    @SerializedName("freewayid")
    private String freewayId;

    @SerializedName("expresswayid")
    private String expresswayId;

    @SerializedName("from_milepost")
    private String fromMilepost;

    @SerializedName("end_milepost")
    private String endMilepost;

    @SerializedName("breakupby")
    private String section;

    @SerializedName("response")
    private Response response;

    public boolean isStatus() {
        return status;
    }

    public String getAction() {
        return action;
    }

    public String getFreewayId() {
        return freewayId;
    }

    public String getExpresswayId() {
        return expresswayId;
    }

    public String getFromMilepost() {
        return fromMilepost;
    }

    public String getEndMilepost() {
        return endMilepost;
    }

    public String getSection() {
        return section;
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "Notify{" +
                "status=" + status +
                ", action='" + action + '\'' +
                ", freewayId='" + freewayId + '\'' +
                ", expresswayId='" + expresswayId + '\'' +
                ", fromMilepost='" + fromMilepost + '\'' +
                ", endMilepost='" + endMilepost + '\'' +
                ", section='" + section + '\'' +
                ", response=" + response +
                '}';
    }
}
