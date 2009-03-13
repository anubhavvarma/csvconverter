/*
 * CDay.java
 *
 * Created on 10 novembre 2007, 09:03
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
public class CDay implements CalendarVisitable
{
    
    //references only
    private TreeMap<Date,File> files = new TreeMap<Date,File>();
    private int day, dow;
    
    /** Creates a new instance of CDay */
    public CDay(int day, int dow)
    {
        this.day=day;
        this.dow=dow;
    }

    public void acceptVisitor(CalendarVisitor vis)
    {
        vis.visit(this);
    }
    
    public TreeMap<Date,File> getFilesInDay()
    {
        return files;
    }
    
    public int getDay()
    {
        return day;
    }
    
    public int getDOW()
    {
        return dow;
    }
    
 
    public void add(File file)
    {
        files.put(new Date(file.lastModified()), file);
    
    }
}
