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
        textViewCounter = findViewById(R.id.textViewCounter);
        buttonSetUnsetCounterValue = findViewById(R.id.buttonSetUnsetCounterValue);
        buttonPlusOne = findViewById(R.id.buttonPlusOne);
        buttonReset = findViewById(R.id.buttonReset);
        buttonDataRecovery = findViewById(R.id.buttonDataRecovery);
        buttonMinusOne = findViewById(R.id.buttonMinusOne);

        buttonSetUnsetCounterValue.setOnClickListener(this);
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
            case R.id.buttonSetUnsetCounterValue:
                setUnsetCounterValue();
                return;

            case R.id.buttonPlusOne:
                plusOne();
                return;

            case R.id.buttonMinusOne:
                minusOne();
                return;

            case R.id.buttonReset:
                resetCounter();
                return;

            case R.id.buttonDataRecovery:
                dataRecovery();


        }

    }

    @SuppressLint("SetTextI18n")
    void plusOne() {
        if (buttonSetUnsetCounterValue.getText().toString().equals("UNSET")) {
            count++;
            valueDataRecovery = count;
            textViewCounter.setText("" + count);
            buttonDataRecovery.setText("" + valueDataRecovery);
            saveData();
        }
    }

    @SuppressLint("SetTextI18n")
    void minusOne() {
        if (buttonSetUnsetCounterValue.getText().toString().equals("UNSET")) {

            if (count > 0) {
                count--;
                valueDataRecovery = count;
                textViewCounter.setText("" + count);
                buttonDataRecovery.setText("" + valueDataRecovery);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    void resetCounter() {
        if (buttonSetUnsetCounterValue.getText().toString().equals("UNSET")) {

            count = 0;
            textViewCounter.setText("" + count);
        }
    }

    @SuppressLint("SetTextI18n")
    void dataRecovery() {
        if (buttonSetUnsetCounterValue.getText().toString().equals("UNSET")) {

            textViewCounter.setText("" + valueDataRecovery);
            count = valueDataRecovery;
        }
    }


    @SuppressLint("SetTextI18n")
    void setUnsetCounterValue() {

        String inputCounterValue;
        inputCounterValue = editTextCounter.getText().toString();

        if (buttonSetUnsetCounterValue.getText().toString().equals("SET")) {

            if (inputCounterValue.isEmpty())
                count = 0;
            else
                count = Integer.parseInt(inputCounterValue);


            valueDataRecovery = count;
            String convertIntToString = Integer.toString(count);
            textViewCounter.setText(convertIntToString);
            buttonDataRecovery.setText(convertIntToString);
            editTextCounter.setText("");

            editTextCounter.setVisibility(View.INVISIBLE);
            textViewCounter.setVisibility(View.VISIBLE);
            buttonSetUnsetCounterValue.setText("UNSET");

        } else {


            editTextCounter.setVisibility(View.VISIBLE);
            textViewCounter.setVisibility(View.INVISIBLE);
            buttonSetUnsetCounterValue.setText("SET");

        }
    }

    public void updateCounter() {

        editTextCounter.setText(textSaved);
        textViewCounter.setText(textSaved);
        buttonDataRecovery.setText(textSaved);
        count = Integer.parseInt(textViewCounter.getText().toString());
        valueDataRecovery = count;

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
