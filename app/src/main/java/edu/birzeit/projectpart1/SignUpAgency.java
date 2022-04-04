package edu.birzeit.projectpart1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpAgency extends AppCompatActivity {
    Button but_backTologin,but_signup;
    Spinner spCity,spCountry;

    String ContentSpinerCity;
    String ContentSpinerCountry;
    EditText firstname ;
    EditText phonenumber;
    EditText email;
    EditText password;
    EditText conf_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_agency);
        but_backTologin=(Button)findViewById(R.id.backlogin);
        but_signup=(Button) findViewById(R.id.signup);
        spCity=findViewById(R.id.CityT);
        spCountry=findViewById(R.id.Country);

        firstname = (EditText)findViewById(R.id.firstname);
        phonenumber=(EditText)findViewById(R.id.phone_signup);
        email = (EditText)findViewById(R.id.email_signup);
        password=(EditText)findViewById(R.id.password_signup);
        conf_password=(EditText)findViewById(R.id.confirmpassword);
        DataBaseHelper dataBaseHelper =new
                DataBaseHelper(SignUpAgency.this,MainActivity.nameDatabase,null,1);
        //dataBaseHelper.addUser("obada@hotmail","sw2");
        Map<String,String> mp=new HashMap<String,String>();
        mp.put("palestine","970");
        mp.put("jordan","962");
        mp.put("Algeria","213");
        mp.put("Syria","963");
        but_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Alltrue()){

                    UserRentingAgency ur = new UserRentingAgency();
                    ur.setEmail(email.getText().toString());
                    ur.setPassword(password.getText().toString());
                    ur.setName(firstname.getText().toString());
                    ur.setPhoneNumber(phonenumber.getText().toString());
                    ur.setCity(ContentSpinerCity);
                    ur.setCountry(ContentSpinerCountry);
                    dataBaseHelper.addUserRentingAgency(ur);


                    Intent intent =new Intent(SignUpAgency.this,Login.class);
                    startActivity(intent);
                }

            }
        });






        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ContentSpinerCity = adapterView.getItemAtPosition(i).toString();

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ContentSpinerCountry = adapterView.getItemAtPosition(i).toString();
                phonenumber.setText(mp.get(ContentSpinerCountry));

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        but_backTologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(SignUpAgency.this, Login.class);
                startActivity(intent);
            }
        });

    }

    boolean ValidateMobile(String input){
        Pattern p= Pattern.compile("[9][7][0][0-9]{9}");
        Matcher m= p.matcher(input);
        if(m.matches()){
            return true;
        }
        else {
            phonenumber.setError("invaled number");
            return m.matches();

        }

    }
    boolean ValidateEmail(String inputemail){
        boolean result;
        result=(!inputemail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(inputemail).matches());
        if(result){
            return true;
        }
        else{
            email.setError("invaled Email");
            return result;
        }
    }
    boolean ValidateName(String name) {
        //boolean result;
        if (name.length() > 2 && name.length() <= 20)
            return true;
        else {
            firstname.setError("invaled name");
            return false;
        }
    }
    boolean ValidatePass(String pass) {
        if(pass.length()>=8 && pass.length() <=20) {
            Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=()*_!/])(?=\\S+$).{4,}$");
            Matcher m = p.matcher(pass);
            if (m.matches()) {
                return true;
            } else {
                password.setError("invaled password");
                return m.matches();
            }
        }
        else{
            if(pass.length()<8){
                password.setError("invaled password the password less 8 caracter");
                return false;
            }
            if(pass.length()>20){
                password.setError("invaled password .. the password greater than 20 caracter");
                return false;
            }
            return false;

        }
    }
    boolean ConfPassword(String pass1,String pass2){
        if (pass1.equals(pass2)){
            return true;
        }
        else {
            conf_password.setError("Envaled .. conferm password not same password");
            return false;
        }
    }

    public boolean Alltrue(){
        int count=0;
        if (ValidateEmail(email.getText().toString())){
            count++;
        }
        if(ValidateName(firstname.getText().toString())){
            count++;
        }
        if(ValidateMobile(phonenumber.getText().toString())){
            count++;
        }
        if(ValidatePass(password.getText().toString())){
            count++;
        }
        if(ConfPassword(password.getText().toString(),conf_password.getText().toString())){
            count++;
        }
        if(count==5){
            return true;
        }
        else{
            return false;
        }

    }
}