package com.m21290940.mystore.dbmanager.views;

import androidx.room.Embedded;
import androidx.room.Relation;
import androidx.room.Transaction;

import com.m21290940.mystore.dbmanager.entities.Product;
import com.m21290940.mystore.dbmanager.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserProducts {
    @Embedded
    public User user = null;

    @Relation(parentColumn = "user_id", entityColumn = "user_id")
    public List<Product> products = new ArrayList<Product>();
}
