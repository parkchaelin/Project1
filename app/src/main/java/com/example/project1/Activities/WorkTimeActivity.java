package com.example.project1.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project1.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkTimeActivity extends AppCompatActivity {
    public Context context;
    private Intent intent;
    private String startTimeText;
    private String endTimeText;
    private TextView ttl;
    private TextView stt;
    private TextView edt;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private Date startTime;
    private Date endTime;
    private long restTime;
    EditText editText;
    TextView time;
    Button button;
    Cursor cs;
    String myText;
    String setText;
    String setText1;
    String showText;
    View view;

    String dbName = "sample.db";
    int dbMode = Context.MODE_PRIVATE;
    SQLiteDatabase myDB;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_time);

        myDB = openOrCreateDatabase(dbName,dbMode,null);
        ttl = (TextView) findViewById(R.id.ttl);
        stt = (TextView) findViewById(R.id.stt);
        edt = (TextView) findViewById(R.id.edt);
        time = (TextView) findViewById(R.id.time);
        button = (Button) findViewById(R.id.callButton);
        editText = (EditText) findViewById(R.id.editText);

        intent = getIntent();
        startTimeText = intent.getExtras().getString("tempstartTimeText");
        endTimeText = intent.getExtras().getString("tempendTimeText");

        cs = this.myDB.rawQuery("SELECT * FROM TIME_T", null);
        while (cs.moveToNext()) {
            if (cs.getString(0).equals(endTimeText)) {
                setText1 = cs.getString(3);
            }
        }
        cs.close();
        ttl.setText(setText1);
        stt.setText(startTimeText);
        edt.setText(endTimeText);

        cs = this.myDB.rawQuery("SELECT * FROM TIME_T", null);
        while (cs.moveToNext()) {
            if (cs.getString(0).equals(startTimeText)) {
                setText = cs.getString(2);
            }
        }
        cs.close();

        editText.setText(setText);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                myText = editText.getText().toString();
//                String sqlInsert = "INSERT INTO TIME_T (TIME, REST) VALUES ('" + startTimeText + "', '" + myText + "')";

                Cursor cs = myDB.rawQuery("SELECT * FROM TIME_T", null);
                while (cs.moveToNext()) {
                    if (cs.getString(0).equals(endTimeText)) {
                        String sqlInsert = "UPDATE TIME_T SET REST = '" + myText + "' WHERE TIME = '" + endTimeText + "'";
                        myDB.execSQL(sqlInsert);
                    }
                }
                cs.close();


                if (editText.getText().toString().equals("")) {editText.setText("0");}
                restTime = Integer.parseInt(editText.getText().toString());

                try{
                    startTime = format.parse(startTimeText);
                    endTime = format.parse(endTimeText);
                } catch(ParseException e){
                    e.printStackTrace();
                }

                long diff = endTime.getTime() - startTime.getTime() - restTime*1000*60;
                long hour = diff/1000/60/60;
                diff = diff - hour*1000*60*60;
                long min = diff/1000/60;
                diff = diff-min*1000*60;
                long sec = diff/1000;

                if(hour == 0 ){
                    if(min == 0)
                        time.setText(sec +"초");
                    else
                        time.setText(min+"분  " + sec +"초");
                }
                else {
                    time.setText(hour+ "시  " + min+"분  " + sec +"초");
                }

            }
        });

    }

    void show()
    {
        final EditText edittext = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("TITLE을 수정하세요.");
        builder.setMessage("Title");
        builder.setView(edittext);
        builder.setPositiveButton("입력",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        showText = edittext.getText().toString();
                        ttl.setText(showText);
                        String sqlInsert = "UPDATE TIME_T SET TITLE = '" + showText + "' WHERE TIME = '" + endTimeText + "'";
                        myDB.execSQL(sqlInsert);
                    }
                });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }

}
