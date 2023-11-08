package com.m21290940.mystore;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.m21290940.mystore.dbmanager.DbManager;
import com.m21290940.mystore.dbmanager.dao.UserDao;
import com.m21290940.mystore.dbmanager.entities.User;
import com.m21290940.mystore.login.login.LoginActivity;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.SchedulerSupport;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {

    private boolean continuar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initComponents();
    }

    private void initComponents() {
        continuar = false;

        LottieAnimationView lav = findViewById(R.id.splashLavAnimacion);
        lav.addAnimatorListener(
                new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        if (continuar) {
                            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        lav.resumeAnimation();
                    }
                }
        );

        UserDao userDao = DbManager.getDatabase(getApplicationContext()).getUserDao();

        userDao.login("mamahuevo", "glugluglu")
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull User user) {
                        SplashActivity.this.continuar = true;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        User master = new User();
                        master.setFullname("Pedro Piña");
                        master.setUsername("mamahuevo");
                        master.setPassword("glugluglu");

                        userDao.insertUser(master)
                                .observeOn(Schedulers.newThread())
                                .subscribe( () -> {
                                    continuar = true;
                                });
                    }
                });
    }
}