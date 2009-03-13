/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oreditions.chrono.rendering;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 *
 * @author olivier
 */
public class HTMLNavigator
{
    protected static final String mainFrameName = "index.html";
    protected static final String header = "header.html";
    protected static final String container = "container.html";

    //Main frame
    protected static final String BEGIN = "<html><head><title>Calendar Explorer</title>"
            +"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" /></head>";
    protected static final String FRAMESET = "<frameset rows=\"10%, 90%\"><frame src=\""+ header
            + "\" name=\"TOP\"><frame src=\"" + container + "\" name=\"BOTTOM\"></FRAMESET>";
    protected static final String END = "</html>";


    
    protected String targetfolder = null;
    protected FileOutputStream mf, head, cont;

    public HTMLNavigator(String targetfolder)
    {
        if ((!targetfolder.endsWith("/"))&&(!targetfolder.endsWith("\\")))
            targetfolder += "/";
        this.targetfolder = targetfolder;
    }

    public void createNavigator()
    {
        try
        {
            //Create the main frame
            mf = new FileOutputStream(new File(targetfolder + mainFrameName));
            PrintStream m = new PrintStream(mf);
            m.println(BEGIN + FRAMESET + END);
            m.flush();
            m.close();

            //Create the head
            head = new FileOutputStream(new File(targetfolder + header));
            PrintStream h = new PrintStream(head);
            //@todo Implement that
            h.flush();
            h.close();

            //Create the container
            cont = new FileOutputStream(new File(targetfolder + container));
            PrintStream c = new PrintStream(cont);
            //@todo Implement that
            c.flush();
            c.close();
        }
        catch(FileNotFoundException e)
        {

        }

    }


}
