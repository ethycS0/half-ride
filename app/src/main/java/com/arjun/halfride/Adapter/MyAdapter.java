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

public class MyAdapter extends FirebaseRecyclerAdapter<model,MyAdapter.MyViewHolder> {

    private OnItemClickListener mListener;

    public MyAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
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
        holder.button.setText("Profile");

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,new DescriptionFragment(model.getName(),model.getLocation(),model.getOrganisation(),model.getDescription(),model.getUserId(),model.getNumber(),model.getEmail())).addToBackStack(null).commit();
            }

        });


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return  new MyViewHolder(view);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, location, organisation;
        Button button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.Uname);
            location = itemView.findViewById(R.id.Ulocation);
            organisation = itemView.findViewById(R.id.Uorganisation);
            button = itemView.findViewById(R.id.prof);


        }
    }

}