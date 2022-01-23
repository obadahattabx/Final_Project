package edu.birzeit.projectpart1;

public class UserRentingAgency extends User {
    private String name;
    private String country;
    private String city;
    private String phoneNumber;


    public UserRentingAgency() {

    }
    public UserRentingAgency(int id, String email, String password, String name, String country, String city, String phoneNumber) {
        super(id, email, password);
        this.name = name;
        this.country = country;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
