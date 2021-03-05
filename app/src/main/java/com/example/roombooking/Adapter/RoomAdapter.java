package com.example.roombooking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roombooking.Model.Room;
import com.example.roombooking.R;
import com.example.roombooking.View.Customer.RoomDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    Context context;
    List<Room> roomList;

    public RoomAdapter(Context context) {
        this.context = context;
        this.roomList = new ArrayList<>();
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.room_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Room room = roomList.get(position);

        holder.capacity.setText(String.valueOf(room.getCapacity()));
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView capacity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            capacity = itemView.findViewById(R.id.capacityTxt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Intent
            int position = getAdapterPosition();
            Intent intent = new Intent(context, RoomDetailActivity.class);
            intent.putExtra("room_id", roomList.get(position).getId());
            context.startActivity(intent);
        }
    }
}
