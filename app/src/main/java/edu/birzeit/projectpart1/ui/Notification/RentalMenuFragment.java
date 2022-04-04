package edu.birzeit.projectpart1.ui.Notification;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import edu.birzeit.projectpart1.ApplayProperty;
import edu.birzeit.projectpart1.DataBaseHelper;
import edu.birzeit.projectpart1.MainActivity;
import edu.birzeit.projectpart1.Properties;
import edu.birzeit.projectpart1.PropertiesAdapter;
import edu.birzeit.projectpart1.R;
import edu.birzeit.projectpart1.UserTenant;
import edu.birzeit.projectpart1.ui.home.HomeFragmentDirections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RentalMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RentalMenuFragment extends Fragment {
    ListView notifilist;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RentalMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RentalMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RentalMenuFragment newInstance(String param1, String param2) {
        RentalMenuFragment fragment = new RentalMenuFragment();
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
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        DataBaseHelper dataBaseHelper =new
                DataBaseHelper(getActivity(),MainActivity.nameDatabase,null,1);
        getActivity().getIntent().putExtra("type","false");
        notifilist=getActivity().findViewById(R.id.notificationlist);
        ArrayList<ApplayProperty> applay= dataBaseHelper.getAllApplay(MainActivity.id_user_login);
        ArrayList<Properties> Arpo=new ArrayList<>();
        ArrayList<UserTenant> AUT=new ArrayList<>();
        for(int i=0;i<applay.size();i++){
            Properties p =dataBaseHelper.getProperty_byID(applay.get(i).getId_property());
            UserTenant ut=dataBaseHelper.getProfile_Tenant(applay.get(i).getId_tenant());
            Arpo.add(p);
            AUT.add(ut);

        }


        // ArrayList<Properties> ap=dataBaseHelper.getProperties_Search("tulakrm","45",null,null,null,null,null,null,null);

        PropertiesAdapter pd=new PropertiesAdapter(getActivity(),R.layout.view_property,Arpo,"NOTIFICATION");
        notifilist.setAdapter(pd);

        NavController navController= Navigation.findNavController(getView());



        notifilist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RentalMenuFragmentDirections.ActionNavNotifictionToDetailsNotficationFragment action=RentalMenuFragmentDirections.actionNavNotifictionToDetailsNotficationFragment(AUT.get(i),applay.get(i));
                navController.navigate(action);

                Log.i("selecte","this is  "+Arpo.get(i).getAvailabilDate());


            }
        });

    }
}