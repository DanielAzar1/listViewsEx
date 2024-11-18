package com.example.listviewsex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.lang.Math;

public class resultActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    double frstNum, multNum, temp;
    boolean choice;

    TextView numTV, sumTV, indexTV, multTV, numAdd;
    ListView lv;
    String[] arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        numTV = findViewById(R.id.numTV);
        sumTV = findViewById(R.id.sumTV);
        indexTV = findViewById(R.id.indexTV);
        multTV = findViewById(R.id.multTV);
        numAdd = findViewById(R.id.numAdd);
        lv = findViewById(R.id.lv);

        Intent gi = getIntent();
        frstNum = gi.getDoubleExtra("frst", 0);
        multNum = gi.getDoubleExtra("mult", 0);
        choice = gi.getBooleanExtra("seriesChoice", false);

        multTV.setText(String.valueOf(multNum));
        numTV.setText(String.valueOf(frstNum));

        arr = new String[20];
        arr = initList(frstNum, choice, multNum, arr);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arr);
        lv.setAdapter(adp);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener(this);
    }

    public String[] initList(double x1, boolean seriesChoice, double d, String[] arr) {

        if (seriesChoice) // Geometric series
        {

            for (int i = 1; i <= 20; i++)
            {
                temp = x1 * Math.pow(d, i - 1);
                if(temp > 1000000 || temp < -1000000) arr[i - 1] = String.valueOf(shortNum(temp));
                else arr[i - 1] = String.valueOf(temp);
            }
        }
        else  // Mathematical series
        {
            for (int i = 1; i <= 20; i++)
            {
                temp = x1 * Math.pow(d, i - 1);
                if(temp > 1000000 || temp < -1000000) arr[i - 1] = String.valueOf(shortNum(temp));
                else arr[i - 1] = String.valueOf(temp);
            }
        }
        return arr;
    }

    public String shortNum(double value)
    {
        if(value>1000000||value<-1000000)
        {
            String scientificNotation = String.format("%.4e", value);
            String[] parts = scientificNotation.split("e");
            double base = Double.parseDouble(parts[0]) / 10.0;
            int exponent = Integer.parseInt(parts[1]) + 1;
            return String.format("%.4f * 10^%d", base, exponent);
        }
        return value+"";
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        double sum = 0;
        position++;
        if (choice)
        {
            sum = frstNum * (1 - Math.pow(multNum, position)) / (1 - multNum);
        }
        else
        {
            sum = (position * (2 * frstNum + (position - 1) * multNum)) / 2;
        }
        indexTV.setText(position+"");
        if (sum > 1000000 || sum < -1000000) {
            sumTV.setText(shortNum(sum)+"");
        } else {
            sumTV.setText(sum+"");
        }
    }
}