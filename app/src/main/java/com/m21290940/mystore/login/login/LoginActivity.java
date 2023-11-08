package com.m21290940.mystore.login.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.m21290940.mystore.R;
import com.m21290940.mystore.login.register.RegisterActivity;
import com.m21290940.mystore.products.productslist.ProductsListActivity;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout tilUsername;
    TextInputLayout tilPassword;

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
        btn.setOnClickListener(this::login);

        tilUsername = findViewById(R.id.loginTilUsername);
        tilPassword = findViewById(R.id.loginTilPassword);
    }

    private void login(View view) {
        String
                username = tilUsername.getEditText().getText().toString(),
                password = tilPassword.getEditText().getText().toString();
        //Login

        Intent intent = new Intent(this, ProductsListActivity.class);
        startActivity(intent);
    }
}