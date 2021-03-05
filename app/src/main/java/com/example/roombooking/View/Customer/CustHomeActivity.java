package com.example.roombooking.View.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.roombooking.R;

public class CustHomeActivity extends AppCompatActivity {

    CardView bookCard, editCard, bookedCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_home);

        bookCard = findViewById(R.id.book_card);
        editCard = findViewById(R.id.edit_card);
        bookedCard = findViewById(R.id.booked_card);

        Intent intentId = getIntent();
        int id = intentId.getIntExtra("userId", 0);

        bookCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bookIntent = new Intent(CustHomeActivity.this, BookActivity.class);
                intentId.putExtra("userId", id);

                startActivity(bookIntent);
            }
        });

        editCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(CustHomeActivity.this, EditActivity.class);
                editIntent.putExtra("userId", id);

                startActivity(editIntent);

            }
        });

        bookedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bookedIntent = new Intent(CustHomeActivity.this, BookedRoomActivity.class);
                bookedIntent.putExtra("userId", id);

                startActivity(bookedIntent);
            }
        });


    }
}