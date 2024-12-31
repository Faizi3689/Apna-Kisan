package com.example.apnakissan;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderConfirmation extends AppCompatActivity {
    private EditText editTextName, editTextPhone, editTextCard;
    private Button buttonSubmit, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextCard = findViewById(R.id.editTextCard);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonCancel = findViewById(R.id.buttonCancel);

        buttonSubmit.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String phone = editTextPhone.getText().toString().trim();
            String card = editTextCard.getText().toString().trim();

            if (name.isEmpty() || phone.isEmpty() || card.isEmpty()) {
                // Show a small toast if any field is empty
                Toast.makeText(this, "Please fill all the details!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Show full-screen dialog for order confirmation
            showOrderPlacedDialog();
        });

        buttonCancel.setOnClickListener(v -> finish()); // Close the activity
    }

    private void showOrderPlacedDialog() {
        // Create a full-screen dialog
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_order);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.setCancelable(false); // User cannot cancel it manually
        dialog.show();

        // After 2 seconds, redirect to ShopActivity and close the dialog
        new Handler().postDelayed(() -> {
            dialog.dismiss();
            Intent intent = new Intent(OrderConfirmation.this, Shop.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Close the current activity
        }, 2000); // 2-second delay
    }
}