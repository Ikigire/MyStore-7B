package com.m21290940.mystore.products.addproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.m21290940.mystore.R;

public class AddProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        initComponents();
    }

    private void initComponents() {
        Button btn = findViewById(R.id.addProductBtnRegistrar);
        btn.setOnClickListener( this::getBackToProductList );
    }

    private void getBackToProductList(View view) {
        finish();
    }
}