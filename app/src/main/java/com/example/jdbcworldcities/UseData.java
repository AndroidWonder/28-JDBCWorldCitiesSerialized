package com.example.jdbcworldcities;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class UseData extends Activity {

    private TextView textIt = null;
    private ArrayList<City> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_data);

        textIt = (TextView) findViewById(R.id.text);

        //get ArrayList<City> from intent object
        cities = (ArrayList<City>) getIntent().getSerializableExtra("list");

        //write data to UI
        for (int i = 0; i < cities.size(); i++) {
            textIt.append(cities.get(i) + "\n");
        }
    }
}
