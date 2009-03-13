/*
 * CMonth.java
 *
 * Created on 10 novembre 2007, 09:05
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
public class CMonth implements CalendarVisitable
{

    private TreeMap<Integer,CDay> days = new TreeMap<Integer,CDay>();
    private int month;
    private int year;
   
    /** Creates a new instance of CMonth */
    public CMonth(int month, int year)
    {
        this.month = month;
        this.year = year;
      }

    public void acceptVisitor(CalendarVisitor vis)
    {
        vis.visit(this);
    }
    
    public int getMonth()
    {
        return month;
    }

    public int getYear()
    {
        return year;
    }

    public TreeMap<Integer,CDay> getDaysInMonth()
    {
        return days;
    }
    
    public void add(int day, int dow, File file)
    {
        CDay tempday = days.get(new Integer(day));
        if (tempday==null)
        {
            //create the month
            tempday = new CDay(day, dow);
            days.put(new Integer(day), tempday);
        }
        tempday.add(file);
    
    }
      
    
}
