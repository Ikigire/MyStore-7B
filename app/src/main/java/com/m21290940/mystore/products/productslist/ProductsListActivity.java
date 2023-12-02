package com.m21290940.mystore.products.productslist;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.m21290940.mystore.R;
import com.m21290940.mystore.dbmanager.DbManager;
import com.m21290940.mystore.dbmanager.dao.ProductDao;
import com.m21290940.mystore.dbmanager.entities.Product;
import com.m21290940.mystore.dbmanager.views.UserProducts;
import com.m21290940.mystore.products.addproduct.AddProductActivity;
import com.m21290940.mystore.products.listadapter.ProductListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductsListActivity extends AppCompatActivity {

    ListView listView;

    ProductListAdapter adapter;

    int user_id;
    Product selectedProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);

        initComponents();
    }

    private void initComponents() {
        user_id = getIntent().getIntExtra("user_id", -1);
        if (user_id < 1) {
            Toast.makeText(this, "No se logró recibir el id", Toast.LENGTH_SHORT).show();
            finish();
        }

        Toolbar toolbar = findViewById(R.id.productListToolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.productListListview);
        registerForContextMenu(listView);

        FloatingActionButton fabAdd = findViewById(R.id.productListFabAdd);
        fabAdd.setOnClickListener( view -> {
            Intent intent = new Intent(this, AddProductActivity.class);
            intent.putExtra("user_id", user_id);
            startActivity(intent);
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProductsListActivity.this);
                builder .setTitle(R.string.productListAletDialogBackTitle)
                        .setMessage(R.string.productListAlertDialogBackMessage)
                        .setNegativeButton(R.string.productListAlertDialogBackNegativeTitle,
                                (dialog, which) -> {
                                    finish();
                                }
                        )
                        .setPositiveButton(R.string.productListAlertDialogBackPositiveTitle,
                                (dialog, which) -> {
                                    finishAffinity();
                                }
                        )
                        .create()
                        .show();
            }
        };

        getOnBackPressedDispatcher().addCallback(callback);
    }

   private void getUserProducts() {
       ProductDao productDao = DbManager.getDatabase(getApplicationContext()).getProductDao();
       productDao.getProductByUserId(user_id)
               .subscribe(
                       this::setListViewAdapter,
                       this::errorHandler
               );
   }

    private void errorHandler(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        setListViewAdapter(null);
    }

    private void setListViewAdapter(UserProducts userProducts) {
        if (userProducts == null)
            adapter = new ProductListAdapter(getApplicationContext(), new ArrayList<>());
        else
            adapter = new ProductListAdapter(getApplicationContext(), userProducts.products);

        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserProducts();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.productListListview){
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
            selectedProduct = (Product) listView.getItemAtPosition(acmi.position);

            menu.setHeaderTitle(selectedProduct.getName() + " opciones");
            menu.add("Editar");
            menu.add("Borrar");
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().equals("Editar")) {
            Toast.makeText(this, "Editando un producto", Toast.LENGTH_SHORT).show();
        }
        if (item.getTitle().equals("Borrar")) {
            Toast.makeText(this, "Borrando producto " + selectedProduct.get_id(), Toast.LENGTH_SHORT).show();
        }

        return super.onContextItemSelected(item);
    }
}