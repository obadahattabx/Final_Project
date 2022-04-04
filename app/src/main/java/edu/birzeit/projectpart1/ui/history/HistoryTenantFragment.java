package edu.birzeit.projectpart1.ui.history;

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

import edu.birzeit.projectpart1.DataBaseHelper;
import edu.birzeit.projectpart1.MainActivity;
import edu.birzeit.projectpart1.Properties;
import edu.birzeit.projectpart1.PropertiesAdapter;
import edu.birzeit.projectpart1.R;
import edu.birzeit.projectpart1.ui.home.HomeFragmentDirections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryTenantFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryTenantFragment extends Fragment {

    ListView history;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryTenantFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryTenantFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryTenantFragment newInstance(String param1, String param2) {
        HistoryTenantFragment fragment = new HistoryTenantFragment();
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
        return inflater.inflate(R.layout.fragment_history_tenant, container, false);
    }




    @Override
    public void onResume() {
        super.onResume();
        DataBaseHelper dataBaseHelper =new
                DataBaseHelper(getActivity(), MainActivity.nameDatabase,null,1);

        history=getActivity().findViewById(R.id.historyList_tenant);



        // ArrayList<Properties> ap=dataBaseHelper.getProperties_Search("tulakrm","45",null,null,null,null,null,null,null);
        ArrayList<Properties> ap=dataBaseHelper.getAllProperties_userTenant_history();
        PropertiesAdapter pd=new PropertiesAdapter(getActivity(),R.layout.view_property,ap,"TENANT");
        pd.getDataBaseHelper(dataBaseHelper);
        history.setAdapter(pd);

        NavController navController= Navigation.findNavController(getView());



        history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HistoryTenantFragmentDirections.ActionHistoryTenantFragmentToDetailsFragment action =HistoryTenantFragmentDirections.actionHistoryTenantFragmentToDetailsFragment(ap.get(i));
                action.setEnableButton(true);
                navController.navigate(action);

                Log.i("selecte","this is  "+ap.get(i).getAvailabilDate());


            }
        });

    }

}