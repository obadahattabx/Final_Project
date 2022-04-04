package edu.birzeit.projectpart1.ui.EditListProprty;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import edu.birzeit.projectpart1.DataBaseHelper;
import edu.birzeit.projectpart1.MainActivity;
import edu.birzeit.projectpart1.Properties;
import edu.birzeit.projectpart1.R;
import edu.birzeit.projectpart1.ui.Postproperty.PostFragmentDirections;
import edu.birzeit.projectpart1.ui.details.detailsFragmentArgs;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditDetailsFragment extends Fragment {

    ImageView image_detail;
    EditText cityname;
    EditText address;
    EditText area;
    EditText year;
    EditText numOfBedroom;
    EditText price;
    EditText date;
    TextView status;
    EditText discrption;
    Button edit;
    Button delete;
    Button confirm;
    int id_pro;
    byte[] image;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditDetailsFragment newInstance(String param1, String param2) {
        EditDetailsFragment fragment = new EditDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_details, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        image_detail=getActivity().findViewById(R.id.edit_image);
        cityname=getActivity().findViewById(R.id.edit_city);
        address=getActivity().findViewById(R.id.edit_address);
        area=getActivity().findViewById(R.id.edit_area);
        year=getActivity().findViewById(R.id.edit_year);
        numOfBedroom=getActivity().findViewById(R.id.edit_bedroom);
        price=getActivity().findViewById(R.id.edit_price);
        date=getActivity().findViewById(R.id.edit_ate);
        status=getActivity().findViewById(R.id.edit_status);
        discrption=getActivity().findViewById(R.id.edit_discription);
        edit=getActivity().findViewById(R.id.Edit_editF);
        delete=getActivity().findViewById(R.id.delet_editF);
        confirm=getActivity().findViewById(R.id.confirm_editF);
        DataBaseHelper dataBaseHelper =new
                DataBaseHelper(getActivity(), MainActivity.nameDatabase,null,1);
        if(getArguments()!=null){
            EditDetailsFragmentArgs args=EditDetailsFragmentArgs.fromBundle(getArguments());
            Properties p=args.getDetails();
            image=p.getImage();
            if(p.getImage()!=null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(p.getImage(), 0, p.getImage().length);
                image_detail.setImageBitmap(bitmap);
            }
            cityname.setText(p.getCityName());
            address.setText(p.getAddress());
            area.setText(p.getSurfaceArea());
            year.setText(p.getConstructionYear());
            numOfBedroom.setText(p.getNumOfBedroom());
            price.setText(p.getPrice());
            date.setText(p.getAvailabilDate());
            status.setText(p.getStatus());
            discrption.setText(p.getDescription());
            id_pro=p.getID();

            setEnabled_allEdit(false);
        }
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseHelper.delete_proprty(String.valueOf(id_pro));
                NavController navController= Navigation.findNavController(getView());
                @NonNull NavDirections action= EditDetailsFragmentDirections.actionEditDetailsFragment2ToNavEdit();
                navController.navigate(action);

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEnabled_allEdit(true);
                confirm.setEnabled(true);
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEnabled_allEdit(false);
                confirm.setEnabled(false);
                Properties p=new Properties();
                p.setCityName(cityname.getText().toString());
                p.setAddress(address.getText().toString());
                p.setSurfaceArea(area.getText().toString());
                p.setConstructionYear(year.getText().toString());
                p.setNumOfBedroom(numOfBedroom.getText().toString());
                p.setPrice(p.getPrice());
                p.setAvailabilDate(date.getText().toString());
                p.setStatus(status.getText().toString());
                p.setDescription(discrption.getText().toString());
                p.setImage(image);// its mistake
                p.setPrice(price.getText().toString());
                dataBaseHelper.Update_proprty(p,String.valueOf(id_pro));

            }
        });



    }
    public void setEnabled_allEdit(boolean b){
         image_detail.setEnabled(b);
         cityname.setEnabled(b);
         address.setEnabled(b);
         area.setEnabled(b);
         year.setEnabled(b);
         numOfBedroom.setEnabled(b);
         price.setEnabled(b);
         date.setEnabled(b);
         status.setEnabled(b);
         discrption.setEnabled(b);
    }
}