package com.m21290940.mystore.login.register;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputLayout;
import com.m21290940.mystore.R;
import com.m21290940.mystore.dbmanager.DbManager;
import com.m21290940.mystore.dbmanager.dao.UserDao;
import com.m21290940.mystore.dbmanager.entities.User;
import com.m21290940.mystore.login.login.LoginActivity;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RegisterActivity extends AppCompatActivity {
    // Elementos de la vista
    TextInputLayout tilFullname;
    TextInputLayout tilUsername;
    TextInputLayout tilPassword;
    TextView tvImgMessage;
    ShapeableImageView sivImage;

    // Contratos
    ActivityResultLauncher getImageLauncher;

    // Objetos auxiliares
    User newUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponents();
        initContracts();
    }

    private void initContracts() {
        getImageLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                this::getContentCallback
        );
    }

    private void getContentCallback(Uri uriToPhoto) {
        if ( uriToPhoto == null ){
            return;
        }

        newUser.setPhoto_uri(uriToPhoto.toString());

        tvImgMessage.setText(R.string.registerTvImgMessageActivo);
        sivImage.setImageURI(uriToPhoto);
    }

    private void initComponents() {
        newUser = new User();

        sivImage     = findViewById(R.id.registerSivImage);
        tilFullname  = findViewById(R.id.registerTilFullname);
        tilUsername  = findViewById(R.id.registerTilUsername);
        tilPassword  = findViewById(R.id.registerTilPassword);
        tvImgMessage = findViewById(R.id.registerTvImgMessage);

        sivImage.setOnLongClickListener( this::resetImage );
        sivImage.setOnClickListener( this::changeImage );

        TextView tvIniciarSesion = findViewById(R.id.registerTvIniciarSesion);
        tvIniciarSesion.setOnClickListener( this::getBackToLogin );

        Button btn = findViewById(R.id.registerBtnRegistrar);
        btn.setOnClickListener( this::registrarUsuario );
    }

    private void changeImage(View view) {
        getImageLauncher.launch("image/*");
    }

    private void getBackToLogin(View view) {
        finish();
    }

    private void registrarUsuario(View view) {
        view.setEnabled(false);
        String 
                fullname = tilFullname.getEditText().getText().toString(),
                username = tilUsername.getEditText().getText().toString(),
                password = tilPassword.getEditText().getText().toString();
        
        fullname = fullname.trim();
        username = username.trim();
        password = password.trim();
        
        if ( fullname.isEmpty() || username.isEmpty() || password.isEmpty() ) {
            view.setEnabled(true);
            Toast.makeText(this, "Alguno de los campos no tiene información", Toast.LENGTH_SHORT).show();
            return;
        }
        
        newUser.setFullname(fullname);
        newUser.setUsername(username);
        newUser.setPassword(password);

        UserDao userDao = DbManager.getDatabase(getApplicationContext()).getUserDao();
        
        userDao.insertUser(newUser)
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Toast.makeText(RegisterActivity.this, "Registrando usuario", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(RegisterActivity.this, "Ahora puede iniciar sesión", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        view.setEnabled(true);
                    }
                });
    }

    private boolean resetImage(View view) {
        newUser.setPhoto_uri(null);
        tvImgMessage.setText(R.string.registerTvImgMessageInactivo);
        //sivImage.setImageDrawable( getDrawable(R.drawable.user_asset) );
        sivImage.setImageResource(R.drawable.user_asset);
        return true;
    }
}