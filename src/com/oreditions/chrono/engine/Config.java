/*
 * Config.java
 *
 * Created on 10 novembre 2007, 12:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.oreditions.chrono.engine;

import java.util.*;
import java.io.*;

/**
 * This class encapsulates some treatments around the .properties files
 * required by the package.
 * @author orey
 */
public class Config
{
    //Constants
    private String PROPERTIES = null;
    public static final int LIMIT_DIR_NUMBER = 10; 
	
    public static final String COLUMN_NUMBER = "cn";
    public static final String DIRECTORY_PREFIX = "dir";
    public static final String INPUT_DIR = "inputdir";
    public static final String OUTPUTFILENAME = "outputfilename";
    public static final String DEFAULTFILENAME = "calendar.html";

    //Variables
    private Properties properties = new Properties();
    private static Config singleton=null;
    
    /**
     * Constructor is protected. This class runs in a singleton mode.
     * @param path Full path of the property file
     */
    protected Config(String path)
    {
        PROPERTIES = path;
        try
        {
            properties.load(new FileInputStream(PROPERTIES));
        }
        catch (IOException e)
        {
            System.out.println("Config class: Configuration file not found");
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    /**
     * API of the Config file
     * @param key
     * @return the value
     */
    public String getProperty(String key)
    {
        return properties.getProperty(key);
    }
    
    /**
     * Access to the singleton : created of not, else a reference is provided
     * @return the singleton
     */
    public static Config getInstance(String path)
    {
        if (singleton==null)
            singleton = new Config(path);
        return singleton;
    }

    public static Config getInstance()
    {
        if (singleton==null)
            throw new RuntimeException("Config class: bad use of the getInstance " +
                    "API ; should be used when the singleton is created and not before.");
        return singleton;
    }

    
    /**
     * This method reads the content of the configuration file to get the list
     * of various directories to inspect
     * @return the vector of directories
     */
    public Vector<String> getListOfDirectories()
    {
        String dir;
        Vector<String> list = new Vector<String>();
        for (int i=1;i<10;i++)
        {
                dir = properties.getProperty(DIRECTORY_PREFIX + i);
                if ((dir==null)||(dir.equals("")))
                        break;
                list.add(dir);
        }
    	return list;
    }
    
    public String getOutputFilename()
    {
    	String output = properties.getProperty(OUTPUTFILENAME);
        if ((output==null)||(output.equals("")))
        	output = DEFAULTFILENAME;
        return output;
    }
    
    
}
