package com.example.sqldatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DbHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="products.db";
    private static final String TABLE_PRODUCTS="products";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_PRODUCTNAME="productname";
    Context context;


    public DbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="CREATE TABLE "+TABLE_PRODUCTS+" ( "+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_PRODUCTNAME+" VARCHAR(255)"+");";
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
        Toast.makeText(context, "Product added", Toast.LENGTH_SHORT).show();
    }

    public void deleteProduct(String productName){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM "+TABLE_PRODUCTS+" WHERE "+COLUMN_PRODUCTNAME+"=\""+productName+"\";");
        Toast.makeText(context, "Product deleted", Toast.LENGTH_SHORT).show();
    }

    String printDatabase(){
        String result="";
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        Cursor c= (Cursor) sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_PRODUCTS+";",null);
        if(c.moveToFirst()){
            do{
                String columnId=c.getString(0);
                String columnProductName=c.getString(1);
                result+=columnId+"\t"+columnProductName+"\n";
            }while (c.moveToNext());
        }
        return result;
    }
}