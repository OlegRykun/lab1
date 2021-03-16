package com.example.ua.kpi.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    Item selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSelectedItem();
        setValues();

    }

    private void getSelectedItem() {
        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");
        selectedItem = MainActivity.items.get(Integer.valueOf(parsedStringID));
    }

    private void setValues() {
        TextView name = findViewById(R.id.item_name);
        TextView price = findViewById(R.id.item_price);
        ImageView image = findViewById(R.id.item_image);

        name.setText(selectedItem.getName());
        price.setText(selectedItem.getPrice());
        image.setImageResource(selectedItem.getImage());
    }
}