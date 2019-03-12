package com.example.hollybootland.emojitranslator;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar faveToolbar;
    MenuItem menu;
    CheckBox starChk;
    RelativeLayout relv;
    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    RelativeLayout mRelativeLayout;
    final ArrayList<Translations> savedTranslationLists = new ArrayList<>();
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";

    TextView inLng,inTxt,outLng,outTxt;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark);
        }
        super.onCreate(savedInstanceState);

        setContentView(R.layout.favourite_translation);

        starChk = findViewById(R.id.starButton);

        faveToolbar = findViewById(R.id.faveToolbar);
        setSupportActionBar(faveToolbar);


        // this is to receive data from main
//        Intent i = getIntent();
//        final String inLang=i.getExtras().getString("inLang");
//        final String inText =i.getExtras().getString("inText");
//        final String outLang=i.getExtras().getString("outLang");
//        final String outText =i.getExtras().getString("outText");

        //reference views from xml
        inLng = findViewById(R.id.lang1);
        inTxt = findViewById(R.id.inputText);
        outLng = findViewById(R.id.lang2);
        outTxt = findViewById(R.id.outputText);




        // setting up recycler view
        rv=findViewById(R.id.rec_v);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new RVAdapter(this, savedTranslationLists); //KEEP THIS AS "this" OR IT WILL CRASH!!!
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
//
//        savedTranslationLists.add(0, new Translations(inLang, inText, outLang, outText));

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
