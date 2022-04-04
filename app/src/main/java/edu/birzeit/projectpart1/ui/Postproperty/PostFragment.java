package edu.birzeit.projectpart1.ui.Postproperty;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import edu.birzeit.projectpart1.DataBaseHelper;
import edu.birzeit.projectpart1.MainActivity;
import edu.birzeit.projectpart1.Properties;
import edu.birzeit.projectpart1.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText cityname;
    EditText address;
    EditText area;
    EditText constructionYear;
    EditText numBedroom;
    EditText price;
    EditText date;
    EditText description;
    Spinner status;
    DatePickerDialog.OnDateSetListener setListener;
    ImageButton addPicture;
    Button Post;
    String ContentSpinerStatus;
    CheckBox garden;
    CheckBox balcony;
    String balcony_status="false",garden_status="false";
    byte [] imageButton;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostFragment newInstance(String param1, String param2) {
        PostFragment fragment = new PostFragment();
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
        return inflater.inflate(R.layout.fragment_post, container, false);




    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        date = getActivity().findViewById(R.id.post_date);
        addPicture= (ImageButton) getActivity().findViewById(R.id.post_picture_button);
        Post= getActivity().findViewById(R.id.post_button);
        cityname=getActivity().findViewById(R.id.edit_city);
        price=getActivity().findViewById(R.id.post_price);
        area=getActivity().findViewById(R.id.edit_area);
        address=getActivity().findViewById(R.id.edit_address);
        constructionYear=getActivity().findViewById(R.id.edit_year);
        numBedroom=getActivity().findViewById(R.id.post_bedroom);
        status=getActivity().findViewById(R.id.post_status);
        description=getActivity().findViewById(R.id.post_discription);
        garden=getActivity().findViewById(R.id.post_garden);
        balcony=getActivity().findViewById(R.id.post_balcony);

        balcony.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                balcony_status=String.valueOf(b);


            }
        });
        garden.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                garden_status=String.valueOf(b);


            }
        });
        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day =calendar.get(Calendar.DAY_OF_MONTH);
        DataBaseHelper dataBaseHelper =new
                DataBaseHelper(getActivity(), MainActivity.nameDatabase,null,1);




        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ContentSpinerStatus = adapterView.getItemAtPosition(i).toString();

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener,year,month,day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });


        setListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month=month+1;
                String contentdate=day+"/"+month+"/"+year;
                date.setText(contentdate);

            }
        };


        addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,100);

            }
        });
        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Properties p=new Properties();
                p.setAddress(address.getText().toString());
                p.setAvailabilDate(date.getText().toString());
                p.setCityName(cityname.getText().toString());
                p.setPrice(price.getText().toString());
                p.setConstructionYear(constructionYear.getText().toString());
                p.setSurfaceArea(area.getText().toString());
                p.setNumOfBedroom(numBedroom.getText().toString());
                p.setStatus(ContentSpinerStatus);
                p.setImage(imageButton);
                p.setDescription(description.getText().toString());
                p.setGarden(garden_status);
                p.setBalcony(balcony_status);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                System.out.println(dtf.format(now));
                p.setCreatDate(dtf.format(now));
               // hereee discrption;
                dataBaseHelper.insertPostProperty(p);
                NavController navController= Navigation.findNavController(getView());
                @NonNull NavDirections action=PostFragmentDirections.actionNavPostpropertyToNavEdit();
                navController.navigate(action);





            }
        });



    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==getActivity().RESULT_OK&& requestCode==100) {
            Uri uri=data.getData();

            try {
                InputStream inputStream=getActivity().getContentResolver().openInputStream(uri);
                Bitmap decode= BitmapFactory.decodeStream(inputStream);
                System.out.println("here----- "+decode);
                addPicture.setImageBitmap(decode);
                imageButton=getBytes(decode);


            }catch (Exception e){

            }

        }
    }
    public  static  byte[] getBytes(Bitmap bitmap){
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);

        return stream.toByteArray();
    }
}