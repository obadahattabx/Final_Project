package edu.birzeit.projectpart1;

public class UserTenant extends User {
    private String FirstName;
    private String LastName;
    private String gender;
    private String nationality;
    private String Salary;
    private String occuptaion;
    private String familySize;
    private String country;
    private String city;
    private String phoneNumber;

    public UserTenant() {

    }

    public UserTenant(int id, String email, String password, String firstName, String lastName, String gender, String nationality, String salary, String occuptaion, String familySize, String country, String city, String phoneNumber) {
        super(id, email, password);
        FirstName = firstName;
        LastName = lastName;
        this.gender = gender;
        this.nationality = nationality;
        Salary = salary;
        this.occuptaion = occuptaion;
        this.familySize = familySize;
        this.country = country;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getOccuptaion() {
        return occuptaion;
    }

    public void setOccuptaion(String occuptaion) {
        this.occuptaion = occuptaion;
    }

    public String getFamilySize() {
        return familySize;
    }

    public void setFamilySize(String familySize) {
        this.familySize = familySize;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
