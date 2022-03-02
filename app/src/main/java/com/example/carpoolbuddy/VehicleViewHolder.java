package com.example.carpoolbuddy;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class VehicleViewHolder extends RecyclerView.ViewHolder {
//    public class VehicleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    protected TextView nameText;
    protected TextView statusText;
//    VehicleRecyclerViewAdapter.OnNoteListener onNoteListener;


    public VehicleViewHolder(@NonNull View itemView) {
        super(itemView);
        nameText = (itemView).findViewById(R.id.nameTextView);
        statusText = (itemView).findViewById(R.id.statusTextView);

    }


//
    public ConstraintLayout getLayout(){
        return itemView.findViewById(R.id.rowLayout);
    }
}
