package study.ian.freeway.model.notify;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("incident")
    private List<Incident> incident;

    public List<Incident> getIncident() {
        return incident;
    }

    @Override
    public String toString() {
        return "Response{" +
                "incident=" + incident +
                '}';
    }
}
