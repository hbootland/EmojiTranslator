

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
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

//    Button homeButton;
//    Button faveButton;
    Switch themeSwitch;
    android.support.v7.widget.Toolbar settToolbar;
    MenuItem menu;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

//
        settToolbar = findViewById(R.id.sett_Toolbar);
        setSupportActionBar(settToolbar);

//        homeButton = findViewById(R.id.action_home);
//        faveButton = findViewById(R.id.action_favorite);
        themeSwitch = findViewById(R.id.themeSwitch);
//        textView = (TextView)findViewById();

        themeSwitch.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {
            startActivity(new Intent(SettingsActivity.this, FavouritesActivity.class));
            return true;
        }
        if(id == R.id.action_home){
            startActivity(new Intent(SettingsActivity.this, MainTranslation.class));
            return true;}


        return super.onOptionsItemSelected(item);
    }
}


