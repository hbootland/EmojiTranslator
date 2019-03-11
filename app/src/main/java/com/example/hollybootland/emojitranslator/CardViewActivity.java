package com.example.hollybootland.emojitranslator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CardViewActivity extends Activity{

    TextView inputLang;
    TextView inputText;
    TextView outputLang;
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.item);
        inputLang = (TextView)findViewById(R.id.lang1);
        inputText = (TextView)findViewById(R.id.inputText);
        outputLang =  (TextView)findViewById(R.id.lang2);
        outputText =  (TextView)findViewById(R.id.outputText);


    }
}
