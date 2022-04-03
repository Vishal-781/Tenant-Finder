package com.example.tanant_finder;

import    android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<user> list;

    public MyAdapter(Context context, ArrayList<user> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.property_list,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        user User= list.get(position);
        holder.details.setText(User.getProperty_type());
        holder.address.setText(User.getAddress());
        holder.rent.setText(User.getRent());
        holder.contact_no.setText(User.getContact_no());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static class  MyViewHolder extends RecyclerView.ViewHolder {
           TextView details,address,rent,contact_no;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            details=itemView.findViewById(R.id.textdetails);
            address=itemView.findViewById(R.id.textaddress);
            rent=itemView.findViewById(R.id.textrent);
            contact_no=itemView.findViewById(R.id.textcontact);



        }
    }
}
