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

public class SignUpTenant extends AppCompatActivity {
    Button but_signup,but_backTologin;
    EditText ET_firstname;
    EditText ET_lastname;
    EditText ET_phonenumber;
    EditText ET_email;
    EditText ET_password;
    EditText ET_conf_password;
    EditText ET_salary;
    EditText ET_sizefamili;
    EditText ET_occupation;
    Spinner spCity,spCountry,spGender,spNation;
    String ContentSpinerCity;
    String ContentSpinerCountry;
    String ContentSpinerGender;
    String ContentSpinerNation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_tenant);
        but_backTologin=(Button)findViewById(R.id.backlogin);
        but_signup=(Button) findViewById(R.id.signup);
        ET_firstname = (EditText)findViewById(R.id.firstname);
        ET_phonenumber=(EditText)findViewById(R.id.phone_signup);
        ET_email = (EditText)findViewById(R.id.email_signup);
        ET_password=(EditText)findViewById(R.id.password_signup);
        ET_conf_password=(EditText)findViewById(R.id.confirmpassword);

        ET_lastname = (EditText)findViewById(R.id.lastname);
        ET_salary=(EditText)findViewById(R.id.salary);
        ET_occupation=(EditText)findViewById(R.id.occupation);
        ET_sizefamili=(EditText)findViewById(R.id.familysize);
        spCity=findViewById(R.id.CityT);
        spNation=findViewById(R.id.nationality);
        spCountry=findViewById(R.id.CountryT);
        spGender=findViewById(R.id.gender);

        DataBaseHelper dataBaseHelper =new
                DataBaseHelper(SignUpTenant.this,MainActivity.nameDatabase,null,1);

        Map<String,String> mp=new HashMap<String,String>();
        mp.put("palestine","970");
        mp.put("jordan","962");
        mp.put("Algeria","213");
        mp.put("Syria","963");
        but_backTologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(SignUpTenant.this, Login.class);
                startActivity(intent);

            }
        });
        but_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Alltrue()) {
                    UserTenant ut = new UserTenant();
                    ut.setEmail(ET_email.getText().toString());
                    ut.setFirstName(ET_firstname.getText().toString());
                    ut.setLastName(ET_lastname.getText().toString());
                    ut.setPassword(ET_password.getText().toString());
                    ut.setPhoneNumber(ET_phonenumber.getText().toString());
                    ut.setSalary(ET_salary.getText().toString());
                    ut.setFamilySize(ET_sizefamili.getText().toString());
                    ut.setOccuptaion(ET_occupation.getText().toString());
                    ut.setGender(ContentSpinerGender);
                    ut.setCity(ContentSpinerCity);
                    ut.setCountry(ContentSpinerCountry);
                    ut.setNationality(ContentSpinerNation);
                    dataBaseHelper.addUserTenant(ut);
                    Intent intent = new Intent(SignUpTenant.this, Login.class);
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
                ET_phonenumber.setText(mp.get(ContentSpinerCountry));

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ContentSpinerGender = adapterView.getItemAtPosition(i).toString();

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        spNation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ContentSpinerNation = adapterView.getItemAtPosition(i).toString();

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
            ET_phonenumber.setError("invaled number");
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
            ET_email.setError("invaled Email");
            return result;
        }
    }
    boolean ValidateName(String name) {
        //boolean result;
        if (name.length() > 2 && name.length() <= 20)
            return true;
        else {
            ET_firstname.setError("invaled First name");
            return false;
        }
    }
    boolean ValidateLastName(String name){
        if (name.length() > 2 && name.length() <= 20)
            return true;
        else {
            ET_lastname.setError("invaled Last name");
            return false;
        }
    }
    boolean ValidateSalary(String salary){
        Pattern p= Pattern.compile("-?[0-9]+");
        Matcher m= p.matcher(salary);
        if(m.matches()){
            return true;
        }
        else {
            ET_salary.setError("invaled ... salary must be integer (delet a caracter)");
            return m.matches();

        }
    }
    boolean ValidateSizeFamily(String size){
        Pattern p= Pattern.compile("-?[0-9]+");
        Matcher m= p.matcher(size);

        if(m.matches()){
            return true;
        }
        else {
            ET_sizefamili.setError("invaled ... size must be integer (delet a caracter)");
            return m.matches();

        }
    }
    boolean ValidateOccupation(String occupation){
        if(occupation.length()>=1 && occupation.length() <=20) {
            return true;
        }
        else{
            if(occupation.length()<1) {
                ET_occupation.setError("invaled ... number of caracter less than 1");
                return false;
            }
            else{
                ET_occupation.setError("invaled ... number of caracter greater than 20");
                return false;
            }
        }
    }
    boolean ValidatePass(String pass) {
        if(pass.length()>=8 && pass.length() <=20) {
            Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=()*_!/])(?=\\S+$).{4,}$");
            Matcher m = p.matcher(pass);
            if (m.matches()) {
                return true;
            } else {
                ET_password.setError("invaled password .. must be contain capital ,small leter , number,spitial caracter");
                return m.matches();
            }
        }
        else{
            if(pass.length()<8){
                ET_password.setError("invaled password the password less 8 caracter");
                return false;
            }
            if(pass.length()>20){
                ET_password.setError("invaled password .. the password greater than 20 caracter");
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
            ET_conf_password.setError("Envaled .. conferm password not same password");
            return false;
        }
    }

    public   boolean Alltrue(){
        int count=0;
        if (ValidateEmail(ET_email.getText().toString())){
            count++;
        }
        if(ValidateName(ET_firstname.getText().toString())){
            count++;
        }
        if(ValidateMobile(ET_phonenumber.getText().toString())){
            count++;
        }
        if(ValidatePass(ET_password.getText().toString())){
            count++;
        }
        if(ConfPassword(ET_password.getText().toString(), ET_conf_password.getText().toString())){
            count++;
        }
        if (ValidateLastName(ET_lastname.getText().toString())){
            count++;
        }
        if(ValidateSalary(ET_salary.getText().toString())){
            count++;
        }
        if(ValidateOccupation(ET_occupation.getText().toString())){
            count++;
        }
        if(ValidateSizeFamily(ET_sizefamili.getText().toString())){
            count++;
        }
        if(count==9){
            return true;
        }
        else{
            return false;
        }

    }
}