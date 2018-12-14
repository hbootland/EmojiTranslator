package com.example.hollybootland.emojitranslator;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.text.emoji.EmojiCompat;
import android.support.text.emoji.bundled.BundledEmojiCompatConfig;
import android.support.text.emoji.widget.EmojiButton;
import android.support.text.emoji.widget.EmojiEditText;
import android.support.text.emoji.widget.EmojiTextView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Magnifier;
import android.support.v7.widget.Toolbar;

import edu.texttoemoji.EmojiConverter;


public class MainTranslation extends AppCompatActivity {

    EmojiEditText editText;
    EmojiButton button;
    EmojiTextView textView;
    EmojiConverter emjiTrans;
    private Toolbar toolbar;
    MenuItem menu;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Init emoji first
        EmojiCompat.Config config = new BundledEmojiCompatConfig(this);
        EmojiCompat.init(config);
        setContentView(R.layout.main_translation);

        // This allows the toolbar to be used for activities
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        // Access Emoji Views
        editText = (EmojiEditText) findViewById(R.id.edtText);
        button = (EmojiButton) findViewById(R.id.edtButton);
        textView = (EmojiTextView) findViewById(R.id.edtTextView);

        // Following 3 lines from:
        // https://github.com/naseemakhtar994/EmojiConverter
        EmojiConverter edt = (EmojiConverter) findViewById(R.id.edtRawText); //Try not adding Textwatcher on this
        final EmojiConverter emojiConverter=new EmojiConverter(MainTranslation.this);
        //editText.setText(emojiConverter.convertEmoji()); //use this on an event, like a button click

        final Magnifier magnifier = new Magnifier(textView);


        // TRANSLATION BUTTON EVENT --- Set event for button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(emojiConverter.convertEmoji()); //use this on an event, like a button click
            }
        });

        //FLOATING ACTION BUTTON --- Sets events for this button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // currenty just outputs some text but can rewrite in future
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


//        magnifier.show(textView.getWidth() / 2, textView.getHeight() / 2);
        // MAGNIFIER FOR TEXT BOX
        textView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Fall through.
                    case MotionEvent.ACTION_MOVE: {
                        final int[] viewPosition = new int[2];
                        v.getLocationOnScreen(viewPosition);
                        magnifier.show(event.getRawX() - viewPosition[0],
                                event.getRawY() - viewPosition[1]);
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:
                        // Fall through.
                    case MotionEvent.ACTION_UP: {
                        magnifier.dismiss();
                    }
                }
            return true;
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_translation, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}




