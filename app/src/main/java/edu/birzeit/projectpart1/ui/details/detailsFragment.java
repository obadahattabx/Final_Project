package edu.birzeit.projectpart1.ui.details;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import edu.birzeit.projectpart1.Properties;
import edu.birzeit.projectpart1.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link detailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class detailsFragment extends Fragment {

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
    Button Apply;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public detailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment detailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static detailsFragment newInstance(String param1, String param2) {
        detailsFragment fragment = new detailsFragment();
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
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         image_detail=getActivity().findViewById(R.id.detail_image);
         cityname=getActivity().findViewById(R.id.detail_city);
         address=getActivity().findViewById(R.id.detail_address);
         area=getActivity().findViewById(R.id.detail_area);
         year=getActivity().findViewById(R.id.detail_year);
         numOfBedroom=getActivity().findViewById(R.id.detail_bedroom);
         price=getActivity().findViewById(R.id.detail_price);
         date=getActivity().findViewById(R.id.detail_date);
         status=getActivity().findViewById(R.id.detail_status);
         discrption=getActivity().findViewById(R.id.detail_discription);
         Apply=getActivity().findViewById(R.id.details_appy);
        if(getArguments()!=null){
            detailsFragmentArgs args=detailsFragmentArgs.fromBundle(getArguments());
            Properties p=args.getDetails();
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



        }



    }
}