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
    public Single<List<User>> getAllUsers();

    @Query("SELECT * FROM users WHERE username == :username AND password == :password")
    public Single<User> login(String username, String password);

    @Query("SELECT * FROM users WHERE user_id == :id")
    public Single<User> getUserById(long id);

    @Insert
    public Completable insertUser(User user);
    @Update
    public Completable updateUser(User user);
    @Delete
    public Completable deleteUser(User user);
}
