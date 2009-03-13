/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oreditions.chrono.engine;

import com.oreditions.chrono.rendering.HTMLRenderer;

/**
 *
 * @author olivier
 */
public class Entry
{
    /**
     * Main entry point of the java package.
     * Takes only one parameter : the name of the .properties file in which we
     * have the list of directories to be found and the directory for the output.
     * @param args
     */
    public static void main(String args[])
    {
        try
        {
            if (args.length != 1)
                usage();

            //Call the singleton
            Config conf = Config.getInstance(args[0]);

            //Create a new controller
            Controller a = new Controller(conf);
            a.initialize();

            //Choose the HMTL renderer
            HTMLRenderer rend = new HTMLRenderer();
            rend.setCCalendar(a.getCalendar());
            rend.setTSHead(a.getHead());

            rend.dump();
        }
        catch (Exception e)
        {
            System.err.println("An error occured in the program.");
            System.err.println("Exception message: " + e.getMessage());
            e.printStackTrace();
            System.err.println("Program aborted.");
        }
    }

    /**
     * Usage method. Terminates the program.
     */
    protected static void usage()
    {
        System.out.println("Usage : ");
        System.out.println("java -jar chrono3.jar file.property");
        System.exit(0);
    }

}
