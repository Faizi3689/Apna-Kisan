package com.example.apnakissan;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.io.InputStream;

public class CropsD extends AppCompatActivity {

    private static final int IMAGE_PICK_CODE = 100;
    private static final int IMAGE_CAPTURE_CODE = 101;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 102;

    private ImageView imageView;
    private TextView resultTextView;
    private Bitmap uploadedBitmap;
    private LocalModel localModel = new LocalModel(); // Replaces ChatBot class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crops_d);

        imageView = findViewById(R.id.imageView);
        resultTextView = findViewById(R.id.resultTextView);
        Button uploadButton = findViewById(R.id.uploadButton);
        Button captureButton = findViewById(R.id.captureButton);

        // Set up click listener for the upload button
        uploadButton.setOnClickListener(v -> pickImageFromGallery());

        // Set up click listener for the capture button
        captureButton.setOnClickListener(v -> {
            // Check for camera permission before launching camera
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                captureImage();
            } else {
                // Request camera permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
            }
        });
    }

    // Open the gallery to pick an image
    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    // Launch camera to capture an image
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, IMAGE_CAPTURE_CODE);
        } else {
            Toast.makeText(this, "Camera not available", Toast.LENGTH_SHORT).show();
        }
    }

    // Handle the result from image selection or capture
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            try {
                if (requestCode == IMAGE_PICK_CODE) {
                    Uri imageUri = data.getData();
                    InputStream inputStream = getContentResolver().openInputStream(imageUri);
                    uploadedBitmap = BitmapFactory.decodeStream(inputStream);
                } else if (requestCode == IMAGE_CAPTURE_CODE) {
                    uploadedBitmap = (Bitmap) data.getExtras().get("data");
                }

                // Set the bitmap to the ImageView
                imageView.setImageBitmap(uploadedBitmap);

                // Detect the disease using the local model
                String result = localModel.detectDisease(uploadedBitmap, this);
                resultTextView.setText(result != null ? result : "No matching disease found.");

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error loading image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Handle the result of the permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with camera functionality
                captureImage();
            } else {
                // Permission denied, show a message to the user
                Toast.makeText(this, "Camera permission is required to capture images.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
