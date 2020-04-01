package rohitsingla.rdrock.counttimes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textViewCounter;
    Button buttonPlusOne,buttonReset,buttonDataRecovery,buttonMinusOne;
    int count = 0,valueDataRecovery = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

     void initViews() {
        textViewCounter = (TextView)findViewById(R.id.textViewCounter);
        buttonPlusOne = (Button) findViewById(R.id.buttonPlusOne);
        buttonReset = (Button) findViewById(R.id.buttonReset);
        buttonDataRecovery = (Button) findViewById(R.id.buttonDataRecovery);
        buttonMinusOne = (Button) findViewById(R.id.buttonMinusOne);

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

        }else if (v.getId() == R.id.buttonMinusOne){
            if (count>0) {
                count--;
                valueDataRecovery = count;
                textViewCounter.setText("" + count);
                buttonDataRecovery.setText("" + valueDataRecovery);
            }
        }else if(v.getId() == R.id.buttonReset){
            count =0;
            textViewCounter.setText("" + count);

        }else if(v.getId() == R.id.buttonDataRecovery){

            textViewCounter.setText("" + valueDataRecovery);
            count = valueDataRecovery;

        }

    }
}
