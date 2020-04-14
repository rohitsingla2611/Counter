package rohitsingla.rdrock.counttimes;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String COUNTER_VALUE = "counterValue";
    TextView textViewCounter;
    EditText editTextCounter;
    String textSaved;
    Button buttonSetUnsetCounterValue;
    Button buttonPlusOne, buttonReset;
    Button buttonDataRecovery, buttonMinusOne;
    int count = 0, valueDataRecovery = 0;
    long backKeyPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    void initViews() {
        editTextCounter = findViewById(R.id.editTextCounter);

        Toast.makeText(this, editTextCounter.getText().toString(), Toast.LENGTH_SHORT).show();
        count = Integer.parseInt(editTextCounter.getText().toString());
        valueDataRecovery = count;
        editTextCounter.setVisibility(View.INVISIBLE);
        textViewCounter.setVisibility(View.VISIBLE);
        textViewCounter = findViewById(R.id.textViewCounter);
        buttonPlusOne = findViewById(R.id.buttonPlusOne);
        buttonReset = findViewById(R.id.buttonReset);
        buttonDataRecovery = findViewById(R.id.buttonDataRecovery);
        buttonMinusOne = findViewById(R.id.buttonMinusOne);

        buttonPlusOne.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
        buttonDataRecovery.setOnClickListener(this);
        buttonMinusOne.setOnClickListener(this);
        loadData();
        updateCounter();

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textViewCounter:
                count = Integer.parseInt(textViewCounter.getText().toString());

                return;

            case R.id.buttonPlusOne:
                count++;
                valueDataRecovery = count;
                textViewCounter.setText("" + count);
                buttonDataRecovery.setText("" + valueDataRecovery);
                saveData();
                return;

            case R.id.buttonMinusOne:
                if (count > 0) {
                    count--;
                    valueDataRecovery = count;
                    textViewCounter.setText("" + count);
                    buttonDataRecovery.setText("" + valueDataRecovery);
                }
                return;
            case R.id.buttonReset:
                count = 0;
                textViewCounter.setText("" + count);
                return;
            case R.id.buttonDataRecovery:

                textViewCounter.setText("" + valueDataRecovery);
                count = valueDataRecovery;

        }
    }


    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(COUNTER_VALUE, textViewCounter.getText().toString());
        editor.apply();

    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        textSaved = sharedPreferences.getString(COUNTER_VALUE, "0");

    }

    public void updateCounter() {
        textViewCounter.setText(textSaved);
        buttonDataRecovery.setText(textSaved);
        count = Integer.parseInt(textViewCounter.getText().toString());
        valueDataRecovery = count;

    }

    @Override
    public void onBackPressed() {
        if (backKeyPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(this, "Press again to Exit", Toast.LENGTH_SHORT).show();
        }
        backKeyPressedTime = System.currentTimeMillis();
    }
}
