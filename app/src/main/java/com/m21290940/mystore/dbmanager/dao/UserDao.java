package com.m21290940.mystore.dbmanager.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.m21290940.mystore.dbmanager.entities.User;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    Single<List<User>> getAllUsers();

    @Query("SELECT * FROM users WHERE username == :username AND password == :password")
    Single<User> login(String username, String password);

    @Query("SELECT * FROM users WHERE user_id == :id")
    Single<User> getUserById(long id);

    @Insert
    Completable insertUser(User user);
    @Update
    Completable updateUser(User user);
    @Delete
    Completable deleteUser(User user);
}
