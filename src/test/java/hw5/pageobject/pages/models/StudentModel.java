package hw5.pageobject.pages.models;

import java.util.List;

public class StudentModel {

    private String firstName,
            lastName,
            email,
            gender,
            mobilePhone,
            picture,
            currentAddress,
            state,
            city;

    private List<String> subjects,
            hobbies;

    private BirthDate birthDate;

    public String getFirstName() {
        return firstName;
    }

    public StudentModel setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public StudentModel setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }

    public String getEmail() {
        return email;
    }

    public StudentModel setEmail(String email) {
        this.email = email;

        return this;
    }

    public String getGender() {
        return gender;
    }

    public StudentModel setGender(String gender) {
        this.gender = gender;

        return this;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public StudentModel setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;

        return this;
    }

    public BirthDate getBirthDate() {
        return birthDate;
    }

    public StudentModel setBirthDate(String day, String month, String year) {
        birthDate = new BirthDate();
        this.birthDate.setBirthDay(day);
        this.birthDate.setBirthMonth(month);
        this.birthDate.setBirthYear(year);

        return this;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public StudentModel setSubjects(List<String> subjects) {
        this.subjects = subjects;

        return this;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public StudentModel setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;

        return this;
    }

    public String getPicture() {
        return picture;
    }

    public StudentModel setPicture(String picture) {
        this.picture = picture;

        return this;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public StudentModel setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;

        return this;
    }

    public String getState() {
        return state;
    }

    public StudentModel setState(String state) {
        this.state = state;

        return this;
    }

    public String getCity() {
        return city;
    }

    public StudentModel setCity(String city) {
        this.city = city;

        return this;
    }
}
