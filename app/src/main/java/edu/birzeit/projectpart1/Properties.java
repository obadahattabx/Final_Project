package edu.birzeit.projectpart1;

import android.os.Parcel;
import android.os.Parcelable;

public class Properties implements Parcelable {
    private int ID;
    private String cityName;
    private String address;
    private String surfaceArea;
    private String constructionYear;
    private String numOfBedroom;
    private String price;
    private String availabilDate;
    private String status;
    private String creatDate;
    private String description;
    private byte[] image;

    public  Properties(){

    }

    protected Properties(Parcel in) {
        ID = in.readInt();
        cityName = in.readString();
        address = in.readString();
        surfaceArea = in.readString();
        constructionYear = in.readString();
        numOfBedroom = in.readString();
        price = in.readString();
        availabilDate = in.readString();
        status = in.readString();
        creatDate = in.readString();
        description = in.readString();
        image = in.createByteArray();
    }

    public static final Creator<Properties> CREATOR = new Creator<Properties>() {
        @Override
        public Properties createFromParcel(Parcel in) {
            return new Properties(in);
        }

        @Override
        public Properties[] newArray(int size) {
            return new Properties[size];
        }
    };



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(String surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public String getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(String constructionYear) {
        this.constructionYear = constructionYear;
    }

    public String getNumOfBedroom() {
        return numOfBedroom;
    }

    public void setNumOfBedroom(String numOfBedroom) {
        this.numOfBedroom = numOfBedroom;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailabilDate() {
        return availabilDate;
    }

    public void setAvailabilDate(String availabilDate) {
        this.availabilDate = availabilDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ID);
        parcel.writeString(cityName);
        parcel.writeString(address);
        parcel.writeString(surfaceArea);
        parcel.writeString(constructionYear);
        parcel.writeString(numOfBedroom);
        parcel.writeString(price);
        parcel.writeString(availabilDate);
        parcel.writeString(status);
        parcel.writeString(creatDate);
        parcel.writeString(description);
        parcel.writeByteArray(image);
    }
}
