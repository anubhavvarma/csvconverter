/*
 * HTMLVisitor.java
 *
 * Created on 25 novembre 2007, 16:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.oreditions.chrono.visitor;

import com.oreditions.chrono.util.ISO8859_1toHTML;
import com.oreditions.chrono.model.calendar.*;
import com.oreditions.chrono.engine.Config;
import com.oreditions.chrono.rendering.HTMLInit;
import java.util.*;
import java.io.*;
import java.text.DateFormat;


/**
 *
 * @author Olivier
 */
public class HTMLCalendarVisitor extends HTMLInit implements CalendarVisitor
{
    //Constants
    protected final static String[] monthstrings= {"January","February", "March",
        "April", "May", "June", "July", "August", "September", "October",
        "November", "December"};
    protected final static String daystrings[] = {"Sunday", "Monday", "Tuesday",
        "Wednesday", "Thursday", "Friday", "Saturday" };

    protected final static String mef = "style=\"border:1px solid #aaa; " +
            "background:#FFFFFF; padding-left:0em; padding-right:0em; " +
            "padding-top:0em; padding-bottom:0em; font-size:smaller;\"";

    //Variables
    protected Config conf;
    protected int[] width = {33,33,34};
    protected int day, dow;
  
    
    /** Creates a new instance of HTMLVisitor */
    public HTMLCalendarVisitor(){ super();}

    
    public void visit(CCalendar cal)
    {
        _kindof_ = "Calendar";
        initializeHeaders();

        TreeMap<Integer,CYear> years = cal.getYearsInCalendar();
        
        //Main loop
        Collection<CYear> coll = years.descendingMap().values();
        for(CYear y : coll)
            y.acceptVisitor(this);

        //Close the HTML sheets
        closeTrailers();
    }
    
    
    public void visit(CYear year)
    {
        TreeMap<Integer, CMonth> months = year.getMonthsInYear();
        //Generate the tag
        String temp = String.valueOf(year.getYear());
        
        //Generate the year line in the right frame
        f.println("<H2><A id=\"" + temp +"\">YEAR " + temp + "</A></H2>");

        //Generate the link in the index at the same time
        index.println("<P><B><A href=\"file://" + rightframe + "#" + temp
                + "\" target=\"" + target + "\">Year " + temp + "</A></B></P>");
        Collection<CMonth> coll = months.descendingMap().values();
        for(CMonth m : coll)
            m.acceptVisitor(this);
    }
    
    public void visit(CMonth month)
    {
        //Generate the tag
        String temp = monthstrings[month.getMonth()] + month.getYear();

        //Generate the tag in the right frame
        f.println("<H3><A id=\"" + temp + "\">" + monthstrings[month.getMonth()] + "</A></H3>");

        //generate entry in the index
        index.println("<li><font size=\"-1\"><A href=\"file://" + rightframe + "#" + temp
                + "\" target=\"" + target + "\">" + monthstrings[month.getMonth()]
                + "</A></font></li>");

        //Main loop
        TreeMap<Integer,CDay> days = month.getDaysInMonth();
        Collection<CDay> coll = days.descendingMap().values();
        
        //management of x columns
        int i=0;
        f.println("<TABLE width=\"100%\" " + mef +">");
        for(CDay d : coll)
        {
            switch(i)
            {
                case 0:
                    //ouverture du row et du premier jour
                    f.println("<TR><TD width=\""+ width[i] +"%\" valign=\"top\" " + mef + ">");
                    d.acceptVisitor(this);
                    f.println("</TD>");
                    //on incrémente
                    i++;
                    break;
                case 1:
                    //we are in the middle
                    f.println("<TD width=\""+ width[i] +"%\" valign=\"top\" " + mef + ">");
                    d.acceptVisitor(this);
                    f.println("</TD>");
                    i++;
                    break;
                case 2:
                    //fermeture du row
                    f.println("<TD width=\""+ width[i] +"%\" valign=\"top\" " + mef + ">");
                    d.acceptVisitor(this);
                    f.println("</TD></TR>");
                    //on réinitialise
                    i=0;
                    break;
                default:
                    throw new RuntimeException("HTMLVisitor: should not occur");
            }
        }

        //Avant de terminer la table, voir la valeur de i pour voir si des rows
        //ne seraient pas à fermer
        switch(i)
        {
            case 2:
                //on est au dernier row mais il nous faut ajouter une cellule vide
                f.println("<TD></TD></TR>");
                break;
            case 1:
                //on est au milieu mais il nous faut ajouter 2 cellules vides
                f.println("<TD></TD><TD></TD></TR>");
                break;
            case 0:
                //on a rien à faire
                break;
            default:
                throw new RuntimeException("HTMLVisitor: should not occur");
        }
        //dans tous les cas, il faut fermer la table
        f.println("</TABLE>");
    }
    
    public void visit(CDay day)
    {
        TreeMap<Date,File> files = day.getFilesInDay();
        f.println("<TABLE><TR><TD><H4>" + daystrings[day.getDOW()-1] + " " + day.getDay() + "</H4></TD></TR>");
        
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE);
        Collection<File> coll = files.descendingMap().values();
        for (File file : coll)
        {
            StringTokenizer st = new StringTokenizer((new Date(file.lastModified()).toString()));
            String temp1 = st.nextToken(), temp2=st.nextToken(), temp3=st.nextToken();            
            f.println("<TR><TD><font size=\"-1\">" + st.nextToken() + " : "
                + "<A href=\"file://" 
                + ISO8859_1toHTML.convertASCIItoHTML(file.getAbsolutePath())
                + "\" target=\"_new\">"
                + ISO8859_1toHTML.convertASCIItoHTML(file.getName())
                + "</A> - <A href=\"file://"
                + ISO8859_1toHTML.convertASCIItoHTML(
                    file.getAbsolutePath().substring(0, file.getAbsolutePath().indexOf(file.getName())))
                + "\" target=\"_new\">"
                + "Folder</A></font></TR></TD>");
        }
        f.println("</TABLE>");
    }

}

