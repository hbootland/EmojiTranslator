package com.example.hollybootland.emojitranslator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RVActivity extends Activity {

    private ArrayList<Translations> translations;
    private RecyclerView rv;
    private Context mContext;


//    public void addItem() {
//        Translations.add(new String());
//        notifyItemInserted(translations.size() - 1);
//    }

//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.item);

        rv=findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
  }

    private void initializeData(){
        translations = new ArrayList<>();
//        translations.add(new Translations("English", "wave", "Emoji", "emj"));

    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(mContext, translations);
        rv.setAdapter(adapter);
    }

}
