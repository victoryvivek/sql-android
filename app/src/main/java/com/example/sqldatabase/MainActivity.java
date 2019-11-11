package com.example.sqldatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText productName;
    Button addProduct;
    DbHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addProduct=findViewById(R.id.add_product_button);
        productName=findViewById(R.id.product_name_edittext);

        dbHandler=new DbHandler(this,null,null,1);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product=new Product(productName.getText().toString());
                dbHandler.addProduct(product);
            }
        });
    }
}
