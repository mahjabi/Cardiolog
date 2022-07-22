package com.app.cardiolog.Models;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.cardiolog.Adapter.CardiologAdapter;
import com.app.cardiolog.HistoryActivity;
import com.app.cardiolog.MyDBHelper;
import com.app.cardiolog.R;

import java.text.SimpleDateFormat;

public class Updatefragment extends android.app.DialogFragment {
    private static final String TAG = "UpdateFragment";
    public  Context thiscontext;

    public interface OnInputListener {
        void sendInput(int sysval,int diaval,int bpm,String comment);
    }
    public OnInputListener mOnInputListener;

    EditText sysInput,diaInput,bpmInput,commentInput,Date,Time;
    Button mButton,delbtn;
    TextView comIn;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        thiscontext= this.getContext();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        View v= inflater.inflate(R.layout.updatefragment,container,false);
        sysInput=v.findViewById(R.id.systolic);
        diaInput=v.findViewById(R.id.diastolic);
        bpmInput=v.findViewById(R.id.bpm_in);
        commentInput=v.findViewById(R.id.comment_in);
        mButton=v.findViewById(R.id.update_btn);
        delbtn=v.findViewById(R.id.del_btn);
        Date=v.findViewById(R.id.date);
        Time=v.findViewById(R.id.time);
        comIn=v.findViewById(R.id.comment_m);

        Date.setInputType(InputType.TYPE_NULL);
        Time.setInputType(InputType.TYPE_NULL);

        Bundle bundle =getArguments();
        String sys= bundle.getString("sysval");
        String dia = bundle.getString("diaval");
        String bpm = bundle.getString("bpm");
        String comment = bundle.getString("comment");
        String dateval = bundle.getString("date");
        String timeval = bundle.getString("time");
        String id= bundle.getString("id");
        diaInput.setText(dia);
        sysInput.setText(sys);
        bpmInput.setText(bpm);
        commentInput.setText(comment);
        Date.setText(dateval);
        Time.setText(timeval);



        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(Date);
            }


        });

        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeDialog(Time);
            }
        });


       delbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               MyDBHelper mydb = new MyDBHelper(thiscontext);

               mydb.deleteOneRow(id);


               Intent intent= new Intent(thiscontext,HistoryActivity.class);
               startActivity(intent);
               getDialog().dismiss();
           }
       });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        int sysval,diaval,bpmval;
                        String date,time;
                    if(TextUtils.isEmpty(sysInput.getText().toString().trim())){
                      sysval = Integer.parseInt(sys);
                    }
                    else{
                         sysval = Integer.parseInt(sysInput.getText().toString());
                    }
                    if(TextUtils.isEmpty(diaInput.getText().toString().trim())){
                       diaval=Integer.parseInt(dia);
                    }
                    else{
                        diaval=Integer.parseInt(diaInput.getText().toString());
                    }
                    if(TextUtils.isEmpty(bpmInput.getText().toString().trim())){
                       bpmval=Integer.parseInt(bpm);
                    }
                    else{
                     bpmval=Integer.parseInt(bpmInput.getText().toString());
                    }
                    if(TextUtils.isEmpty(Date.getText().toString().trim())){
                      date=dateval;
                    }
                    else{
                        date = Date.getText().toString();
                    }
                    if(TextUtils.isEmpty(Time.getText().toString().trim())){
                     time = timeval;
                    }
                    else{
                         time = Time.getText().toString();
                    }


                    String com = null;


                    if(comment.equals(commentInput.getText().toString())){

                        if((sysval>=90 && sysval<140) && (diaval>=60 && diaval<=90 )){
                            com="Normal";

                        }
                        else if((sysval>=140 && sysval<180) || (diaval>90 && diaval<120 )){
                            com="High Blood Pressure";

                        }
                        else if(sysval>=180 || diaval>=120 ){
                            com="Hypertensive Crisis,Consult Doctor";

                        }
                        else if(sysval<90 || diaval<60){
                            com="Low Blood Pressure";

                        }

                    }
                    else{
                        com=commentInput.getText().toString();
                    }
                    MyDBHelper mydb = new MyDBHelper(thiscontext);

                  mydb.updateData(id,sysval,diaval,bpmval,date,com,time);


                    Intent intent= new Intent(thiscontext,HistoryActivity.class);
                    startActivity(intent);
                getDialog().dismiss();

                }


            }
        );

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showTimeDialog(EditText time) {
        Calendar calendar= Calendar.getInstance();
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourofDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY,hourofDay);
                calendar.set(Calendar.MINUTE,minute);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm a");
                Time.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new TimePickerDialog(thiscontext,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
    }


    private void showDateDialog(EditText date) {
        Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-mm-yyyy");
                Date.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new DatePickerDialog(thiscontext,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

    }



}




