package edu.birzeit.projectpart1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class PropertiesAdapter extends ArrayAdapter<Properties> {
    Context context;
    int resource;
    String Type;
    DataBaseHelper dataBaseHelper;





    public PropertiesAdapter(@NonNull Context context, int resource, @NonNull List<Properties> objects,String Type) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.Type=Type;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView= LayoutInflater.from(context).inflate(resource,parent,false);
        TextView city=convertView.findViewById(R.id.view_city);
        TextView address=convertView.findViewById(R.id.view_address);
        TextView date=convertView.findViewById(R.id.view_date);
        TextView firstname=convertView.findViewById(R.id.view_firstname);
        TextView lastname =convertView.findViewById(R.id.view_lastname);
        ImageView imageView=convertView.findViewById(R.id.imageView);




        Properties current=getItem(position);

        city.setText("City: "+current.getCityName());
        address.setText("Postal Address: "+current.getAddress());
        firstname.setText("");
        lastname.setText("");
        if(Type.equals("AGANCY")){
            date.setText("Rented perio: "+current.getCreatDate());

            UserTenant ut =MainActivity.dataBaseHelper.getProfile_Tenant(current.getID_tenant());
            firstname.setText("First name tenant: "+ut.getFirstName());
            lastname.setText("Last name tenant: "+ut.getLastName());
        }
        if(Type.equals("TENANT")){
            date.setText("Rented perio: "+current.getCreatDate());
            UserRentingAgency ur=MainActivity.dataBaseHelper.getProfile_Agency(current.getID_agancy());
            firstname.setText("Name agancy: "+ur.getName());

        }
        if(Type.equals("NOTIFICATION")){
            date.setText("request for apply");
        }




        if(current.getImage()!=null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(current.getImage(), 0, current.getImage().length);
            imageView.setImageBitmap(bitmap);
        }

        return convertView;
    }

    public DataBaseHelper getDataBaseHelper(DataBaseHelper dataBaseHelper) {
        return this.dataBaseHelper;
    }

    public void setDataBaseHelper(DataBaseHelper dataBaseHelper) {
        this.dataBaseHelper = dataBaseHelper;
    }
}
