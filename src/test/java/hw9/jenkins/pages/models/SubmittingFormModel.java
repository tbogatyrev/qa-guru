package hw9.jenkins.pages.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubmittingFormModel {

    private String studentName,
            studentEmail,
            gender,
            mobile,
            dateOfBirth,
            subjects,
            hobbies,
            picture,
            address,
            stateAndCity;

    public String getStudentName() {
        return studentName;
    }

    @JsonProperty("Student Name")
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    @JsonProperty("Student Email")
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getGender() {
        return gender;
    }

    @JsonProperty("Gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    @JsonProperty("Mobile")
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonProperty("Date of Birth")
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSubjects() {
        return subjects;
    }

    @JsonProperty("Subjects")
    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getHobbies() {
        return hobbies;
    }

    @JsonProperty("Hobbies")
    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getPicture() {
        return picture;
    }

    @JsonProperty("Picture")
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAddress() {
        return address;
    }

    @JsonProperty("Address")
    public void setAddress(String address) {
        this.address = address;
    }

    public String getStateAndCity() {
        return stateAndCity;
    }

    @JsonProperty("State and City")
    public void setStateAndCity(String stateAndCity) {
        this.stateAndCity = stateAndCity;
    }

    @Override
    public String toString() {
        return "SubmittingFormModel{" +
                "studentName='" + studentName + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                ", gender='" + gender + '\'' +
                ", mobile='" + mobile + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", subjects='" + subjects + '\'' +
                ", hobbies='" + hobbies + '\'' +
                ", picture='" + picture + '\'' +
                ", address='" + address + '\'' +
                ", stateAndCity='" + stateAndCity + '\'' +
                '}';
    }
}
