package com.oreditions.chrono.engine;

import java.util.*;
import java.io.*;
import com.oreditions.chrono.model.calendar.*;
import com.oreditions.chrono.model.typesize.TSHead;



/**
 * This class is the main entry point of the Java package.
 * Its use is above all to create the {@link com.oreditions.chrono.calendar.CCalendar CCalendar} class
 * which is a tree of classes containing all the files of the directories sorted
 * by date
 * @author orey
 */
public class Controller 
{
    protected Vector<File> allfiles = null;

    /**
     * This variable is the root class built by {@link Controller}.
     */
    protected CCalendar calendar    = new CCalendar();
    protected TSHead head           = new TSHead();

    protected Vector<TreeInitializer> inits = new Vector<TreeInitializer>();

    /**
     * The {@link Controller} class must have a reference to the {@link Config} class
     * in the same package.
     */
    protected Config conf = null;

    protected FileLister fl = null;

    /**
     * Constructor.
     */
    public Controller(Config conf)
    {
        this.conf = conf;
        inits.add(head);
        inits.add(calendar);
    }

    /**
     * This method builds the CCalendar tree.
     */
    public void initialize()
    {
        Vector<String> dirs = conf.getListOfDirectories();
        fl = new FileLister(dirs);
        allfiles = fl.buildFileList();

        for (TreeInitializer t : inits)
            t.initializeTree(allfiles);
    }



    /**
     * Internal technical method.
     * @return the CCalendar created
     */
    protected CCalendar getCalendar()
    {
        return calendar;
    }

    protected TSHead getHead()
    {
        return head;
    }

	
  }
