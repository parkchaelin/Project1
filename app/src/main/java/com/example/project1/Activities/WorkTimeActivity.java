package com.example.project1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project1.R;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkTimeActivity extends AppCompatActivity {
    private Intent intent;
    private String startTimeText;
    private String endTimeText;
    private TextView stt;
    private TextView edt;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private Date startTime;
    private Date endTime;
    private long restTime;
    EditText editText;
    TextView time;
    Button button;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_time);
        stt = (TextView) findViewById(R.id.stt);
        edt = (TextView) findViewById(R.id.edt);
        time = (TextView) findViewById(R.id.time);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);

        intent = getIntent();
        startTimeText = intent.getExtras().getString("startTimeText");
        endTimeText = intent.getExtras().getString("endTimeText");
        stt.setText(startTimeText);
        edt.setText(endTimeText);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
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
}
