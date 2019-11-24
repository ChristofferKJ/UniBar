package com.group25.unibar.Helpers;

import android.content.Context;

import com.group25.unibar.models.Bar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CSVHelper {

    private final Context context;

    public CSVHelper(Context context)
    {
        this.context = context;
    }

    public ArrayList<Bar> CsvToBars(String filename) throws IOException {

        ArrayList<Bar> bars = new ArrayList<Bar>();


        InputStreamReader is = new InputStreamReader(context.getAssets().open(filename));

        BufferedReader reader = new BufferedReader(is);

        String line = reader.readLine();

        while((line=reader.readLine()) != null)
        {
            Bar tempBar = new Bar();
            String[] barStrings = line.split(";");
            tempBar.Name = barStrings[0];
            tempBar.Latitude = Float.valueOf(barStrings[1]);
            tempBar.Longitude = Float.valueOf(barStrings[2]);
            bars.add(tempBar);
        }

        return bars;
    }





}
