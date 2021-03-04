package com.example.roombooking.View.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.roombooking.R;

public class CustHomeActivity extends AppCompatActivity {

    CardView bookCard, editCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_home);

        bookCard = findViewById(R.id.book_card);
        editCard = findViewById(R.id.edit_card);

        bookCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bookIntent = new Intent(CustHomeActivity.this, BookActivity.class);
                startActivity(bookIntent);
            }
        });

        editCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(CustHomeActivity.this, EditActivity.class);
                startActivity(editIntent);

            }
        });
    }
}