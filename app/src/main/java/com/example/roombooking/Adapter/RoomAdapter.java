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
import com.example.roombooking.View.Admin.EditRoomActivity;
import com.example.roombooking.View.Admin.ManageRoomActivity;
import com.example.roombooking.View.Customer.RoomDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    Context context;
    List<Room> roomList;
    String userRole;
    int userId;

    public RoomAdapter(Context context, int userId, String userRole) {
        this.context = context;
        this.roomList = new ArrayList<>();
        this.userId = userId;
        this.userRole = userRole;
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
        holder.name.setText(String.valueOf(room.getName()));
        holder.type.setText(String.valueOf(room.getType()));

    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView capacity, name, type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            capacity = itemView.findViewById(R.id.capacityTxt);
            name = itemView.findViewById(R.id.nameTxt);
            type = itemView.findViewById(R.id.typeTxt);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Intent
            int position = getAdapterPosition();

            if (userRole.equalsIgnoreCase("admin")){
                Intent adminIntent = new Intent(context, ManageRoomActivity.class);
                adminIntent.putExtra("room_id", roomList.get(position).getId());
                adminIntent.putExtra("userId", userId);
                adminIntent.putExtra("userRole", userRole);
                context.startActivity(adminIntent);
            }
            else if (userRole.equalsIgnoreCase("user")){
                Intent intent = new Intent(context, RoomDetailActivity.class);
                intent.putExtra("room_id", roomList.get(position).getId());
                intent.putExtra("userId", userId);
                intent.putExtra("userRole", userRole);
                context.startActivity(intent);
            }
        }
    }
}
