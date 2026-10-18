package com.example.zdarz1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private double firstNumber = 0;
    private String selectedOperator = "";
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        View.OnClickListener symbolListener =
                v -> appendSymbol(((Button) v).getText().toString());

        int[] symbolIds = {
                R.id.button16,
                R.id.button13,
                R.id.button14,
                R.id.button15,
                R.id.button10,
                R.id.button11,
                R.id.button12,
                R.id.button7,
                R.id.button8,
                R.id.button9,
                R.id.button17
        };

        for (int id : symbolIds) {
            findViewById(id).setOnClickListener(symbolListener);
        }


        View.OnClickListener operatorListener = v -> {
            Button b = (Button) v;
            firstNumber = Double.parseDouble(textView.getText().toString().replace(",", "."));
            selectedOperator = b.getText().toString();
            isNewOperation = true;
        };

        int[] operatorIds = {
                R.id.button3,
                R.id.button4,
                R.id.button5,
                R.id.button6
        };

        for (int id : operatorIds) {
            findViewById(id).setOnClickListener(operatorListener);
        }


        findViewById(R.id.button18).setOnClickListener(v -> calculateResult());


        findViewById(R.id.button19).setOnClickListener(v -> clearAll());
    }


    private void appendSymbol(String symbol) {
        String currentText = textView.getText().toString();

        if (isNewOperation) {
            if (symbol.equals(",")) {
                textView.setText("0,");
            } else {
                textView.setText(symbol);
            }
            isNewOperation = false;
        } else {

            if (symbol.equals(",")) {
                if (!currentText.contains(",")) {
                    textView.append(",");
                }
            } else {
                if (currentText.equals("0")) {
                    textView.setText(symbol);
                } else {
                    textView.append(symbol);
                }
            }
        }
    }


    private void calculateResult() {
        if (selectedOperator.isEmpty()) return;

        double secondNumber = Double.parseDouble(textView.getText().toString().replace(",", "."));
        double result = 0;

        switch (selectedOperator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "x":
                result = firstNumber * secondNumber;
                break;
            case "÷":
                if (secondNumber == 0) {
                    textView.setText("Błąd");
                    isNewOperation = true;
                    selectedOperator = "";
                    return;
                }
                result = firstNumber / secondNumber;
                break;
        }


        String resultText;
        if (result == (long) result) {
            resultText = String.format("%d", (long) result);
        } else {
            resultText = String.valueOf(result).replace(".", ",");
        }

        textView.setText(resultText);
        isNewOperation = true;
        selectedOperator = "";
    }


    private void clearAll() {
        textView.setText("0");
        firstNumber = 0;
        selectedOperator = "";
        isNewOperation = true;
    }
}