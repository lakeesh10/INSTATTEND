package org.opencv.javacv.facerecognition;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class SelectSubject extends ListActivity {



    String classes[]={};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //FullScreen

        try {
            InstattendDatabase entry = new InstattendDatabase(SelectSubject.this);
            entry.open();
            classes=entry.getDataSubject();
            entry.close();

        }catch (Exception e){}
        //List Adapter
        setListAdapter(new ArrayAdapter<String>(SelectSubject.this, android.R.layout.simple_list_item_1, classes));
    }
    protected void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);
        String cheese=classes[position];
        try {
            Bundle basket=new Bundle();
            basket.putString("key",cheese);
            Class ourClass = Class.forName("org.opencv.javacv.facerecognition.FdActivity");
            Intent ourIntent = new Intent(SelectSubject.this, ourClass);
            ourIntent.putExtras(basket);
            startActivity(ourIntent);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }


}
