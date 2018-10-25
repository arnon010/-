package info.androidhive.firebase;

public class IncomeOutcome {

    String detail_income;
    String value_income;
    String username;
    String detail_outcome;
    String value_outcome;


    public IncomeOutcome(){

    }

    public IncomeOutcome(String detail_income, String value_income, String username, String detail_outcome, String value_outcome) {
        this.detail_income = detail_income;
        this.value_income = value_income;
        this.username = username;
        this.detail_outcome = detail_outcome;
        this.value_outcome = value_outcome;
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
}
