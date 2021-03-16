package com.example.ua.kpi.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Item> items = new ArrayList<Item>();
    private ListView listView;

    private String selectedFilterName = "all";

    private String var = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpData();
        setUpList();
        initSearchWidgets();
        setUpOnClickListener();
    }

    private void initSearchWidgets() {
        SearchView searchView = findViewById(R.id.itemListSearch);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Item> filteredItems = new ArrayList<Item>();
                for (Item item: items) {
                    if (item.getName().toLowerCase().contains(s.toLowerCase())) {
                        filteredItems.add(item);
                    }
                }

                ItemAdapter adapter = new ItemAdapter(getApplicationContext(), 0, filteredItems);
                listView.setAdapter(adapter);
                return false;
            }
        });
    }

    private void setUpData() {
        Item plasticSpoon = new Item("0", "Plastic Spoon", "10", R.drawable.plastic_spoon);
        items.add(plasticSpoon);

        Item metalSpoon = new Item("1", "Metal Spoon", "50", R.drawable.metal_spoon);
        items.add(metalSpoon);

        Item goldSpoon = new Item("2", "Gold Spoon", "100", R.drawable.gold_spoon);
        items.add(goldSpoon);

        Item plasticCup = new Item("3", "Plastic Cup", "15", R.drawable.plastic_cup);
        items.add(plasticCup);

        Item ceramicCup = new Item("4", "Ceramic Cup", "60", R.drawable.ceramic_cup);
        items.add(ceramicCup);

        Item expensiveCup = new Item("5", "Expensive Cup", "125", R.drawable.expensive_cup);
        items.add(expensiveCup);
    }

    private void setUpList() {
        listView = findViewById(R.id.itemListView);

        ItemAdapter adapter = new ItemAdapter(getApplicationContext(), 0, items);
        listView.setAdapter(adapter);
    }

    private void filterListName(String status) {
        selectedFilterName = status;
        ArrayList<Item> filteredItems = new ArrayList();
        for (Item item: items) {
            if (item.getName().toLowerCase().contains(status)) {
                filteredItems.add(item);
            }
        }

        ItemAdapter adapter = new ItemAdapter(getApplicationContext(), 0, filteredItems);
        listView.setAdapter(adapter);
    }

    public void allFilterTapped(View view) {
        ItemAdapter adapter = new ItemAdapter(getApplicationContext(), 0, items);
        listView.setAdapter(adapter);
    }

    public void spoonFilterTapped(View view) {
        filterListName("spoon");
        var = "spoon";
    }

    public void cupFilterTapped(View view) {
        filterListName("cup");
        var = "cup";
    }

    private void setUpOnClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item selectedItem = (Item) (listView.getItemAtPosition(position));
                Intent showDetails = new Intent(getApplicationContext(), DetailActivity.class);
                showDetails.putExtra("id", selectedItem.getId());
                startActivity(showDetails);
            }
        });
    }

    public void smallPriceFilterTapped(View view) {
        ArrayList<Item> smallPriceItem = new ArrayList();
        for (Item item: items) {
            if (item.getName().toLowerCase().contains(var)) {
                if (Integer.valueOf(item.getPrice()) >= 0 && Integer.valueOf(item.getPrice()) <= 30) {
                    smallPriceItem.add(item);
                }
            }
        }

        ItemAdapter adapter = new ItemAdapter(getApplicationContext(), 0, smallPriceItem);
        listView.setAdapter(adapter);
    }

    public void mediumPriceFilterTapped(View view) {
        ArrayList<Item> mediumPriceItem = new ArrayList();
        for (Item item: items) {
            if (item.getName().toLowerCase().contains(var)) {
                if (Integer.valueOf(item.getPrice()) >= 31 && Integer.valueOf(item.getPrice()) <= 90) {
                    mediumPriceItem.add(item);
                }
            }
        }

        ItemAdapter adapter = new ItemAdapter(getApplicationContext(), 0, mediumPriceItem);
        listView.setAdapter(adapter);
    }

    public void expensivePriceFilterTapped(View view) {
        ArrayList<Item> expensivePriceItem = new ArrayList();
        for (Item item: items) {
            if (item.getName().toLowerCase().contains(var)) {
                if (Integer.valueOf(item.getPrice()) >= 91 && Integer.valueOf(item.getPrice()) <= 130) {
                    expensivePriceItem.add(item);
                }
            }
        }

        ItemAdapter adapter = new ItemAdapter(getApplicationContext(), 0, expensivePriceItem);
        listView.setAdapter(adapter);
    }
}