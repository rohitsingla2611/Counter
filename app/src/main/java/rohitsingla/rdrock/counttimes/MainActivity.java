package rohitsingla.rdrock.counttimes;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String COUNTER_VALUE = "counterValue";
    ConstraintLayout counterLayout;
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

    @SuppressLint("ClickableViewAccessibility")
    void initViews() {
        editTextCounter = findViewById(R.id.editTextCounter);
        counterLayout = findViewById(R.id.counterLayout);

        counterLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                assert inputMethodManager != null;
                inputMethodManager.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getApplicationWindowToken(), 0);
                return true;
            }
        });
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
            editTextCounter.setText("" + valueDataRecovery);
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
                editTextCounter.setText("" + valueDataRecovery);
                buttonDataRecovery.setText("" + valueDataRecovery);
                saveData();

            }
        }
    }

    @SuppressLint("SetTextI18n")
    void resetCounter() {
        if (buttonSetUnsetCounterValue.getText().toString().equals("UNSET")) {

            count = 0;
            textViewCounter.setText("" + count);
            editTextCounter.setText("" + valueDataRecovery);

        }
    }

    @SuppressLint("SetTextI18n")
    void dataRecovery() {
        if (buttonSetUnsetCounterValue.getText().toString().equals("UNSET")) {

            textViewCounter.setText("" + valueDataRecovery);
            editTextCounter.setText("" + valueDataRecovery);
            count = valueDataRecovery;
        } else {
            editTextCounter.setText("" + valueDataRecovery);

        }
    }


    @SuppressLint("SetTextI18n")
    void setUnsetCounterValue() {
        editTextCounter.onEditorAction(EditorInfo.IME_ACTION_DONE);

        String inputCounterValue;
        inputCounterValue = editTextCounter.getText().toString();

        if (buttonSetUnsetCounterValue.getText().toString().equals("SET")) {

            if (inputCounterValue.isEmpty())
                count = 0;
            else
                count = Integer.parseInt(inputCounterValue);


            valueDataRecovery = count;
            textViewCounter.setText("" + count);
            buttonDataRecovery.setText("" + valueDataRecovery);
            editTextCounter.setVisibility(View.INVISIBLE);
            textViewCounter.setVisibility(View.VISIBLE);
            buttonSetUnsetCounterValue.setText("UNSET");
            saveData();


        } else {


            editTextCounter.setVisibility(View.VISIBLE);
            textViewCounter.setVisibility(View.INVISIBLE);
            buttonSetUnsetCounterValue.setText("SET");
            editTextCounter.setText("");


        }
    }

    public void updateCounter() {

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
