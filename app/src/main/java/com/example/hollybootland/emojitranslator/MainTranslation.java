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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Magnifier;
import android.support.v7.widget.Toolbar;
import android.widget.Spinner;

import edu.texttoemoji.EmojiConverter;


public class MainTranslation extends AppCompatActivity {

    EmojiEditText editText;
    EmojiButton langSwitchButton;
    Button translateButton;
    EmojiTextView textView;
    private Toolbar myToolbar, cardToolbar;
    MenuItem menu;
    private Spinner spinner, spinner2;

    // Function to clear the text in the bottom text box for the next translation
    public void clearTextBox(String text){
        if(editText.getText().equals("")){
            return;
        }
        else{
            editText.setText("");
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Init emoji first
        EmojiCompat.Config config = new BundledEmojiCompatConfig(this);
        EmojiCompat.init(config);
        setContentView(R.layout.main_translation);


        // Adding the card view toolbar - set as action bar so I can change stuff on menu
        cardToolbar = findViewById(R.id.card_menu);
        setSupportActionBar(cardToolbar);
//        getSupportActionBar().setTitle("Search");


        // Toolbar at the top of the page is defined here
        myToolbar = findViewById(R.id.toolbar);
        myToolbar.setNavigationIcon(R.drawable.ic_favorite_black_24dp);
        // Then define onn action listeners so I can use buttons



        // Access Emoji Views
        editText = findViewById(R.id.edtText);
        langSwitchButton = findViewById(R.id.edtButton);
        textView = findViewById(R.id.edtTextView);

        // Button for the translation
        translateButton = findViewById(R.id.translate_button);

        // Following 3 lines from:
        // https://github.com/naseemakhtar994/EmojiConverter
        EmojiConverter edt = (EmojiConverter) findViewById(R.id.edtRawText); //Try not adding Textwatcher on this
        final EmojiConverter emojiConverter = new EmojiConverter(MainTranslation.this);

        final Magnifier magnifier = new Magnifier(textView);


        // TRANSLATE BUTTON EVENT --- Set event for button
        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spinner1String = spinner.getSelectedItem().toString();
                String spinner2String = spinner2.getSelectedItem().toString();
                if(spinner1String.equals("English")&& spinner2String.equals("Emoji")){ // Translating from ENGLISH-EMOJI
                    clearTextBox(editText.getText().toString());
                    editText.setText(emojiConverter.convertEmoji()); //use this on an event, like a button click
                }
                else if(spinner1String.equals("Emoji") && spinner2String.equals("English")){

                }

            }
        });


        // CODE TAKEN FROM THE ANDROID DEVELOPER WEBSITE -- BEGIN
        // https://developer.android.com/guide/topics/ui/controls/spinner
        spinner = (Spinner)findViewById(R.id.language_spinner1);
        spinner2 = (Spinner)findViewById(R.id.language_spinner2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        // END -- CODE TAKEN FROM THE ANDROID DEVELOPER WEBSITE






        // LANGUAGE SWITCH BUTTON EVENT --- Set event for button
        langSwitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // GET THE CURRENT VALUES IN EACH SPINNER WHEN THE BUTTON IS SELECTED
                final int spinner1Index = spinner.getSelectedItemPosition();
                final int spinner2Index = spinner2.getSelectedItemPosition();
                spinner.setSelection(spinner2Index);
                spinner2.setSelection(spinner1Index);            }
        });

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




