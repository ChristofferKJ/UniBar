package com.group25.unibar.Db;

import com.group25.unibar.models.BarInfo;

import java.util.ArrayList;

//https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples
// Thread safe example

public class BarsDb
{

    private static BarsDb instance;

    private BarsDb(){}

    private ArrayList<BarInfo> _barList;

    public void set_barList(ArrayList<BarInfo> bars)
    {
        _barList = bars;
    }

    public ArrayList<BarInfo> get_barList()
    {
        return _barList;
    }

    public static synchronized BarsDb getInstance()
    {
        if(instance == null)
        {
            instance = new BarsDb();
        }
        return instance;
    }
}