package org.opencv.javacv.facerecognition;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by lakeesh on 22/03/2015.
 */
public class ShowAttendance extends Activity {
    Button totalAttendance;
    TextView showAttendance,showAttName;
    String selectTable="",data="",name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showattendance);
        Bundle gotBasket=getIntent().getExtras();
        selectTable=gotBasket.getString("key");
        totalAttendance=(Button) findViewById(R.id.bTotalAttendance);
        showAttendance=(TextView) findViewById(R.id.tvShowAttendance);
        showAttName=(TextView) findViewById(R.id.tvShowAttName);

        try {
            InstattendDatabase info = new InstattendDatabase(this);
            info.open();
            name=info.showAttendName(selectTable);
            data = info.showAttendance(selectTable);

            info.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        showAttName.setText(name);
        showAttendance.setText(data);
        totalAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle basket=new Bundle();
                basket.putString("key",selectTable);

                Intent ourIntent = new Intent(ShowAttendance.this, TotalAttendance.class);
                ourIntent.putExtras(basket);
                startActivity(ourIntent);
            }
        });




    }
}
