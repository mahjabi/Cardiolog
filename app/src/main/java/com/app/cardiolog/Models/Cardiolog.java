package com.app.cardiolog.Models;

import android.text.format.Time;

import java.util.Date;
import java.util.Timer;

public class Cardiolog {

   String mDate,mTime;

   Integer Sys,Dia,Bpm;
   String Comment;

    public Cardiolog(String date, String time, Integer sys, Integer dia, Integer bpm, String comment) {
        mDate = date;
        mTime = time;
        Sys = sys;
        Dia = dia;
        Bpm = bpm;
        Comment = comment;
    }

    public Cardiolog(String date, String time, Integer sys, Integer dia, Integer bpm) {
        mDate = date;
        mTime = time;
        Sys = sys;
        Dia = dia;
        Bpm = bpm;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public Integer getSys() {
        return Sys;
    }

    public void setSys(Integer sys) {
        Sys = sys;
    }

    public Integer getDia() {
        return Dia;
    }

    public void setDia(Integer dia) {
        Dia = dia;
    }

    public Integer getBpm() {
        return Bpm;
    }

    public void setBpm(Integer bpm) {
        Bpm = bpm;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
