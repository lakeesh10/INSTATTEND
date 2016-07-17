package org.opencv.javacv.facerecognition;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by lakeesh on 26/03/2015.
 */
public class TotalAttendance extends Activity {
    TextView tvTotalName,tvTotalAttend;
    String selectTable="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.totalattendance);

        Bundle gotBasket=getIntent().getExtras();
        selectTable=gotBasket.getString("key");

        tvTotalName=(TextView)findViewById(R.id.tvTotalName);
        tvTotalAttend=(TextView)findViewById(R.id.tvTotalAttend);
        String name="StudentName"+"\n\n";
        String data="Out Of ";
        String[] Student={};
        int i=0;
        try {
            InstattendDatabase info = new InstattendDatabase(this);
            info.open();
            name=name+info.showAttendName(selectTable);
            data=data+info.NoOfAttendance(selectTable)+"\n\n";
            Student=info.getDataStudent();
            for(i=0;i<Student.length;i++) {
                data = data+"      "+info.TotalAttendance(selectTable,Student[i])+"\n";
            }

            info.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        tvTotalName.setText(name);
        tvTotalAttend.setText(data);
    }
}
