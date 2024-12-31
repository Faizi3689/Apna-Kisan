package com.example.apnakissan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ListView listViewItems = findViewById(R.id.listViewItems);

        // Create a list of items with names and image resources
        List<Item> items = new ArrayList<>();
        items.add(new Item("SP GREEN", R.drawable.chemical));
        items.add(new Item("HEMARS CONE", R.drawable.chemcials4));
        items.add(new Item("SP GREEN", R.drawable.chemical2));
        items.add(new Item("POISON", R.drawable.chemicall));
        items.add(new Item("BLIGHT PLUS", R.drawable.chemical3));
        items.add(new Item("ORGAVIT", R.drawable.bio));
        items.add(new Item("ACELUM", R.drawable.bioo));
        items.add(new Item("GREEN SEEDS", R.drawable.ferti));
        items.add(new Item("SOLU K", R.drawable.fertilii));
        items.add(new Item("FULL GREEN", R.drawable.fertilizers));
        items.add(new Item("FERTIUM", R.drawable.fertilz));
        items.add(new Item("SOP", R.drawable.fertizi2));
        items.add(new Item("BUTA CLORE", R.drawable.rice));
        items.add(new Item("ZOKNI", R.drawable.ricespray));
        items.add(new Item("DAWIEW", R.drawable.ricet));
        items.add(new Item("CORUM", R.drawable.sprayy));
        items.add(new Item("UNI SOFT", R.drawable.sprayyy));
        items.add(new Item("BUNDI KUSH", R.drawable.sprry));
        items.add(new Item("Item 19", R.drawable.ricespray));
        items.add(new Item("BRINSH", R.drawable.bioo));
        // Set the custom adapter
        ItemAdapter adapter = new ItemAdapter(this, items);
        listViewItems.setAdapter(adapter);

        // Handle item clicks to navigate to ItemDetailActivity
        listViewItems.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(Shop.this, ItemDetail.class);
            intent.putExtra("itemName", items.get(position).getName());
            startActivity(intent);
        });
    }
}
