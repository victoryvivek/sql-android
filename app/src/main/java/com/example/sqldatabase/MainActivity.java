package com.example.sqldatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText productName;
    Button addProduct,printButton,deleteButton;
    TextView showDatabseTextView;
    DbHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addProduct=findViewById(R.id.add_product_button);
        printButton=findViewById(R.id.print_button);
        productName=findViewById(R.id.product_name_edittext);
        showDatabseTextView=findViewById(R.id.show_database_textview);
        deleteButton=findViewById(R.id.delete_button);

        dbHandler=new DbHandler(this,null,null,1);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product=new Product(productName.getText().toString());
                dbHandler.addProduct(product);
            }
        });

        printButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result=dbHandler.printDatabase();
                showDatabseTextView.setText(result);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.deleteProduct(productName.getText().toString());
            }
        });
    }

}
