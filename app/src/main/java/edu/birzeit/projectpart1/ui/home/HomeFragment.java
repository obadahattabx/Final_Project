package edu.birzeit.projectpart1.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import edu.birzeit.projectpart1.DataBaseHelper;
import edu.birzeit.projectpart1.HomeActivity;
import edu.birzeit.projectpart1.MainActivity;
import edu.birzeit.projectpart1.Properties;
import edu.birzeit.projectpart1.PropertiesAdapter;
import edu.birzeit.projectpart1.R;
import edu.birzeit.projectpart1.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    Bundle bundle;
    String notifi;


    ListView propertylist;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();
        DataBaseHelper dataBaseHelper = new
                DataBaseHelper(getActivity(), MainActivity.nameDatabase, null, 1);
        Log.i("test","اثقثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثثث");




        try {
            bundle = getActivity().getIntent().getExtras();
             notifi=bundle.getString("type");
        }catch (Exception e){
            Log.i("test",String.valueOf(bundle));
            Log.i("test",String.valueOf(notifi));
        }

        Log.i("test",String.valueOf(bundle));


        Log.i("test",String.valueOf(notifi));
        if(bundle!=null && MainActivity.cheakNotifi &&notifi.equals("true")) {
               NavController navController = Navigation.findNavController(getView());
               @NonNull NavDirections action = HomeFragmentDirections.actionNavHomeToNavNotifiction();
               dataBaseHelper.Update_SetNotifiction(String.valueOf(MainActivity.id_user_login), "FALSE");
               getActivity().getIntent().putExtra("type","false");
               MainActivity.cheakNotifi=false;
               navController.navigate(action);
           }




       else {





           propertylist = getActivity().findViewById(R.id.propertylist);


           // ArrayList<Properties> ap=dataBaseHelper.getProperties_Search("tulakrm","45",null,null,null,null,null,null,null);
           ArrayList<Properties> ap = dataBaseHelper.getAllProperties();
           PropertiesAdapter pd = new PropertiesAdapter(getActivity(), R.layout.view_property, ap,"NORMAL");
           propertylist.setAdapter(pd);

            NavController navController= Navigation.findNavController(getView());


           propertylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   HomeFragmentDirections.ActionNavHomeToDetailsFragment action = HomeFragmentDirections.actionNavHomeToDetailsFragment(ap.get(i));

                   navController.navigate(action);

                   Log.i("selecte", "this is  " + ap.get(i).getAvailabilDate());


               }
           });
       }

    }
}