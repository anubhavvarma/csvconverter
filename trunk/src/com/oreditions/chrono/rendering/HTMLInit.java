/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oreditions.chrono.rendering;

import java.io.PrintStream;
import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author olivier
 */
public abstract class HTMLInit
{

    protected PrintStream f = null, index = null;
    protected String target = null, rightframe = null;
    protected String datev = null, timev = null;

    //to be overloaded
    protected String _kindof_ = "OVERLOAD";

    public HTMLInit()
    {
        Date date = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
        DateFormat df2 = DateFormat.getTimeInstance(DateFormat.LONG);
        datev = df.format(date);
        timev = df2.format(date);
    }

    public void initialize(PrintStream leftps,
            PrintStream rightps,
            String target,
            String rightframe)
    {
        // get the printstreams
        index = leftps;
        f = rightps;
        this.target = target;
        this.rightframe = rightframe;
    }

    protected void initializeHeaders()
    {
        String header = "<html><head><title>Calendar Explorer</title>"+
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />" +
                "</head><h2>" + _kindof_ + " Explorer - generated : " + datev + ", "+ timev +"</h2>";
        f.println(header);

        String headerindex = "<html><head><title>Calendar explorer</title>"+
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />" +
                "</head><h4>" + _kindof_ + " Explorer<BR>Generated : " + datev + ", "+ timev +"</h4>";
        index.println(headerindex);
    }

    protected void closeTrailers()
    {
        f.println("</HTML>");
        index.println("</HTML>");
    }
    
}
