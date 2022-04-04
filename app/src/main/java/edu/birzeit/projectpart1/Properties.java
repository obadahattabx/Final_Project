package edu.birzeit.projectpart1;

import android.os.Parcel;
import android.os.Parcelable;

public class Properties implements Parcelable {
    private int ID;
    private int ID_tenant;
    private int ID_agancy;
    private String  garden;
    private String  balcony;
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
    private String valid;
    private byte[] image;
    private String urlImage ;

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
        ID_tenant=in.readInt();
        ID_agancy=in.readInt();
        garden=in.readString();
        balcony=in.readString();
        valid=in.readString();

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

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public int getID_tenant() {
        return ID_tenant;
    }

    public void setID_tenant(int ID_tenant) {
        this.ID_tenant = ID_tenant;
    }

    public int getID_agancy() {
        return ID_agancy;
    }

    public void setID_agancy(int ID_agancy) {
        this.ID_agancy = ID_agancy;
    }

    public String getGarden() {
        return garden;
    }

    public void setGarden(String garden) {
        this.garden = garden;
    }

    public String getBalcony() {
        return balcony;
    }

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

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
        parcel.writeInt(ID_agancy);
        parcel.writeInt(ID_tenant);
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
        parcel.writeString(balcony);
        parcel.writeString(garden);
        parcel.writeString(valid);


    }

}
