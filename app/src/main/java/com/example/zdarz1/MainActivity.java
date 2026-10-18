package com.example.zdarz1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
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
                R.id.button17  };

        // Pętla przypisująca listener do każdego przycisku z tablicy
        for (int id : symbolIds) {
            findViewById(id).setOnClickListener(symbolListener);
        }
    }

    private void appendSymbol(String symbol) {

            textView.setText(symbol);


    }


    public void onClick0(View view) {
        textView.setText("0");
    }


    public void onClick1(View view) {
        textView.setText("1");
    }


    public void onClick2(View view) {
        textView.setText("2");
    }

    public void onClickCyfr(View view) {
        Button button = (Button) view;
        String Cyfra = button.getText().toString();
        textView.setText(Cyfra);
    }
}

