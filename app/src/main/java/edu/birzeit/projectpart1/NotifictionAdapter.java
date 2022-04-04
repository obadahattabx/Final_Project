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

import java.util.List;

public class NotifictionAdapter extends ArrayAdapter<Properties> {
    Context context;
    int resource;



    public NotifictionAdapter(@NonNull Context context, int resource, @NonNull List<Properties> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView= LayoutInflater.from(context).inflate(resource,parent,false);
        TextView area=convertView.findViewById(R.id.view_city);
        TextView price=convertView.findViewById(R.id.view_address);
        TextView date=convertView.findViewById(R.id.view_date);
        ImageView imageView=convertView.findViewById(R.id.imageView);



        Properties current=getItem(position);
        area.setText(current.getSurfaceArea());
        price.setText(current.getPrice());
        date.setText(current.getAvailabilDate());
        if(current.getImage()!=null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(current.getImage(), 0, current.getImage().length);
            imageView.setImageBitmap(bitmap);
        }

        return convertView;
    }
}
