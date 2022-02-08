package com.example.abood.newproject;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Group_Activity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.compmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.aboutMenuItem) {
            Toast.makeText(getApplicationContext(), "about", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.closeMenuItem) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


     public  void initilizetoolbar (){
         toolbar = findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);

     }


}

