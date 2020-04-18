package ntnk.sample.fileioexample;

import java.io.Serializable;

public class Student implements Serializable {
    private String roll;
    private String name;
    private String birthdate;
    private String email;
    private String phone;
    private String address;

    public Student() {
    }

    public Student(String roll, String name, String birthdate, String email, String phone, String address) {
        this.roll = roll;
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
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
