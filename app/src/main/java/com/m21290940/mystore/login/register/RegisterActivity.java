package com.m21290940.mystore.login.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.m21290940.mystore.R;
import com.m21290940.mystore.login.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponents();
    }

    private void initComponents() {
        TextView tvIniciarSesion = findViewById(R.id.registerTvIniciarSesion);
        /*tvIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBackToLogin(v);
            }
        });*/
        tvIniciarSesion.setOnClickListener( this::getBackToLogin );

        Button btn = findViewById(R.id.registerBtnRegistrar);
        btn.setOnClickListener( this::getBackToLogin );
    }

    private void getBackToLogin(View view) {
        finish();
    }
}