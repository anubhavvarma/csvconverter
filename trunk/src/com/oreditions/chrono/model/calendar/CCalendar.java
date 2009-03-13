/*
 * CCalendar.java
 *
 * Created on 10 novembre 2007, 09:07
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.oreditions.chrono.model.calendar;

import com.oreditions.chrono.engine.TreeInitializer;
import java.util.*;
import java.io.*;
import com.oreditions.chrono.visitor.*;

/**
 *
 * @author Olivier
 */
public class CCalendar implements TreeInitializer, CalendarVisitable
{
    private TreeMap<Integer,CYear> years = new TreeMap<Integer,CYear>();
    
    public CCalendar(){}

    public void initializeTree(Vector<File> allfiles)
    {
        Date filedate = null;
        Calendar cal = Calendar.getInstance();

        for (File file : allfiles)
        {
            //loop again in the files to determine their date
            filedate = new Date(file.lastModified());
            cal.setTime(filedate);

            //get the year for file
            int year = cal.get(Calendar.YEAR);
            CYear tempyear = years.get(new Integer(year));
            if (tempyear==null)
            {
                //create the year
                tempyear = new CYear(year);
                years.put(new Integer(year), tempyear);
            }
            tempyear.add(cal.get(Calendar.MONTH),
            		cal.get(Calendar.DAY_OF_MONTH),
            		cal.get(Calendar.DAY_OF_WEEK),
            		file);
        }
    }


    public void acceptVisitor(CalendarVisitor vis)
    {
        vis.visit(this);
    }
    
    public TreeMap<Integer,CYear> getYearsInCalendar()
    {
        return years;
    }
}
