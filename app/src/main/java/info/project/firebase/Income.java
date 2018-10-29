package info.project.firebase;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Income {

    String detail_income;
    String value_income;
    String username;

    public Income() {
    }

    public Income(String detail_income, String value_income, String username) {
        this.detail_income = detail_income;
        this.value_income = value_income;
        this.username = username;
    }

    public String getDetail_income() {
        return detail_income;
    }

    public void setDetail_income(String detail_income) {
        this.detail_income = detail_income;
    }

    public String getValue_income() {
        return value_income;
    }

    public void setValue_income(String value_income) {
        this.value_income = value_income;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
