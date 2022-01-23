package edu.birzeit.projectpart1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button buttongetstart;
    private static final String TOAST_connected = "Succefull connected ";
    private static final String TOAST_Notconnected = "Faild connection ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttongetstart=(Button)findViewById(R.id.getstart);
        String url="http://www.mocky.io/v2/5b4e6b4e3200002c009c2a44";
        buttongetstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkConnection()) {
                    Toast toast =Toast.makeText(MainActivity.this,TOAST_connected,Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(MainActivity.this, Login.class);
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