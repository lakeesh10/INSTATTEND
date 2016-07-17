package org.opencv.javacv.facerecognition;

/**
 * Created by lakeesh on 19/03/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.SQLException;

import javax.security.auth.Subject;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by lakeesh on 18/03/2015.
 */
public class InstattendDatabase {

    public static final String SubjectTableSubName = "Subject_name";
    public static final String StudentTableStudName = "Student_name";
    public static final String SubjectTableTime = "Time";
    public static final String TotalAttendancetotal = "Total";
    //public static final String time = "person_name";
    public static final String totalTabletotal = "attendance";
    private static final String DATABASE_NAME = "InstattendDatabase";
    private static final String AddSubject = "AddSubject";
    private static final String Student = "Student";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public InstattendDatabase(Context c) {
        ourContext = c;
    }
    public InstattendDatabase open() throws SQLException{
        ourHelper = new DbHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return this;
    }

    public void close() throws SQLException{
        ourHelper.close();
    }


    public long createSubjectEntry(String name) {
        ContentValues cv=new ContentValues();
        cv.put(SubjectTableSubName,name);
        return ourDatabase.insert(AddSubject,null,cv);

    }


    public long createStudentEntry(String name) {
        ContentValues cv=new ContentValues();
        cv.put(StudentTableStudName,name);
        return ourDatabase.insert(Student,null,cv);

    }


    public String[] getDataSubject() throws SQLException{
        String[] columns=new String[]{SubjectTableSubName};
        Cursor c=ourDatabase.query(AddSubject,columns,null,null,null,null,null);

        int iName=c.getColumnIndex(SubjectTableSubName);
        int i=0,count=0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            count++;
        }
        String[] result=new String[count];
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result[i]=c.getString(iName);
            i++;

        }
        return result;
    }
    public String[] getDataStudent() {
        String[] columns=new String[]{StudentTableStudName};
        Cursor c=ourDatabase.query(Student,columns,null,null,null,null,null);

        int iName=c.getColumnIndex(StudentTableStudName);
        int i=0,count=0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            count++;
        }
        String[] result=new String[count];
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result[i]=c.getString(iName);
            i++;

        }
        return result;

    }


    public void deleteEntry(long l1) throws SQLException{
        ourDatabase.delete(AddSubject,SubjectTableSubName+"="+ l1,null);

    }



    public void createSujectTable(String Subjectname) {

        String[] columns=new String[]{StudentTableStudName};
        Cursor c=ourDatabase.query(Student,columns,null,null,null,null,null);

        int iName=c.getColumnIndex(StudentTableStudName);
        int i=0,count=0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            count++;
        }

        String[] studentList=new String[count+1];
        String queryexe="";
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            studentList[i]=c.getString(iName);
            i++;
        }
        //queryexe="CREATE TABLE IF NOT EXISTS "+Subjectname+" ( "+SubjectTableTime+" TEXT NOT NULL);";
        //ourDatabase.execSQL(queryexe);
        queryexe="CREATE TABLE IF NOT EXISTS "+Subjectname+" ( "+SubjectTableTime+" TEXT NOT NULL, ";
        for(i=0;i<count-1;i++)
        {
            queryexe=queryexe+studentList[i]+" TEXT NOT NULL DEFAULT '0',";
        }
        queryexe=queryexe+studentList[count-1]+" TEXT NOT NULL DEFAULT '0');";
       ourDatabase.execSQL(queryexe);
        /*String total="CREATE TABLE IF NOT EXISTS "+Subjectname+"TOTAL ( "+StudentTableStudName+" TEXT NOT NULL, "+TotalAttendancetotal+" TEXT NOT NULL DEFAULT '0');";
        ourDatabase.execSQL(total);
        for(i=0;i<count;i++)
        {
            ContentValues cv=new ContentValues();
            cv.put(StudentTableStudName,studentList[i]);
            ourDatabase.insert(Subjectname+"TOTAL",null,cv);
        }*/

    }



    public String giveAttendance (String pName, String column, String selectTable) {

        ContentValues cv=new ContentValues();
        String i="1";
        cv.put(pName,i);
       //ourDatabase.execSQL("UPDATE "+selectTable+" SET "+pName+"= '1' WHERE "+SubjectTableTime+"="+column);
        ourDatabase.update(selectTable, cv, SubjectTableTime + "='" + column+"'", null);
        return pName;


    }



  public void createColumn(String column, String selectTable) {

        ContentValues cv=new ContentValues();
        cv.put(SubjectTableTime,column);
        ourDatabase.insert(selectTable,null,cv);
        //ourDatabase.execSQL("ALTER TABLE "+selectTable+" ADD COLUMN "+column+" NUMBER");
    }



    public String showAttendance(String selectTable) {
        String result="";


        String[] columns=new String[]{StudentTableStudName};
        Cursor c=ourDatabase.query(Student,columns,null,null,null,null,null);

        int iName=c.getColumnIndex(StudentTableStudName);
        int i=0,count=0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            count++;
        }

        String[] studentList=new String[count+1];
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            studentList[i]=c.getString(iName);
            i++;
        }

   // String[] studentList=new String[]{SubjectTableTime};
     Cursor cursor=ourDatabase.query(selectTable,studentList,null,null,null,null,null);

       // int cusorStudent[]=new int[count],j=0;
       int j;
        int[] cursorStudent=new int[count+1];
        for(j=0;j<count;j++) {
            cursorStudent[j] = cursor.getColumnIndex(studentList[j]);
        }

        for(j=0;j<count;j++){
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){

                result=result+cursor.getInt(cursorStudent[j])+" " ;
            }
            result=result+"\n";

        }
        return result;


    }

    public String showAttendName(String selectTable) {
        String[] columns=new String[]{StudentTableStudName};
        Cursor c=ourDatabase.query(Student,columns,null,null,null,null,null);

        int iName=c.getColumnIndex(StudentTableStudName);
        int i=0,count=0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            count++;
        }
        String result="";
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result=result+c.getString(iName)+"\n";
            i++;

        }
        return result;
    }

    public String TotalAttendance(String selectTable, String student) {
        String result="";
        String[] studentarr=new String[]{student};
        Cursor cursor=ourDatabase.query(selectTable,studentarr,null,null,null,null,null);

        // int cusorStudent[]=new int[count],j=0;


       int cursorStudent=cursor.getColumnIndex(student);

        int re, count=0;

            for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                re=cursor.getInt(cursorStudent) ;
                count=count+re;
            }
            result=String.valueOf(count);


        return result;



    }

    public String NoOfAttendance(String selectTable) {
        String[] columns=new String[]{SubjectTableTime};
        Cursor c=ourDatabase.query(selectTable,columns,null,null,null,null,null);

        int iName=c.getColumnIndex(SubjectTableTime);
        int i=0,count=0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            count++;
        }
        String result=String.valueOf(count);
        return result;

    }


    private static class DbHelper extends SQLiteOpenHelper{


        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "+AddSubject+" ("+
                            SubjectTableSubName + " TEXT NOT NULL UNIQUE);"
            );
            db.execSQL("CREATE TABLE "+ Student+" ("+
                            StudentTableStudName + " TEXT NOT NULL UNIQUE);"
            );

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXIST "+AddSubject);
            db.execSQL("DROP TABLE IF EXIST "+Student);
            onCreate(db);
        }
    }

}
