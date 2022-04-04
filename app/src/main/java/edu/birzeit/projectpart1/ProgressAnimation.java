package edu.birzeit.projectpart1;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressAnimation extends Animation {
    private Context context;
    private ProgressBar progressBar;
    private TextView textView;
    private float from;
    private float to;

    public ProgressAnimation(Context context, ProgressBar progressBar , TextView textView, float from, float to){
        this.context=context;
        this.progressBar=progressBar;
        this.textView=textView;
        this.from=from;
        this.to=to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value =from +(to - from) *interpolatedTime;
        progressBar.setProgress((int)value);
        textView.setText((int)value +"%");

        if(value ==to){

            // insert image to property
            for(int i=0;i<ProgresBarAnimation.proepertJson.size();i++) {
                new DownloadImageUrlTask(ProgresBarAnimation.proepertJson.get(i))
                        .execute(ProgresBarAnimation.proepertJson.get(i).getUrlImage());
            }


            context.startActivity(new Intent(context,MainActivity.class));
        }

    }
}
