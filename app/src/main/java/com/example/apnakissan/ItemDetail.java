package com.example.apnakissan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ItemDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        TextView itemName = findViewById(R.id.itemName);
        EditText quantityInput = findViewById(R.id.quantityInput);
        TextView priceView = findViewById(R.id.priceView);
        Button addToCartButton = findViewById(R.id.btnAddToCart);

        String item = getIntent().getStringExtra("itemName");
        itemName.setText(item);

        addToCartButton.setOnClickListener(view -> {
            int quantity = Integer.parseInt(quantityInput.getText().toString());
            int price = quantity * 100;  // Assume 100 Rs per item
            priceView.setText("Price: " + price + " Rs");

            Intent intent = new Intent(ItemDetail.this, CartActivity.class);
            intent.putExtra("itemName", item);
            intent.putExtra("quantity", quantity);
            intent.putExtra("price", price);
            startActivity(intent);
        });
    }
}