package edu.birzeit.projectpart1;

import android.os.Parcel;
import android.os.Parcelable;

public class ApplayProperty implements Parcelable {
    private int id;
    private int id_tenant;
    private int id_agancy;
    private int id_property;

    protected ApplayProperty(Parcel in) {
        id = in.readInt();
        id_tenant = in.readInt();
        id_agancy = in.readInt();
        id_property = in.readInt();
    }
    public ApplayProperty() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(id_tenant);
        dest.writeInt(id_agancy);
        dest.writeInt(id_property);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ApplayProperty> CREATOR = new Creator<ApplayProperty>() {
        @Override
        public ApplayProperty createFromParcel(Parcel in) {
            return new ApplayProperty(in);
        }

        @Override
        public ApplayProperty[] newArray(int size) {
            return new ApplayProperty[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_tenant() {
        return id_tenant;
    }

    public void setId_tenant(int id_tenant) {
        this.id_tenant = id_tenant;
    }

    public int getId_agancy() {
        return id_agancy;
    }

    public void setId_agancy(int id_agancy) {
        this.id_agancy = id_agancy;
    }

    public int getId_property() {
        return id_property;
    }

    public void setId_property(int id_property) {
        this.id_property = id_property;
    }
}
