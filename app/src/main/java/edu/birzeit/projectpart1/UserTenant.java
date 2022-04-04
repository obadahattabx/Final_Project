package edu.birzeit.projectpart1;

import android.os.Parcel;
import android.os.Parcelable;

public class UserTenant extends User implements Parcelable {
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

    protected UserTenant(Parcel in) {
        FirstName = in.readString();
        LastName = in.readString();
        gender = in.readString();
        nationality = in.readString();
        Salary = in.readString();
        occuptaion = in.readString();
        familySize = in.readString();
        country = in.readString();
        city = in.readString();
        phoneNumber = in.readString();
    }

    public static final Creator<UserTenant> CREATOR = new Creator<UserTenant>() {
        @Override
        public UserTenant createFromParcel(Parcel in) {
            return new UserTenant(in);
        }

        @Override
        public UserTenant[] newArray(int size) {
            return new UserTenant[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(FirstName);
        parcel.writeString(LastName);
        parcel.writeString(gender);
        parcel.writeString(nationality);
        parcel.writeString(Salary);
        parcel.writeString(occuptaion);
        parcel.writeString(familySize);
        parcel.writeString(country);
        parcel.writeString(city);
        parcel.writeString(phoneNumber);
    }
}
