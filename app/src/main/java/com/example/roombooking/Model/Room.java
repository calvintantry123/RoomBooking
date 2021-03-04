package com.example.roombooking.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Room {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("capacity")
    @Expose
    private int capacity;

    public Room(int capacity) {
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
