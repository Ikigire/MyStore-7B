package com.m21290940.mystore.products.listadapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.imageview.ShapeableImageView;
import com.m21290940.mystore.R;
import com.m21290940.mystore.dbmanager.entities.Product;

import java.util.List;

public class ProductListAdapter extends ArrayAdapter<Product> {
    public ProductListAdapter(@NonNull Context context, @NonNull List<Product> products) {
        super(context, R.layout.list_item_layout, products);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        Product product = getItem(position);

        if (view == null){
            view = LayoutInflater
                    .from(getContext())
                    .inflate(R.layout.list_item_layout, parent, false);
        }

        ShapeableImageView sivProductImg = view.findViewById(R.id.listItemSivProductImg);
        TextView
                tvProductName       = view.findViewById(R.id.listItemTvProductName),
                tvProductExistences = view.findViewById(R.id.listItemTvProductExistences),
                tvProductPrice      = view.findViewById(R.id.listItemTvProductPrice);

        try {
            Uri img = Uri.parse(product.getPhoto_uri());
            sivProductImg.setImageURI(img);
        } catch (Exception e){
            sivProductImg.setImageResource(R.drawable.product_asset);
        }

        tvProductName.setText(product.getName());
        tvProductExistences.setText( String.format("Existencias: %d", product.getExistences()) );
        tvProductPrice.setText( String.format("Precio: $%.2f", product.getPrice()) );

        return view;
    }
}
