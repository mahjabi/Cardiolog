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

import java.text.DateFormat;
import java.util.Calendar;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.app.cardiolog.MainActivity;
import com.app.cardiolog.MyDBHelper;
import com.app.cardiolog.R;

import java.text.SimpleDateFormat;

public class DialogFragment extends android.app.DialogFragment {
    /**
     * this class method is for the add dialog fragment
     */
    private static final String TAG = "DialogFragment";
    public  Context thiscontext;

    public interface OnInputListener {
        void sendInput(int sysval,int diaval,int bpm,String comment);
    }
    public OnInputListener mOnInputListener;

    EditText sysInput,diaInput,bpmInput,commentInput,Date,Time;
    Button mButton;
TextView comIn;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        /**
         * this is method where we show the dialog box with the input sections
         */
        super.onCreateView(inflater, container, savedInstanceState);

        thiscontext= this.getContext();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        View v= inflater.inflate(R.layout.inputfragment,container,false);
        sysInput=v.findViewById(R.id.systolic);
        diaInput=v.findViewById(R.id.diastolic);
        bpmInput=v.findViewById(R.id.bpm_in);
        commentInput=v.findViewById(R.id.comment_in);
        mButton=v.findViewById(R.id.input_btn);
        Date=v.findViewById(R.id.date);
        Time=v.findViewById(R.id.time);
 comIn=v.findViewById(R.id.comment_m);

        Date.setInputType(InputType.TYPE_NULL);
        Time.setInputType(InputType.TYPE_NULL);

        Bundle bundle =new Bundle();
        bundle.putInt("sysval",sysInput.getInputType());
        bundle.putInt("diaval",diaInput.getInputType());
        bundle.putInt("bpmval",bpmInput.getInputType());
        bundle.putString("commentval",commentInput.getText().toString());


        Date.setOnClickListener(new View.OnClickListener() {
            /**
             * this click listener is to show the calender and date dialog
             * @param view
             */
            @Override
            public void onClick(View view) {
                showDateDialog(Date);
            }


        });

        Time.setOnClickListener(new View.OnClickListener() {
            /**
             * this click listener is to show the clock and time input
             * @param view
             */
            @Override
            public void onClick(View view) {
                showTimeDialog(Time);
            }
        });




        mButton.setOnClickListener(new View.OnClickListener() {
            /**
             * this click listener method is for the feedback for the users about what they should have inserted
             * @param view
             */
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(sysInput.getText().toString().trim())||TextUtils.isEmpty(diaInput.getText().toString().trim())||TextUtils.isEmpty(bpmInput.getText().toString().trim()))
                {
                    if(TextUtils.isEmpty(sysInput.getText().toString().trim())){
                        Toast.makeText(thiscontext,"Systolic Pressure Field is Required",Toast.LENGTH_LONG).show();
                    }
                    if(TextUtils.isEmpty(diaInput.getText().toString().trim())){
                        Toast.makeText(thiscontext,"Diastolic Pressure Field is Required",Toast.LENGTH_LONG).show();
                    }
                    if(TextUtils.isEmpty(bpmInput.getText().toString().trim())){
                        Toast.makeText(thiscontext,"Beats Per Minute Field is Required",Toast.LENGTH_LONG).show();
                    }

                }

                else {
                    int sysval = Integer.parseInt(sysInput.getText().toString());
                    int diaval=Integer.parseInt(diaInput.getText().toString());
                    int bpmval =Integer.parseInt(bpmInput.getText().toString());
                    String com = null,d=null,t=null;
                 String dateval,timeval;
                    if(TextUtils.isEmpty(commentInput.getText().toString().trim())){

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

                    if(TextUtils.isEmpty(Date.getText().toString().trim())||TextUtils.isEmpty(Time.getText().toString().trim())){
                        Calendar calendar = Calendar.getInstance();

                        dateval = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

                        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                       timeval = dateFormat.format(calendar.getTime());

                    }
                    else{
                        dateval = Date.getText().toString();
                      timeval = Time.getText().toString();
                    }
                    MyDBHelper mydb = new MyDBHelper(thiscontext);
                    mydb.addRecord(sysval,diaval,bpmval,dateval,timeval,com);

                    //mOnInputListener.sendInput(sysval,diaval,com);
                    getDialog().dismiss();
                }


            }
        });

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showTimeDialog(EditText time) {
        Calendar calendar= Calendar.getInstance();
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            /**
             * date input method
             */
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
        /**
         * shoe date
         */
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