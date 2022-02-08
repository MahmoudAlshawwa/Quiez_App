package com.example.abood.newproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class MainActivity extends Group_Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initilizetoolbar();




        Button b1 = findViewById(R.id.button);
        final EditText ed1 = findViewById(R.id.editText);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed1.getText().toString();
                if (name == null || name.trim().equalsIgnoreCase("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("تنبيه");
                    builder.setIcon(R.drawable.r);
                    builder.setMessage("الرجاء إدخال الإسم!!");
                    builder.setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                          dialog.dismiss();
                        }

                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    alertDialog.setCanceledOnTouchOutside(false);


                }else {
                    Intent intent = new Intent(MainActivity.this,Second_Activity.class);
                    intent.putExtra("name",name);
                    startActivity(intent);
                }


            }
        });

    }



    @Override
    public void onBackPressed() {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("تنبيه");
            builder.setIcon(R.drawable.r);
            builder.setMessage("هل تريد الخروج؟!");
            builder.setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.super.onBackPressed();
                   // or
                  //  MainActivity.this.finish();
                }

            });

            builder.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

           AlertDialog alertDialog= builder.create();
           alertDialog.show();




    }



}
