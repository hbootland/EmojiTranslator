package com.example.hollybootland.emojitranslator;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.text.emoji.EmojiCompat;
import android.support.text.emoji.bundled.BundledEmojiCompatConfig;
import android.support.text.emoji.widget.EmojiButton;
import android.support.text.emoji.widget.EmojiEditText;
import android.support.text.emoji.widget.EmojiTextView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Magnifier;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;
import edu.texttoemoji.EmojiConverter;
import emoji4j.EmojiUtils;

import java.util.ArrayList;

public class MainTranslation extends AppCompatActivity {

    EmojiButton langSwitchButton;
    Button favouriteButton, translateButton;
    CheckBox starButton;
    EmojiTextView textView;
    Toolbar myTopToolbar, cardToolbar;
    MenuItem menu;
    private Spinner spinner, spinner2;
    private Context mContext;
    RelativeLayout mRelativeLayout;
    private RecyclerView rv;
    StringBuffer sb = null;

    private RVAdapter adapter; // changed from view.adapter or something
    private RVAdapter adapter1;
    private RecyclerView.LayoutManager layoutManager;
    final ArrayList<Translations> translationLists = new ArrayList<>();
    private ArrayList<Translations> mSavedTranslations;

    private static final String TAG = "MainTranslation";
    DatabaseHelper mDatabaseHelper;
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark);
        }
        super.onCreate(savedInstanceState);

        mDatabaseHelper = new DatabaseHelper(this);
        // Init emoji first
        EmojiCompat.Config config = new BundledEmojiCompatConfig(this);
        EmojiCompat.init(config);
        setContentView(R.layout.main_translation);

        // Adding the card view toolbar - set as action bar so I can change stuff on menu
        cardToolbar = findViewById(R.id.card_menu);
        setSupportActionBar(cardToolbar);

        mRelativeLayout =  findViewById(R.id.rl);

        // Toolbar at the top of the page is defined here
        myTopToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myTopToolbar);

        // Access Emoji Views
        langSwitchButton = findViewById(R.id.edtButton);
        textView = findViewById(R.id.edtTextView);

        // Button for the translation
        translateButton = findViewById(R.id.translate_button);

        // Button for the favourite translations = takes me to anothe rpage
        favouriteButton = findViewById(R.id.action_favorite);

        // button for adding a new favourite translation
        starButton = findViewById(R.id.starButton);

        // Following 2 lines from:
        // https://github.com/naseemakhtar994/EmojiConverter
        final EditText edt = findViewById(R.id.edtRawText);
        final EmojiConverter emojiConverter = new EmojiConverter(MainTranslation.this);

        final Magnifier magnifier = new Magnifier(textView);

        // setting up recycler view
        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new RVAdapter(this, translationLists); //KEEP THIS AS "this" OR IT WILL CRASH!!!
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);



        // TRANSLATE BUTTON EVENT --- Set event for button
        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spinner1String = spinner.getSelectedItem().toString();
                String spinner2String = spinner2.getSelectedItem().toString();
                String textViewContents = edt.getText().toString();
                String trans,trans2,trans3;
                String str,stra;
                int insertIndex = 0;
                String colon = ":";
                String outputStr = "";


                if (spinner1String.equals("English") && spinner2String.equals("Emoji")) { // ENGLISH-EMOJI   WORKING!!
                    //https://github.com/naseemakhtar994/EmojiConverter
//                    System.out.println(trans);
                    str = colon+textViewContents+colon;
                    System.out.println("str ; "+str);

                    trans = EmojiParser.parseToUnicode(str);
                    stra = trans.replace(":","");
                    System.out.println("stra:" + stra);
                    System.out.println("OGtrans;"+stra);
                    System.out.println("OGtrans EM?;"+ EmojiManager.isEmoji(trans));

                    trans2 = emojiConverter.convertEmoji();
                    trans3 = trans2.replace(" ", "");
                    System.out.println("OGtrans2;"+trans2 + "end");
                    System.out.println("OGtrans2 EM?;"+ check(trans2)); // if true then emoji

                    System.out.println("Equal?; "+ trans3.equals(stra)); // if not true==emoji

                    if (EmojiManager.isEmoji(trans) && !check(trans3) && !trans.equals(textViewContents) && !trans2.equals(textViewContents)) {
                        outputStr = trans + ", " + trans2;
                        System.out.println("Same ; " + outputStr);
                        translationLists.add(insertIndex, new Translations("English", textViewContents, "Emoji", outputStr));
                        adapter.notifyItemInserted(insertIndex);
                    }else if(trans3.equals(stra) && trans3.equals(textViewContents)){
                        SpannableStringBuilder biggerText = new SpannableStringBuilder("TRANSLATION NOT CURRENTLY AVAILABLE!");
                        biggerText.setSpan(new RelativeSizeSpan(1.35f), 0, "TRANSLATION NOT CURRENTLY AVAILABLE!".length(), 0);
                        Toast.makeText(MainTranslation.this, biggerText, Toast.LENGTH_LONG).show();
                    }else if(!trans3.equals(textViewContents) && stra.equals(textViewContents)){ // trans2 == emoji
                        System.out.println("trans2 ; "+trans2);
                        translationLists.add(insertIndex, new Translations("English", textViewContents, "Emoji", trans2));
                        adapter.notifyItemInserted(insertIndex);
                    }else if(trans3.equals(textViewContents) && !check(trans2) && !stra.equals(textViewContents)){ // trans is an emoji
                        System.out.println("trans ; "+trans);
                        translationLists.add(insertIndex, new Translations("English", textViewContents, "Emoji", trans));
                        adapter.notifyItemInserted(insertIndex);
                    }
                } else if (spinner1String.equals("Emoji") && spinner2String.equals("English")) { // EMOJI-ENGLISH  WORKING!!!
                    System.out.println("emoji-english:"+textViewContents+".");
//                    https://github.com/kcthota/emoji4j
                    // editText.setText(EmojiUtils.shortCodify(textViewContents));
                    //https://github.com/vdurmont/emoji-java
                    trans = EmojiParser.parseToAliases(textViewContents);
                    trans = trans.replace(":", "");
                    trans = trans.replace("_", " ");
                    if(trans.equals(textViewContents)){
                        SpannableStringBuilder biggerText = new SpannableStringBuilder("TRANSLATION NOT CURRENTLY AVAILABLE!");
                        biggerText.setSpan(new RelativeSizeSpan(1.35f), 0, "TRANSLATION NOT CURRENTLY AVAILABLE!".length(), 0);
                        Toast.makeText(MainTranslation.this, biggerText, Toast.LENGTH_LONG).show();
                    }else{
                        translationLists.add(insertIndex, new Translations("Emoji", textViewContents, "English", trans));
                        adapter.notifyItemInserted(insertIndex);
                    }
                } else if (spinner1String.equals("Hex") && spinner2String.equals("Emoji")) { // Hex-Emoji     WORKING!!!
                    //https://github.com/vdurmont/emoji-java
                    trans = (EmojiParser.parseToUnicode(textViewContents));
                    if(trans.equals(textViewContents)){
                        SpannableStringBuilder biggerText = new SpannableStringBuilder("TRANSLATION NOT CURRENTLY AVAILABLE!");
                        biggerText.setSpan(new RelativeSizeSpan(1.35f), 0, "TRANSLATION NOT CURRENTLY AVAILABLE!".length(), 0);
                        Toast.makeText(MainTranslation.this, biggerText, Toast.LENGTH_LONG).show();
                    }else{
                        translationLists.add(insertIndex, new Translations("Hex", textViewContents, "Emoji", trans));
                        adapter.notifyItemInserted(insertIndex);
                    }
                } else if (spinner1String.equals("Emoji") && spinner2String.equals("Hex")) { // Emoji-Hex     WORKING!!!
                    trans = (EmojiParser.parseToHtmlHexadecimal(textViewContents));
                    if(trans.equals(textViewContents)){
                        SpannableStringBuilder biggerText = new SpannableStringBuilder("TRANSLATION NOT CURRENTLY AVAILABLE!");
                        biggerText.setSpan(new RelativeSizeSpan(1.35f), 0, "TRANSLATION NOT CURRENTLY AVAILABLE!".length(), 0);
                        Toast.makeText(MainTranslation.this, biggerText, Toast.LENGTH_LONG).show();
                    }else{
                        translationLists.add(insertIndex, new Translations("Emoji", textViewContents, "Hex", trans));
                        adapter.notifyItemInserted(insertIndex);
                    }
                }else if (spinner1String.equals("English") && spinner2String.equals("Hex")) { // ENGLISH-UNICODE     NOT- WORKING!!!
                    trans = emojiConverter.convertEmoji();
                    trans = (EmojiParser.parseToHtmlHexadecimal(trans));
                    if(trans.equals(textViewContents)){
                        SpannableStringBuilder biggerText = new SpannableStringBuilder("TRANSLATION NOT CURRENTLY AVAILABLE!");
                        biggerText.setSpan(new RelativeSizeSpan(1.35f), 0, "TRANSLATION NOT CURRENTLY AVAILABLE!".length(), 0);
                        Toast.makeText(MainTranslation.this, biggerText, Toast.LENGTH_LONG).show();
                    }else{
                        translationLists.add(insertIndex, new Translations("English", textViewContents, "Hex", trans));
                        adapter.notifyItemInserted(insertIndex);
                    }
                }else if (spinner1String.equals("Hex") && spinner2String.equals("English")) { // ENGLISH-UNICODE     NOT- WORKING!!!
                    trans = (EmojiParser.parseToUnicode(textViewContents));
                    trans = EmojiParser.parseToAliases(trans);
                    trans = trans.replace(":", "");
                    trans = trans.replace("_", " ");
                    if(trans.equals(textViewContents)){
                        SpannableStringBuilder biggerText = new SpannableStringBuilder("TRANSLATION NOT CURRENTLY AVAILABLE!");
                        biggerText.setSpan(new RelativeSizeSpan(1.35f), 0, "TRANSLATION NOT CURRENTLY AVAILABLE!".length(), 0);
                        Toast.makeText(MainTranslation.this, biggerText, Toast.LENGTH_LONG).show();
                    }else{
                        translationLists.add(insertIndex, new Translations("Hex", textViewContents, "English", trans));
                        adapter.notifyItemInserted(insertIndex);
                    }
                } else if(spinner1String.equals(spinner2String)){
                    SpannableStringBuilder biggerText = new SpannableStringBuilder("CHOOSE A LANGUAGE TO TRANSLATE TO!");
                    biggerText.setSpan(new RelativeSizeSpan(1.35f), 0, "CHOOSE A LANGUAGE TO TRANSLATE TO!".length(), 0);
                    Toast.makeText(MainTranslation.this, biggerText, Toast.LENGTH_LONG).show();
                }
            }
        });



        // CODE TAKEN FROM THE ANDROID DEVELOPER WEBSITE -- BEGIN
        // https://developer.android.com/guide/topics/ui/controls/spinner
        spinner = (Spinner)findViewById(R.id.language_spinner1);
        spinner2 = (Spinner)findViewById(R.id.language_spinner2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner.setSelection(1,true);
        spinner2.setSelection(0, true);
        // END -- CODE TAKEN FROM THE ANDROID DEVELOPER WEBSITE


//        starButton.setOnCheckedChangeListener(new View.OnClickListener() {
//            @Override
//            public void onCheckedChange(View v) {
//                sb = new StringBuffer();
//                for(Translations tran : mSavedTranslations){
//                    sb.append(tran.getInLang());
//                    sb.append(tran.getInText());
//                    sb.append(tran.getOutLang());
//                    sb.append(tran.getOutText());
//                }
//                if(mSavedTranslations.size() > 0 ){
//                    Toast.makeText(MainTranslation.this,sb.toString(),Toast.LENGTH_LONG).show();
//                }else{
//                    Toast.makeText(MainTranslation.this,"Check some translations ",Toast.LENGTH_LONG).show();
//
//                }
//            }
//        });




//        starButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if((starButton).isChecked()){
//                    Intent i = new Intent(MainTranslation.this, FavouritesActivity.class);
//                    //REFERENCE RECYCLER
//                    RecyclerView rev= FavouritesActivity.findViewById(R.id.rec_v);
//
//                    //SET PROPERTIES
//                    rev.setLayoutManager(new LinearLayoutManager(rev.getContext()));
////                    rv.setItemAnimator(new DefaultItemAnimator());
//
//                    //ADAPTER
//                    RVAdapter adapter=new RVAdapter(rev.getContext(),savedTranslationLists);
//                    rv.setAdapter(adapter);}
//            }
//        });




//        starButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
//                sb = new StringBuffer();
//                for(Translations tran : mSavedTranslations){
//                    sb.append(tran.getInLang());
//                    sb.append(tran.getInText());
//                    sb.append(tran.getOutLang());
//                    sb.append(tran.getOutText());
//                }
//                if(mSavedTranslations.size() > 0 ){
//                    Toast.makeText(MainTranslation.this,sb.toString(),Toast.LENGTH_LONG).show();
//                }else{
//                    Toast.makeText(MainTranslation.this,"Check some translations ",Toast.LENGTH_LONG).show();
//
//                }
//            } } );



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


    private ArrayList<Translations> getTranslations(){
        ArrayList<Translations> trans = new ArrayList<>();
        for(int i = 0; i < translationLists.size();i++){
            Translations tran = translationLists.get(i);
            trans.add(tran);
        }
        return trans;
    }
//    public void AddData(String newEntry){
//        boolean insertData = mDatabaseHelper.addData(newEntry);
//
//        if(insertData){
//            System.out.println("inserted");
//        }else{
//            System.out.println("not inserted");
//        }
//    }


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
            startActivity(new Intent(MainTranslation.this, SettingsActivity.class));
            return true;
        }
        if (id == R.id.action_favorite){
            startActivity(new Intent(MainTranslation.this, FavouritesActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


//
//    protected void onSaveInstanceState(Bundle state) {
//        super.onSaveInstanceState(state);
//
//        // Save list state
//        mTranslationState = (ArrayList<Translations>) layoutManager.onSaveInstanceState();
//        state.putParcelable(LIST_STATE_KEY, mTranslationState);
//    }
//
//    protected void onRestoreInstanceState(Bundle state) {
//        super.onRestoreInstanceState(state);
//
//        // Retrieve list state and list/item positions
//        if(state != null)
//            mListState = state.getParcelable(LIST_STATE_KEY);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        if (mListState != null) {
//            layoutManager.onRestoreInstanceState(mListState);
//        }
//    }

    boolean check(String s) {
        if (s == null) // checks if the String is null
             {
            return false;
    }
    int len = s.length();
      for(int i = 0; i < len; i++) {
        // checks whether the character is not a letter
        // if it is not a letter ,it will return false
        if ((Character.isLetter(s.charAt(i)) == false)) {
            return false;
        }
    }
      return true;
}
}




