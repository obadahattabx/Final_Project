package edu.birzeit.projectpart1;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText email,password;
    Button login,tenant,agency,gest;
    CheckBox remember;
    @Override
    //yazan
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        remember=(CheckBox)findViewById(R.id.remember);
        // email=(EditText)findViewById(R.id.email_login);
       // password=(EditText)findViewById(R.id.password_login);
      //  login=(Button) findViewById(R.id.login);
        //  gest=(Button)findViewById(R.id.gest);
        tenant=(Button)findViewById(R.id.tenant);
        agency=(Button)findViewById(R.id.agency);
        login=findViewById(R.id.login);
        email=findViewById(R.id.email_login);
        password=findViewById(R.id.password_login);
        DataBaseHelper dataBaseHelper =new
                DataBaseHelper(Login.this,"home4.db",null,1);

     // dataBaseHelper.addUser("obada@hotmail","12312");

        SharedPreferences shPr=getSharedPreferences("rememberme",MODE_PRIVATE);
        SharedPreferences.Editor editor= shPr.edit();
        getPreferencesData();
        tenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Login.this, SignUpTenant.class);
                startActivity(intent);
            }
        });
        agency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Login.this, SignUpAgency.class);
                startActivity(intent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,HomeActivity.class);
                startActivity(intent);
//
//                if(remember.isChecked()){
//                    editor.putString("email",email.getText().toString());
//                    editor.putString("password",password.getText().toString());
//                    editor.putBoolean("checkremember",remember.isChecked());
//                    editor.apply();
//                }
//                else{
//                    editor.clear().apply();
//                }
//
//                if(dataBaseHelper.checkusernamepassword(email.getText().toString(),password.getText().toString())){
//                    Intent intent=new Intent(Login.this,HomeActivity.class);
//                    startActivity(intent);
//                }
//                else{
//                    email.setError("");
//                    password.setError("");
//                    System.out.println(email.getText().toString()+password.getText().toString());
//                    String s=email.getText().toString();
//                    System.out.println(s.charAt(0));
//                    Toast toast =Toast.makeText(Login.this,"Faild Login",Toast.LENGTH_SHORT);
//                    toast.show();
//                }
            }
        });


    }
    private void getPreferencesData(){
        SharedPreferences sp= getSharedPreferences("rememberme",MODE_PRIVATE);
        if(sp.contains("email")){
            String Remail=sp.getString("email","not found");
            email.setText(Remail.toString());
            System.out.println(Remail);
        }
        if(sp.contains("password")){
            String Rpassword=sp.getString("password","not found");
            password.setText(Rpassword.toString());
        }
        if(sp.contains("checkremember")){
            Boolean b = sp.getBoolean("checkremember",false );
            remember.setChecked(b);

        }
    }
}