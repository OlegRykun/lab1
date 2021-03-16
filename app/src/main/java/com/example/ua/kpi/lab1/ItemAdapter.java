package com.example.ua.kpi.lab1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    public ItemAdapter(Context context, int resource, List<Item> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_layout, parent, false);
        }

        TextView name = convertView.findViewById(R.id.item_name);
        TextView price = convertView.findViewById(R.id.item_price);
        ImageView image = convertView.findViewById(R.id.item_image);

        name.setText(item.getName());
        price.setText(item.getPrice());
        image.setImageResource(item.getImage());

        return convertView;
    }
}
