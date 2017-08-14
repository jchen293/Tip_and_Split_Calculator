package io.github.httpsjchen293.tipandsplitcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    EditText amount, tip;
    Button add, minus;
    TextView numberPeople, totalAmount, personPay;
    Button cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = (EditText) findViewById(R.id.editText);
        tip = (EditText) findViewById((R.id.editText2));

        numberPeople = (TextView) findViewById(R.id.editText3);
        totalAmount = (TextView) findViewById(R.id.textView3);
        personPay = (TextView) findViewById(R.id.textView4);

        add = (Button) findViewById(R.id.addPeople);
        minus = (Button) findViewById(R.id.minusPeople);
        cal = (Button) findViewById(R.id.cal);

        add.setOnClickListener(new View.OnClickListener() {
            Integer num = Integer.parseInt(numberPeople.getText().toString());

            @Override
            public void onClick(View v) {
                if (!num.equals(numberPeople.getText().toString())) {
                    Integer num12 = Integer.parseInt(numberPeople.getText().toString());
                    num = num12;
                }
                if (num > 0) {
                    minus.setClickable(true);
                }
                num++;
                numberPeople.setText(toString().valueOf(num));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            Integer num1 = Integer.parseInt(numberPeople.getText().toString());

            @Override
            public void onClick(View v) {

                if (!num1.equals(numberPeople.getText().toString())) {
                    Integer num12 = Integer.parseInt(numberPeople.getText().toString());
                    num1 = num12;
                }
                if (num1 < 3) {
                    showToast(v);
                    minus.setClickable(false);
                }
                num1--;
                numberPeople.setText(toString().valueOf(num1));
            }
        });
        cal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                double totalAmountt = Double.parseDouble(amount.getText().toString());
                double totalTip = Double.parseDouble(tip.getText().toString());
                double percentTip = ((totalTip / 100) + 1);
                double finalTotal = roundTwoDecimals(totalAmountt * percentTip);

                int persons = Integer.parseInt(numberPeople.getText().toString());
                double split = roundTwoDecimals(finalTotal / persons);

                totalAmount.setText("Total Amount: " + toString().valueOf(finalTotal));
                personPay.setText("Each person pays: " + toString().valueOf(split));
            }
        });
    }
        public double roundTwoDecimals(double d) {
            DecimalFormat twoDForm = new DecimalFormat("#.##");
            return Double.valueOf(twoDForm.format(d));
        }
        public void showToast(View v){

            Toast.makeText(this,"Minimum person is 1", Toast.LENGTH_SHORT).show();
        }
}