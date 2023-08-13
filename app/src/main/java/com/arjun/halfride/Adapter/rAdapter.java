package com.arjun.halfride.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.arjun.halfride.Fragments.DescriptionFragment;
import com.arjun.halfride.R;
import com.arjun.halfride.HelperClass.model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class rAdapter extends FirebaseRecyclerAdapter<model,rAdapter.MyViewHolder> {

    private OnItemClickListener mListener;

    public rAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull final model model) {

        holder.name.setText(model.getName());
        holder.location.setText(model.getLocation());
        holder.organisation.setText(model.getOrganisation());
        holder.number.setText(model.getNumber());
        holder.email.setText(model.getEmail());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2,parent,false);
        return  new MyViewHolder(view);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, location, organisation, number, email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.Uname);
            location = itemView.findViewById(R.id.Ulocation);
            organisation = itemView.findViewById(R.id.Uorganisation);
            number = itemView.findViewById((R.id.uNumber));
            email = itemView.findViewById(R.id.uEmail);

        }
    }

}