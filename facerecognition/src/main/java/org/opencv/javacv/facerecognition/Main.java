package org.opencv.javacv.facerecognition;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import us.feras.ecogallery.EcoGallery;

/**
 * Created by lakeesh on 09/03/2015.
 */
public class Main extends Activity  {
    ImageView attendance,addSub,show;
    ImageView add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initialize();
        addSub.setImageResource(R.drawable.addsub);
        addSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ad=new Intent(Main.this,AddSubject.class);
                startActivity(ad);
            }
        });
        attendance.setImageResource(R.drawable.attendance);

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent attend=new Intent(Main.this,SelectSubject.class);
                startActivity(attend);
            }
        });
        add.setImageResource(R.drawable.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ad=new Intent(Main.this,Find.class);
                startActivity(ad);
            }
        });
        add.setImageResource(R.drawable.add);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ad=new Intent(Main.this,SelectSubjectForShow.class);
                startActivity(ad);
            }
        });



    }
    private void initialize()
    {
        attendance=(ImageView) findViewById(R.id.attendance);
        add=(ImageView) findViewById(R.id.addremove);
        addSub=(ImageView) findViewById(R.id.addsub);
        show=(ImageView) findViewById(R.id.showatt);


    }


}
