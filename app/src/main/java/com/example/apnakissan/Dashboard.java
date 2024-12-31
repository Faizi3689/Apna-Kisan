package com.example.apnakissan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
 ImageView scan,weather,advisor,soilissue,logout,map,shop,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        scan = findViewById(R.id.scan);
        weather = findViewById(R.id.weatherk);
        advisor = findViewById(R.id.agrok);
        soilissue = findViewById(R.id.soilk);
        logout = findViewById(R.id.logout1);
        map = findViewById(R.id.maps);
        shop = findViewById(R.id.shop);
        about = findViewById(R.id.about);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,CropsD.class));
            }
        });
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Weather.class));
            }
        });
        advisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, AgroAdvisor.class));
            }
        });
        soilissue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Soilinfo.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, LogOut.class));
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Maps.class));
            }
        });
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Shop.class));
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, AboutUs.class));
            }
        });

        ImageSlider imageSlider = findViewById(R.id.imageslider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.ht, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.cs, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.frm, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.scs, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.tc, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}