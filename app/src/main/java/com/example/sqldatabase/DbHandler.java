package com.example.sqldatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

public class DbHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="products.db";
    private static final String TABLE_PRODUCTS="products";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_PRODUCTNAME="productname";


    public DbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="CREATE TABLE "+TABLE_PRODUCTS+" ( "+COLUMN_ID+" INTEGER PRIMARY KEY AUTO INCREMENT, "+COLUMN_PRODUCTNAME+" TEXT"+");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_PRODUCTS);
        onCreate(sqLiteDatabase);
    }

    public void addProduct(Product product){
        ContentValues values=new ContentValues();
        values.put(COLUMN_PRODUCTNAME,product.getProductName());
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.insert(TABLE_PRODUCTS,null,values);
        sqLiteDatabase.close();
    }

    public void deleteProduct(String productName){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM "+TABLE_PRODUCTS+" WHERE "+COLUMN_PRODUCTNAME+"=\""+productName+"\";");
    }

//    public String databaseToString(){
//        String dbString="";
//        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
//        String query="SELECT * FROM "+TABLE_PRODUCTS+" WHERE 1";
//        sqLiteDatabase.execSQL(query);
//    }
}
