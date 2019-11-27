package com.group25.unibar.Db;

import com.group25.unibar.models.Bar;

import java.util.ArrayList;

//https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples
// Thread safe example

public class BarsDb
{

    private static BarsDb instance;

    private BarsDb(){}

    private ArrayList<Bar> _barList;

    public ArrayList<Bar> get_barList()
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