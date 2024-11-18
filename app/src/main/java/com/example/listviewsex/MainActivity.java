package com.example.listviewsex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Switch seriesTypeSwitch;
    EditText mult, frst;
    Button nextBtn;

    double frstNum, multNum;
    String temp;
    boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seriesTypeSwitch = findViewById(R.id.seriesTypeSwitch);
        mult = findViewById(R.id.multEdt);
        frst = findViewById(R.id.frstEdt);
        nextBtn = findViewById(R.id.nexrBtn);
    }

    public void nxt(View view) {
        temp = frst.getText().toString();
        if (checkNum(temp))
        {
            frstNum = Double.parseDouble(temp);

            temp = mult.getText().toString();
            if (checkNum(temp))
            {
                multNum = Double.parseDouble(temp);

                isChecked = seriesTypeSwitch.isChecked();

                Toast.makeText(this, "Valid", Toast.LENGTH_SHORT).show();
                Intent si = new Intent(this,resultActivity.class);
                si.putExtra("frst", frstNum);
                si.putExtra("mult", multNum);
                si.putExtra("seriesChoice", isChecked);
                startActivity(si);
            }
            else Toast.makeText(this, "Invalid number!", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Invalid number!", Toast.LENGTH_SHORT).show();
    }

    public boolean checkNum(String x)
    {
        if (x.isEmpty()) return false;
        else if (x.equals(".") || x.equals("+") || x.equals("-") || x.equals("-.") || x.equals("+.")) {return false;}
        return true;
    }
}