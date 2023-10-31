package com.m21290940.mystore.products.productslist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.m21290940.mystore.R;
import com.m21290940.mystore.products.addproduct.AddProductActivity;

public class ProductsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);

        initComponents();
    }

    private void initComponents() {
        FloatingActionButton fabAdd = findViewById(R.id.productListFabAdd);
        fabAdd.setOnClickListener( view -> {
            Intent intent = new Intent(this, AddProductActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder .setTitle(R.string.productListAletDialogBackTitle)
                .setMessage(R.string.productListAlertDialogBackMessage)
                .setNegativeButton(R.string.productListAlertDialogBackNegativeTitle,
                        (dialog, which) -> {
                             finish();
                        }
                )
                .setPositiveButton(R.string.productListAlertDialogBackPositiveTitle,
                        (dialog, which) -> {

                        }
                )
                .create()
                .show();
    }
}