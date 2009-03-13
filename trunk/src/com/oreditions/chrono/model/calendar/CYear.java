/*
 * CYear.java
 *
 * Created on 10 novembre 2007, 08:57
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.oreditions.chrono.model.calendar;

import java.util.*;
import java.io.*;
import com.oreditions.chrono.visitor.*;

/**
 *
 * @author Olivier
 */
public class CYear implements CalendarVisitable
{
    
    /** Creates a new instance of CYear */
    
    private TreeMap<Integer, CMonth> months = new TreeMap<Integer,CMonth>(); 
    private int year;
    
    public CYear(int year)
    {
        this.year = year;
    }

    public void acceptVisitor(CalendarVisitor vis)
    {
        vis.visit(this);
    }
    
    public TreeMap<Integer, CMonth> getMonthsInYear()
    {
        return months;
    }
    
    public int getYear()
    {
        return year;
    }

    
 
 
    public void add(int month, int day, int dow, File file)
    {
        CMonth tempmonth = months.get(new Integer(month));
        if (tempmonth==null)
        {
            //create the month
            tempmonth = new CMonth(month, year);
            months.put(new Integer(month), tempmonth);
        }
        tempmonth.add(day, dow, file);
    
    }
    
}
