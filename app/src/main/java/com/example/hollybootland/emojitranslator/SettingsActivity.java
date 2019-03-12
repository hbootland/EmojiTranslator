

package com.example.hollybootland.emojitranslator;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {


    Switch themeSw;
    android.support.v7.widget.Toolbar settToolbar;
    MenuItem menu;
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";
    Spinner font_spinner;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark);
        }
//        this.setTheme(R.style.AppTheme);
//        Utils.onActivityCreateSetTheme(SettingsActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        settToolbar = findViewById(R.id.sett_Toolbar);
        setSupportActionBar(settToolbar);


        themeSw =  findViewById(R.id.themeSwitch);

        themeSw.setChecked(useDarkTheme);
        themeSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton v, boolean isChecked) {
                toggleTheme(isChecked);
            }
        } );

        font_spinner = (Spinner)findViewById(R.id.fontSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.text_sizes, R.layout.spinner_settings_page);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // Apply the adapter to the spinner
        font_spinner.setAdapter(adapter);
        font_spinner.setSelection(1,true);
    }


//                if(v.getId() == R.id.themeSwitch&&themeSw.isChecked()){
////                    SettingsActivity.this.setTheme(R.style.DarkTheme);
//                    Utils.changeToTheme(SettingsActivity.this, Utils.DARK_THEME);
//                    themeSw.setChecked(true);
////                    SettingsActivity.this.setVisible(true);
//                    System.out.println("Putting dark theme on");
//                }if((!themeSw.isChecked())) {
////                    SettingsActivity.this.setTheme(R.style.AppTheme);
//                    Utils.changeToTheme(SettingsActivity.this, Utils.APP_THEME);
////                    SettingsActivity.this.setVisible(true);
//                    themeSw.setChecked(false);
//
//                    System.out.println("Putting light theme on");
//                }




    private void toggleTheme(boolean darkTheme) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(PREF_DARK_THEME, darkTheme);
        editor.apply();

        Intent intent = getIntent();
        finish();

        startActivity(intent);
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


