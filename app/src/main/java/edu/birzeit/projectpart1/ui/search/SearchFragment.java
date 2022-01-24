package edu.birzeit.projectpart1.ui.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import edu.birzeit.projectpart1.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    EditText city;
    EditText min_area;
    EditText max_area;
    EditText min_bedroom;
    EditText max_bedroom;
    EditText price;
    Spinner status;
    CheckBox garden;
    CheckBox balcony;
    ImageButton btn_search;
    String balcony_status="false",garden_status="false";
    String ContentSpinerStatus="";




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }




    @Override
    public void onResume() {
        super.onResume();
        city=getActivity().findViewById(R.id.search_city);
        min_area=getActivity().findViewById(R.id.search_min_area);
        max_area=getActivity().findViewById(R.id.search_max_area);
        min_bedroom=getActivity().findViewById(R.id.search_min_bedroom);
        max_bedroom=getActivity().findViewById(R.id.search_max_bebroom);
        price=getActivity().findViewById(R.id.search_price);
        status=getActivity().findViewById(R.id.search_spinner);
        garden=getActivity().findViewById(R.id.search_garden);
        balcony=getActivity().findViewById(R.id.search_balcony);
        btn_search=getActivity().findViewById(R.id.search_picture_button);

        balcony.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                balcony_status=String.valueOf(b);
                Log.i("qeury","this is balcony  "+balcony_status);


            }
        });
        garden.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                garden_status=String.valueOf(b);
                Log.i("qeury","this is garden "+garden_status);


            }
        });

        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ContentSpinerStatus = adapterView.getItemAtPosition(i).toString();

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        NavController navController= Navigation.findNavController(getView());
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchFragmentDirections.ActionNavSearchToListFragment action= SearchFragmentDirections.actionNavSearchToListFragment();
                action.setBalcony(balcony_status);
                action.setGarden(garden_status);
                if(min_area.getText().toString().isEmpty()){
                    action.setMinArea("");
                }
                else{
                    action.setMinArea(min_area.getText().toString());
                }

                if(max_area.getText().toString().isEmpty()){
                    action.setMaxArea("");
                }
                else{
                    action.setMaxArea(max_area.getText().toString());
                }
                if(min_bedroom.getText().toString().isEmpty()){
                    action.setMinBedroom("");
                }
                else{
                    action.setMinBedroom(min_bedroom.getText().toString());
                }
                if(max_bedroom.getText().toString().isEmpty()){
                    action.setMaxBedroom("");
                }
                else{
                    action.setMaxBedroom(max_bedroom.getText().toString());
                }
                if(price.getText().toString().isEmpty()){
                    action.setPrice("");
                }
                else{
                    action.setPrice(price.getText().toString());
                }
                if(city.getText().toString().isEmpty()){
                    action.setCityname("");
                }
                else{
                    action.setCityname(city.getText().toString());
                }





                action.setStatus(ContentSpinerStatus);
                navController.navigate(action);
            }
        });

    }
}