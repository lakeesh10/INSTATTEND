package org.opencv.javacv.facerecognition;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lakeesh on 19/03/2015.
 */
public class AddSubject extends Activity implements View.OnClickListener{
    EditText etAddSub;
    Button addSub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.addsubject);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addSub=(Button) findViewById(R.id.bAddSubject);
        etAddSub=(EditText) findViewById(R.id.etAddSubject);
        addSub.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bAddSubject:
                try {


                    String name = etAddSub.getText().toString();


                    InstattendDatabase entry = new InstattendDatabase(AddSubject.this);
                    entry.open();
                    entry.createSubjectEntry(name);
                    entry.createSujectTable(name);

                    entry.close();

                }catch (Exception e){

                }
                finally {

                    finish();

                }
                break;
        }
    }
}
