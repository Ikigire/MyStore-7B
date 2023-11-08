package com.m21290940.mystore.dbmanager;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.m21290940.mystore.dbmanager.dao.ProductDao;
import com.m21290940.mystore.dbmanager.dao.UserDao;
import com.m21290940.mystore.dbmanager.entities.Product;
import com.m21290940.mystore.dbmanager.entities.User;

import java.lang.reflect.Constructor;

@Database(entities = { User.class, Product.class }, version = 1, exportSchema = false)
public abstract class DbManager extends RoomDatabase {
    private static DbManager database = null;
    public abstract UserDao getUserDao();
    public abstract ProductDao getProductDao();

    public static DbManager getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, DbManager.class, "miDb").build();
        }
        return database;
    }

}
