package com.example.apnakissan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        TextView cartDetails = findViewById(R.id.cartDetails);
        Button confirmButton = findViewById(R.id.btnConfirm);

        String item = getIntent().getStringExtra("itemName");
        int quantity = getIntent().getIntExtra("quantity", 0);
        int price = getIntent().getIntExtra("price", 0);

        cartDetails.setText("Item: " + item + "\nQuantity: " + quantity + "\nTotal: " + price + " Rs");

        confirmButton.setOnClickListener(view -> {
            Intent intent = new Intent(CartActivity.this, OrderConfirmation.class);
            startActivity(intent);
        });
    }
}
