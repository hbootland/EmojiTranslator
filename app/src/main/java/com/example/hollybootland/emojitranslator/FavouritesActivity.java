package com.example.hollybootland.emojitranslator;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class FavouritesActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar faveToolbar;
    MenuItem menu;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourite_translation);


        faveToolbar = findViewById(R.id.faveToolbar);
        setSupportActionBar(faveToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.favourites_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(FavouritesActivity.this, SettingsActivity.class));
            return true;
        }
        if(id == R.id.action_home){
            startActivity(new Intent(FavouritesActivity.this, MainTranslation.class));
            return true;}


        return super.onOptionsItemSelected(item);
    }}
