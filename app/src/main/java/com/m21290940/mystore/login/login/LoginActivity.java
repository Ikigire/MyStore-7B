package com.m21290940.mystore.login.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.m21290940.mystore.R;
import com.m21290940.mystore.login.register.RegisterActivity;
import com.m21290940.mystore.products.productslist.ProductsListActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();
    }

    private void initComponents() {
        TextView tvCrearCuenta = findViewById(R.id.loginTvCrearCuenta);
        tvCrearCuenta.setOnClickListener( view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        } );

        Button btn = findViewById(R.id.loginBtnAcceder);
        btn.setOnClickListener( view -> {
            Intent intent = new Intent(this, ProductsListActivity.class);
            startActivity(intent);
        } );

    }
}