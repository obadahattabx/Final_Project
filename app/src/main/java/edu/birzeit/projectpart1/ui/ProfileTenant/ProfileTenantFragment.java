package edu.birzeit.projectpart1.ui.ProfileTenant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import edu.birzeit.projectpart1.DataBaseHelper;
import edu.birzeit.projectpart1.MainActivity;
import edu.birzeit.projectpart1.R;
import edu.birzeit.projectpart1.UserTenant;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileTenantFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileTenantFragment extends Fragment {
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
    Button edit;
    Button confirm;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileTenantFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileTenentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileTenantFragment newInstance(String param1, String param2) {
        ProfileTenantFragment fragment = new ProfileTenantFragment();
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
        return inflater.inflate(R.layout.fragment_profile_tenent, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        firstname=getActivity().findViewById(R.id.firstname_profileTen);
        lastname=getActivity().findViewById(R.id.lastname_profileTen);
        email=getActivity().findViewById(R.id.email_profileTen);
         password=getActivity().findViewById(R.id.password_profileTen);
         gender=getActivity().findViewById(R.id.gender_profileTen);
         city=getActivity().findViewById(R.id.city_profileTen);
         phone=getActivity().findViewById(R.id.phone_profileTen);
         country=getActivity().findViewById(R.id.country_profileTen);
         nationlity=getActivity().findViewById(R.id.nationality_profileTen);
         salary=getActivity().findViewById(R.id.salary_profileTen);
         familysize=getActivity().findViewById(R.id.familysize_profileTen);
         occutption=getActivity().findViewById(R.id.occupation_profileTen);
         edit=getActivity().findViewById(R.id.detailN_Accept);
         confirm=getActivity().findViewById(R.id.detialN_Reject);
        DataBaseHelper dataBaseHelper =new
                DataBaseHelper(getActivity(), MainActivity.nameDatabase,null,1);
        UserTenant ut=dataBaseHelper.getProfile_Tenant();
        firstname.setText(ut.getFirstName());
        lastname.setText(ut.getLastName());
        email.setText(ut.getEmail());
        password.setText(ut.getPassword());
        gender.setText(ut.getGender());
        city.setText(ut.getCity());
        phone.setText(ut.getPhoneNumber());
        country.setText(ut.getCountry());
        nationlity.setText(ut.getNationality());
        salary.setText(ut.getSalary());
        familysize.setText(ut.getFamilySize());
        occutption.setText(ut.getOccuptaion());


        setEnabled_allEdit(false);
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
                UserTenant ut =new UserTenant();
                ut.setFirstName(firstname.getText().toString());
                ut.setLastName(lastname.getText().toString());
                ut.setEmail(email.getText().toString());
                ut.setPassword(password.getText().toString());
                ut.setGender(gender.getText().toString());
                ut.setCity(city.getText().toString());
                ut.setPhoneNumber(phone.getText().toString());
                ut.setCountry(country.getText().toString());
                ut.setNationality(nationlity.getText().toString());
                ut.setSalary(salary.getText().toString());
                ut.setFamilySize(familysize.getText().toString());
                ut.setOccuptaion(occutption.getText().toString());
                dataBaseHelper.Update_Tenant(ut);

            }
        });


    }
    public  void setEnabled_allEdit(boolean b){
        firstname.setEnabled(b);
        lastname.setEnabled(b);
        email.setEnabled(b);
        password.setEnabled(b);
        gender.setEnabled(b);
        city.setEnabled(b);
        phone.setEnabled(b);
        country.setEnabled(b);
        nationlity.setEnabled(b);
        salary.setEnabled(b);
        familysize.setEnabled(b);
        occutption.setEnabled(b);


    }


}