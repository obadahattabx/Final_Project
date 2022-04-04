package edu.birzeit.projectpart1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import kotlin.reflect.KAnnotatedElement;

public class ProgresBarAnimation extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;
    public static ArrayList<Properties> proepertJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progres_bar_animation);
        progressBar=findViewById(R.id.progress_bar);
        textView=findViewById(R.id.text_View);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);
        progressAnimation();

    }


    public void progressAnimation(){
        ProgressAnimation anim =new ProgressAnimation(this,progressBar,textView,0f,100f);
        ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(ProgresBarAnimation.this);
        connectionAsyncTask.execute("https://api.npoint.io/54a4ca9faccb9d309d62");
        anim.setDuration(8000);


        progressBar.setAnimation(anim);
    }
}