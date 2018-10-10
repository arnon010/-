package info.androidhive.firebase;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Register {

    public String name;
    public String surname;
    public String email;
    public String username;
    public String password;
    public String detail_income;
    public String detail_outcome;
    public String value_income;
    public String value_outcome;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Register() {
    }

    public Register(String name, String surname, String email, String username, String password, String detail_income, String detail_outcome, String value_income, String value_outcome) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.detail_income = detail_income;
        this.detail_outcome = detail_outcome;
        this.value_income = value_income;
        this.value_outcome = value_outcome;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDetail_income() {
        return detail_income;
    }

    public void setDetail_income(String detail_income) {
        this.detail_income = detail_income;
    }

    public String getDetail_outcome() {
        return detail_outcome;
    }

    public void setDetail_outcome(String detail_outcome) {
        this.detail_outcome = detail_outcome;
    }

    public String getValue_income() {
        return value_income;
    }

    public void setValue_income(String value_income) {
        this.value_income = value_income;
    }

    public String getValue_outcome() {
        return value_outcome;
    }

    public void setValue_outcome(String value_outcome) {
        this.value_outcome = value_outcome;
    }
}
