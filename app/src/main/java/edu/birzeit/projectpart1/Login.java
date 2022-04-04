package edu.birzeit.projectpart1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
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
    DataBaseHelper dataBaseHelper;



    private static final int NOTIFICATION_ID = 123;
    private static final String NOTIFICATION_TITLE = "Notification Title";
    private static final String NOTIFICATION_BODY = "This is the body of my notification";
    private static final String MY_CHANNEL_ID = "my_chanel_1";
    private static final String MY_CHANNEL_NAME = "My channel";

    @Override
    //yazan
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        remember=(CheckBox)findViewById(R.id.remember);
        // email=(EditText)findViewById(R.id.email_login);
       // password=(EditText)findViewById(R.id.password_login);
      //  login=(Button) findViewById(R.id.login);
          gest=(Button)findViewById(R.id.gest);
        tenant=(Button)findViewById(R.id.tenant);
        agency=(Button)findViewById(R.id.agency);
        login=findViewById(R.id.login);
        email=findViewById(R.id.email_login);
        password=findViewById(R.id.password_login);
         dataBaseHelper=new
                DataBaseHelper(Login.this,MainActivity.nameDatabase,null,1);

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
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("notification","notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(Login.this,HomeActivity.class);
//                startActivity(intent);
              //  createNotification(NOTIFICATION_TITLE,NOTIFICATION_BODY);










                if(remember.isChecked()){
                    editor.putString("email",email.getText().toString());
                    editor.putString("password",password.getText().toString());
                    editor.putBoolean("checkremember",remember.isChecked());
                    editor.apply();
                }
                else{
                    editor.clear().apply();
                }

                if(dataBaseHelper.checkusernamepassword(email.getText().toString(),password.getText().toString())){
                    Intent intent=new Intent(Login.this,HomeActivity.class);
                    startActivity(intent);
                    MakeNotification();
                }
                else{
                    email.setError("");
                    password.setError("");
                    System.out.println(email.getText().toString()+password.getText().toString());
                    String s=email.getText().toString();
                    Toast toast =Toast.makeText(Login.this,"Faild Login",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        gest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.type_user="GEST";
                Intent intent=new Intent(Login.this,HomeActivity.class);
                startActivity(intent);
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


    public void MakeNotification(){
        if(MainActivity.type_user.equals("AGANCY") && dataBaseHelper.check_notification(MainActivity.id_user_login)){


            NotificationCompat.Builder builder = new NotificationCompat.Builder(Login.this,"notification");
            builder.setContentTitle("You have request");
            builder.setContentText("If want show , click me");
            builder.setSmallIcon(R.drawable.ic_baseline_notifications_24);
            builder.setAutoCancel(true);

            Intent intent =new Intent(Login.this,HomeActivity.class);
            intent.putExtra("type","true");
            PendingIntent pendingIntent=PendingIntent.getActivity(Login.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setContentIntent(pendingIntent);
            MainActivity.cheakNotifi=true;
            NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0,builder.build());
        }



        boolean accept_tenant=dataBaseHelper.check_notification_TenantAccept(MainActivity.id_user_login);
        boolean reject_tenant=dataBaseHelper.check_notification_Tenantrejct(MainActivity.id_user_login);
        Log.i("test3","A "+accept_tenant+" R "+reject_tenant);

        if(MainActivity.type_user.equals("TENANT") && accept_tenant || reject_tenant ){


            NotificationCompat.Builder builder = new NotificationCompat.Builder(Login.this,"notification");
            builder.setContentTitle("Respone of Agency");
            if(accept_tenant){
                builder.setContentText("Your request has been accepted");
            }
            if(reject_tenant){
                builder.setContentText("Your request has been rejected ");

            }

            builder.setSmallIcon(R.drawable.ic_baseline_notifications_24);
            builder.setAutoCancel(true);
            MainActivity.cheakNotifi=false;
            Intent intent =new Intent(Login.this,HomeActivity.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(Login.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
            NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0,builder.build());
            dataBaseHelper.Update_SetNotifiction(String.valueOf(MainActivity.id_user_login),"FALSE");
        }
    }

}