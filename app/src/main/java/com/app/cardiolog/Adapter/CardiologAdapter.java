package com.app.cardiolog.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.cardiolog.Models.Cardiolog;
import com.app.cardiolog.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CardiologAdapter extends RecyclerView.Adapter<CardiologAdapter.viewHolder>{

    ArrayList<Cardiolog> logList;
    Context context;



    public CardiologAdapter(ArrayList<Cardiolog> logList, Context context) {
        this.logList = logList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.show_log,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Cardiolog log = logList.get(position);
        Date date = log.getDate();
        String d = new SimpleDateFormat("dd-mm-yyyy").format(date);
        holder.mdate.setText(d);

        holder.msys.setText(log.getSys().toString());
        holder.mdia.setText(log.getDia().toString());
        holder.mbpm.setText(log.getBpm().toString());
        holder.mcomment.setText(log.getComment());
    }



    @Override
    public int getItemCount() {
        return 0;
    }


    public class viewHolder extends RecyclerView.ViewHolder{
        TextView mdate,msys,mdia,mbpm,mcomment;
        ImageView medit,mdelete;

        public viewHolder(@NonNull View itemView) {

            super(itemView);
            mdate= itemView.findViewById(R.id.date);
            msys= itemView.findViewById(R.id.sys_m);
            mdia= itemView.findViewById(R.id.dia_m);
            mbpm= itemView.findViewById(R.id.bpm_m);
            mcomment= itemView.findViewById(R.id.comment_m);
            medit= itemView.findViewById(R.id.sys_m);
            mdelete= itemView.findViewById(R.id.comment_m);
        }
    }




}
