package spinner;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import com.example.hollybootland.emojitranslator.R;


public class AndroidSpinnerExampleActivity extends Activity implements OnItemSelectedListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {

        // Code from: https://www.tutorialspoint.com/android/android_spinner_control.htm
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_translation);

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.language_spinner1);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Drop down spinner elements
        List<String> languages = new ArrayList<String>();
        languages.add("English");
        languages.add("Emoji");

        // Creating adapter for spinner
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages);

        // Drop down layout style - list view with radio button
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item you can retrieve the item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
        // interface callback
    }
}