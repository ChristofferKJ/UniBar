package com.group25.unibar.Helpers;

import android.content.Context;

import com.group25.unibar.models.BarInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


//Made with inspiration from assignment 1.
//Loads in all the Bars from the CSV file in assets and parses them to Bar objects.

public class CSVHelper {

    private final Context context;

    public CSVHelper(Context context)
    {
        this.context = context;
    }

    public ArrayList<BarInfo> CsvToBars(String filename) throws IOException {

        ArrayList<BarInfo> bars = new ArrayList<BarInfo>();


        InputStreamReader is = new InputStreamReader(context.getAssets().open(filename));

        BufferedReader reader = new BufferedReader(is);

        String line = reader.readLine();

        while((line=reader.readLine()) != null)
        {
            BarInfo tempBar = new BarInfo();
            String[] barStrings = line.split(";");
            tempBar.setBarName(barStrings[0]);
            tempBar.setLatitude(Float.valueOf(barStrings[1]));
            tempBar.setLongitude(Float.valueOf(barStrings[2]));
            tempBar.setImage_url(barStrings[3]);
            tempBar.setDescription(barStrings[4]);
            bars.add(tempBar);
        }

        return bars;
    }





}
