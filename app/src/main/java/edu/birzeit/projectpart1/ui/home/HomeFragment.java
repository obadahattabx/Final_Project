package edu.birzeit.projectpart1.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import edu.birzeit.projectpart1.DataBaseHelper;
import edu.birzeit.projectpart1.Properties;
import edu.birzeit.projectpart1.PropertiesAdapter;
import edu.birzeit.projectpart1.R;
import edu.birzeit.projectpart1.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {


    ListView propertylist;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();
        DataBaseHelper dataBaseHelper =new
                DataBaseHelper(getActivity(),"home5.db",null,1);
        propertylist=getActivity().findViewById(R.id.propertylist);



       // ArrayList<Properties> ap=dataBaseHelper.getProperties_Search("tulakrm","45",null,null,null,null,null,null,null);
        ArrayList<Properties> ap=dataBaseHelper.getAllProperties();
        PropertiesAdapter pd=new PropertiesAdapter(getActivity(),R.layout.view_property,ap);
        propertylist.setAdapter(pd);
        NavController navController= Navigation.findNavController(getView());
        propertylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HomeFragmentDirections.ActionNavHomeToDetailsFragment action=HomeFragmentDirections.actionNavHomeToDetailsFragment(ap.get(i));

                navController.navigate(action);

                Log.i("selecte","this is  "+ap.get(i).getAvailabilDate());


            }
        });

    }
}