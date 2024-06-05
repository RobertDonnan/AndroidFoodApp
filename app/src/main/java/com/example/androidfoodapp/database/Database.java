package com.example.androidfoodapp.database;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.androidfoodapp.model.Orders;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;
//Seperate database used to store order data which then goes into firebase database

public class Database  extends SQLiteAssetHelper {
    private static final String name="FoodBankBookingsDB.db";
    private static final int version=1;
    public Database(Context context) {
        super(context, name,null,version);
    }


    public List<Orders> getBaskets()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"ItemsID","ItemName","Quantity"};
        String sqlTable="OrderData";

        qb.setTables(sqlTable);
        Cursor cu;
        cu = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<Orders> result = new ArrayList<>();
        if(cu.moveToFirst())
        {
            do{
                result.add(new Orders(cu.getString(cu.getColumnIndexOrThrow("ItemsID")),
                        cu.getString(cu.getColumnIndexOrThrow("ItemName")),
                        cu.getString(cu.getColumnIndexOrThrow("Quantity"))
                ));
            }while (cu.moveToNext());
        }
        return result;
    }

    public void addDataToBasket(Orders orders)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderData(ItemsID, ItemName, Quantity) VALUES('%s', '%s', '%s');",
              orders.getItemsID(),
                orders.getItemName(),
                orders.getQuantity());
        db.execSQL(query);
    }
 public void clearBasket()
 {
     SQLiteDatabase db = getReadableDatabase();
     String query = String.format("DELETE FROM OrderData");
     db.execSQL(query);
 }


}
