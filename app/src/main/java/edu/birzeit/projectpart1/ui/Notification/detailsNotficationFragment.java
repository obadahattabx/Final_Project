package edu.birzeit.projectpart1.ui.Notification;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import edu.birzeit.projectpart1.ApplayProperty;
import edu.birzeit.projectpart1.DataBaseHelper;
import edu.birzeit.projectpart1.MainActivity;
import edu.birzeit.projectpart1.Properties;
import edu.birzeit.projectpart1.R;
import edu.birzeit.projectpart1.UserTenant;
import edu.birzeit.projectpart1.ui.details.detailsFragmentArgs;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link detailsNotficationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class detailsNotficationFragment extends Fragment {
    EditText firstname;
    EditText lastname;
    EditText email;
    EditText password;
    EditText gender;
    EditText city;
    EditText phone;
    EditText country;
    EditText nationlity;
    EditText salary;
    EditText familysize;
    EditText occutption;
    Button accept;
    Button reject;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public detailsNotficationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment detailsNotficationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static detailsNotficationFragment newInstance(String param1, String param2) {
        detailsNotficationFragment fragment = new detailsNotficationFragment();
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
        return inflater.inflate(R.layout.fragment_details_notfication, container, false);
    }
    @Override
    public void onResume() {
        super.onResume();



        firstname = getActivity().findViewById(R.id.firstname_profileTen);
        lastname = getActivity().findViewById(R.id.lastname_profileTen);
        email = getActivity().findViewById(R.id.email_profileTen);
        gender=getActivity().findViewById(R.id.gender_profileTen);
        city = getActivity().findViewById(R.id.city_profileTen);
        phone = getActivity().findViewById(R.id.phone_profileTen);
        country = getActivity().findViewById(R.id.country_profileTen);
        nationlity = getActivity().findViewById(R.id.nationality_profileTen);
        salary = getActivity().findViewById(R.id.salary_profileTen);
        familysize = getActivity().findViewById(R.id.familysize_profileTen);
        occutption = getActivity().findViewById(R.id.occupation_profileTen);
        accept = getActivity().findViewById(R.id.detailN_Accept);
        reject = getActivity().findViewById(R.id.detialN_Reject);
        DataBaseHelper dataBaseHelper = new
                DataBaseHelper(getActivity(), MainActivity.nameDatabase, null, 1);

        if(getArguments()!=null) {
            detailsNotficationFragmentArgs args1=detailsNotficationFragmentArgs.fromBundle(getArguments());
            UserTenant ut = args1.getProfileTenant();
            ApplayProperty applayProper=args1.getIDApplay();



            //UserTenant ut = dataBaseHelper.getProfile_Tenant();
            firstname.setText(ut.getFirstName());
            lastname.setText(ut.getLastName());
            email.setText(ut.getEmail());
            gender.setText(ut.getGender());
            city.setText(ut.getCity());
            phone.setText(ut.getPhoneNumber());
            country.setText(ut.getCountry());
            nationlity.setText(ut.getNationality());
            salary.setText(ut.getSalary());
            familysize.setText(ut.getFamilySize());
            occutption.setText(ut.getOccuptaion());

            NavController navController= Navigation.findNavController(getView());
            @NonNull NavDirections action= detailsNotficationFragmentDirections.actionDetailsNotficationFragmentToNavNotifiction();

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dataBaseHelper.delete_applayproperty(applayProper.getId_property());
                    dataBaseHelper.Update_proprtyAfterAccept(applayProper.getId_tenant(),String.valueOf(applayProper.getId()),"FALSE");
                    dataBaseHelper.Update_SetNotifiction(String.valueOf(applayProper.getId_tenant()),"TRUE ACCEPT");


                    navController.navigate(action);
                }
            });



            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dataBaseHelper.delete_applay(applayProper.getId());
                    dataBaseHelper.Update_proprtyAfterAccept(-1,String.valueOf(applayProper.getId()),"TRUE");
                    dataBaseHelper.Update_SetNotifiction(String.valueOf(applayProper.getId_tenant()),"TRUE REJECT");
                    navController.navigate(action);
                }
            });

        }





    }


}