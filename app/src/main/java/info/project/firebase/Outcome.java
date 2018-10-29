package info.project.firebase;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Outcome {

    String detail_outcome;
    String value_outcome;
    String username;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Outcome() {
    }

    public Outcome(String detail_outcome, String value_outcome, String username) {
        this.detail_outcome = detail_outcome;
        this.value_outcome = value_outcome;
        this.username = username;
    }

    public String getDetail_outcome() {
        return detail_outcome;
    }

    public void setDetail_outcome(String detail_outcome) {
        this.detail_outcome = detail_outcome;
    }

    public String getValue_outcome() {
        return value_outcome;
    }

    public void setValue_outcome(String value_outcome) {
        this.value_outcome = value_outcome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
