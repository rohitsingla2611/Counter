package rohitsingla.rdrock.counttimes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textViewCounter;
    Button buttonPlusOne, buttonReset, buttonDataRecovery, buttonMinusOne;
    int count = 0, valueDataRecovery = 0;
    long backKeyPressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    void initViews() {
        textViewCounter = findViewById(R.id.textViewCounter);
        buttonPlusOne = findViewById(R.id.buttonPlusOne);
        buttonReset = findViewById(R.id.buttonReset);
        buttonDataRecovery = findViewById(R.id.buttonDataRecovery);
        buttonMinusOne = findViewById(R.id.buttonMinusOne);

        buttonPlusOne.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
        buttonDataRecovery.setOnClickListener(this);
        buttonMinusOne.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonPlusOne) {
            count++;
            valueDataRecovery = count;
            textViewCounter.setText("" + count);
            buttonDataRecovery.setText("" + valueDataRecovery);

        } else if (v.getId() == R.id.buttonMinusOne) {
            if (count > 0) {
                count--;
                valueDataRecovery = count;
                textViewCounter.setText("" + count);
                buttonDataRecovery.setText("" + valueDataRecovery);
            }
        } else if (v.getId() == R.id.buttonReset) {
            count = 0;
            textViewCounter.setText("" + count);

        } else if (v.getId() == R.id.buttonDataRecovery) {

            textViewCounter.setText("" + valueDataRecovery);
            count = valueDataRecovery;

        }

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
