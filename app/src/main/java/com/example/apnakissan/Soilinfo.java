package com.example.apnakissan;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Soilinfo extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;
    DataClass androidData;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soilinfo);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Soilinfo.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataList = new ArrayList<>();

        androidData = new DataClass("Alluvial Soil", R.string.Alluvial_soil, "Alluvial", R.drawable.alluvial);
        dataList.add(androidData);

        androidData = new DataClass("Clay Soil", R.string.Clayey_soils, "Clayey", R.drawable.clay);
        dataList.add(androidData);

        androidData = new DataClass("Laterite Soil", R.string.Laterite_soil, "Laterite", R.drawable.laterite);
        dataList.add(androidData);

        androidData = new DataClass("Loamy Soil", R.string.Loamy_soil, "Loamy", R.drawable.loamy);
        dataList.add(androidData);

        androidData = new DataClass("Sandy Loam", R.string.Sandy_loam, "Loam ", R.drawable.sandyloam);
        dataList.add(androidData);

        adapter = new MyAdapter(Soilinfo.this, dataList);
        recyclerView.setAdapter(adapter);
    }

    private void searchList(String text){
        List<DataClass> dataSearchList = new ArrayList<>();
        for (DataClass data : dataList){
            if (data.getDataTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()){
            Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }
    }
}