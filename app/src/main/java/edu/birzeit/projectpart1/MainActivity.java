package edu.birzeit.projectpart1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button buttongetstart;
    private static final String TOAST_connected = "Succefull connected ";
    private static final String TOAST_Notconnected = "Faild connection ";
    public static int id_user_login;
    public static String type_user;
    public  static String nameDatabase="home23.db";
    public static  boolean cheakNotifi=false;
    private RequestQueue mQueue;






    private static final int NOTIFICATION_ID = 123;
    private static final String NOTIFICATION_TITLE = "Notification Title";
    private static final String NOTIFICATION_BODY = "This is the body of my notification";
    private static final String MY_CHANNEL_ID = "my_chanel_1";
    private static final String MY_CHANNEL_NAME = "My channel";
    public  static DataBaseHelper dataBaseHelper;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttongetstart=(Button)findViewById(R.id.getstart);
        String url="http://www.mocky.io/v2/5b4e6b4e3200002c009c2a44";

        buttongetstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                 dataBaseHelper =new
                        DataBaseHelper(MainActivity.this,MainActivity.nameDatabase,null,1);


                if (checkConnection()) {
                    Toast toast =Toast.makeText(MainActivity.this,TOAST_connected,Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    mQueue = Volley.newRequestQueue(MainActivity.this);


                    startActivity(intent);
                }
                else{
                    Toast toast =Toast.makeText(MainActivity.this,TOAST_Notconnected,Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });
    }
    public boolean checkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        }

        else
            return false;
    }



}