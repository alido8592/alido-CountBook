package comalido8592.github.alido_countbook;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CountBookMain extends AppCompatActivity implements View.OnClickListener {

    private static final String FILENAME = "file.sav";
    private ArrayList<CounterRow> counterList; //list of counters
    private ArrayAdapter<CounterRow> adapter;

    Button save;
    Button newLine;
    Button summary;
    Button incr;
    Button decr;

    EditText itemName;
    EditText counterVal;

    private ListView oldCounterList;

    /** called when the activity is first created */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_book_main);

        save = (Button) findViewById(R.id.button_save);
        newLine = (Button) findViewById(R.id.button_newline);
        summary = (Button) findViewById(R.id.button_summary);
        incr = (Button) findViewById(R.id.button_incr); // to increase int in counterList entry
        decr = (Button) findViewById(R.id.button_decr); // to decrease int in counterList entry

        itemName = (EditText) findViewById(R.id.itemname1); //need to move to CounterRow
        counterVal = (EditText) findViewById(R.id.counterVal); //need to move to CounterRow

        oldCounterList = (ListView) findViewById(R.id.list_counters);

        save.setOnClickListener(this);
        newLine.setOnClickListener(this);
        summary.setOnClickListener(this);
        incr.setOnClickListener(this);
        decr.setOnClickListener(this);

    }

    @Override
    protected void onStart() {

     // activity upon startup (load from file)
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<CounterRow>(this, R.layout.list_item, counterList);
        oldCounterList.setAdapter(adapter);

     }

    @Override
    public void onClick(View v) {

        /**
         * method based off of
         * https://github.com/abdulajet/Android-counter/blob/master/app/src/main/java/me/abdulajet/counter/MainActivity.java
         * as of 2017-10-02
         */

        switch(v.getId()){
            case R.id.button_save: // save

                saveInFile();

        } //end of button_save

    }

    private void loadFromFile() {

        // loads previous data from file (to be used onstart)
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken from https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-09-19
            Type listType = new TypeToken<ArrayList<CounterRow>>(){}.getType();
            counterList = gson.fromJson(in, listType);

        }
        catch (FileNotFoundException e) {
            counterList = new ArrayList<CounterRow>();
        }
        catch (IOException e) {
            throw new RuntimeException();
        }

    } //end of loadFromFile

    private void saveInFile() {

        // saves data into file (save button)

        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(counterList,out);
            out.flush();

            fos.close();

        }
        catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
        catch (IOException e) {
            throw new RuntimeException();
        }

    } //end of saveInFile

    /**@Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }*/
}