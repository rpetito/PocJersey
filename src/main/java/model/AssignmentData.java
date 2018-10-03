package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rodrigo on 30/09/18.
 */
public class AssignmentData {

    @SerializedName("assignments")
    private List<AssignmentItem> assignments;

    public List<AssignmentItem> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<AssignmentItem> assignments) {
        this.assignments = assignments;
    }

}
