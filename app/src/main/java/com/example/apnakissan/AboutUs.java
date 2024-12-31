package com.example.apnakissan;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // Initialize the TextView (optional if you want to set text programmatically)
        TextView aboutUsTextView = findViewById(R.id.aboutUsTextView);

        // Set text dynamically if needed
        String aboutText = "Welcome to Apna Kissan App!\n\n" +
                "Our application is designed to support farmers and agriculturists by offering a platform where they can detect their crops disases through scaning or uploading images form gallery. Its also offering the weather feature which helps the user to check the weather conditions.The user can buy  agricultural products, find nearby agri-shops, and receive assistance with AI-powered tools. Here’s an overview of what Apna Kissan offers:\n\n" +
                "Disease Detector:Detect your diseases form the images which you upload or capturegiera nhi hy.\n" +
                "- Map: View and search the shops near about you.\n" +
                "- Soil Information: View and read the articals on the types of soils.\n" +
                "- Weather System: This feature provide the actual weather conditions in 24/7 hours.\n" +
                "- AI Agriculture Assistant: Get expert advice.\n" +
                "- Shop Activity: Access a variety of agricultural products. \n\n" +
                "We hope you find this app helpful for your farming needs. Thank you for using Apna Kissan!";

        // Optionally set the text here if you don't want to hardcode it in XML
        aboutUsTextView.setText(aboutText);
    }
}