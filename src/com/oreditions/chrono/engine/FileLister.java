/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oreditions.chrono.engine;

import java.io.File;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author olivier
 */
public class FileLister
{
    
    protected Vector<String> rootdirectories = null;
    //protected HashMap<Long, File> datedfiles = new HashMap<Long, File>();
    //protected HashMap<String, File> typedfiles = new HashMap<String, File>();
    
    protected Vector<File> allfiles = new Vector<File>();


    public FileLister(Vector<String> rootdirectories)
    {
        this.rootdirectories = rootdirectories;
    }

    public Vector<File> buildFileList()
    {
        for (String rootdirectory: rootdirectories)
        {
            File rootdir = new File(rootdirectory);
            listFilesInDirectoryAndSubDirectories(rootdir);
        }
        return allfiles;
    }

    /*public HashMap<Long, File> getDatedFiles()
    {
        return datedfiles;
    }

    public HashMap<String, File> getTypedFiles()
    {
        return typedfiles;
    }*/

    protected void listFilesInDirectoryAndSubDirectories(File dir)
    {
        if (!dir.isDirectory())
        {
            System.err.println("Error in FileLister::listFilesInDirectoryAndSubDirectories");
            System.err.println(dir.getName() + " is not a directory, skipping...");
            return;
        }

        //Get the files and subdirectories in the directory
        File[] files = dir.listFiles();

        //directory is empty
        if (files.length==0)
            return;

        //create the subdirectory temp vector
        Vector<File> subdirectories = new Vector<File>();

        //Loop over the elements in the directory
        for (int i=0;i<files.length;i++)
        {
            if (files[i].isDirectory())
                subdirectories.add(files[i]);
            else
                allfiles.add(files[i]);
            //{
                //it is a file
                //datedfiles.put(files[i].lastModified(), files[i]);
                //typedfiles.put(FileLister.getFileExtension(files[i]), files[i]);
            //}
        }

        //sub directories analysis
        for (File file : subdirectories)
            listFilesInDirectoryAndSubDirectories(file);
    }

    

}
