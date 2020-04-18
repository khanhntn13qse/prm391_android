package ntnk.sample.assignmentprm;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private String name;
    private boolean gender;
    private String email;
    private String phone;
    private String address;

    public UserInfo() {
    }

    public UserInfo(String name, boolean gender, String email, String phone, String address) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
