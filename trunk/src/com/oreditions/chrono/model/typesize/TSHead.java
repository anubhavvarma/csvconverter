/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oreditions.chrono.model.typesize;

import com.oreditions.chrono.engine.TreeInitializer;
import com.oreditions.chrono.visitor.*;
import java.io.File;
import java.util.TreeMap;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author olivier
 */
public class TSHead implements TreeInitializer, TSVisitable
{
    protected final static String NO_EXT = "Files without extension";

    protected TreeMap<String, TSType> types = new TreeMap<String, TSType>();

    public TSHead(){}

    public void initializeTree(Vector<File> allfiles)
    {
        for (File f : allfiles)
        {
            String ext = TSHead.getFileExtension(f);
            TSType group = types.get(ext);
            if (group == null)
            {
                group = new TSType(ext);
                types.put(ext, group);
            }
            group.add(f);
        }
    }


    public void acceptVisitor(TSVisitor vis)
    {
        vis.visit(this);
    }

    public TreeMap<String, TSType> getTypesTree()
    {
        return types;
    }

    protected static String getFileExtension(File f)
    {
        String temp = f.getName(), temp2 = null;
        StringTokenizer st = new StringTokenizer(temp,".");

        //we need to count the tokens because there is at least one in the
        //case there is no extension
        if (st.countTokens() == 1)
            return NO_EXT;

        while(st.hasMoreTokens())
            temp2 = st.nextToken();

        //First case: we did  not enter the loop ; we will index on 'no extension'
        if (temp2==null)
            return NO_EXT;

        //Second case : we entered the loop, so we return the last token
        return temp2;
    }

    public static void main(String[] args)
    {
        File f1 = new File("/home/olivier/dev/_SVNCO_/CalendarExplore/filesamples/WITHOUT_ext");
        File f2 = new File("/home/olivier/dev/_SVNCO_/CalendarExplore/filesamples/arg.sdsfsgs");
        File f3 = new File("/home/olivier/dev/_SVNCO_/CalendarExplore/filesamples/titi.urg.titi.toto");
        File f4 = new File("/home/olivier/dev/_SVNCO_/CalendarExplore/filesamples/toto.pdf");
        File f5 = new File("/home/olivier/dev/_SVNCO_/CalendarExplore/filesamples/urg.urg.urg");
        System.out.println(TSHead.getFileExtension(f1));
        System.out.println(TSHead.getFileExtension(f2));
        System.out.println(TSHead.getFileExtension(f3));
        System.out.println(TSHead.getFileExtension(f4));
        System.out.println(TSHead.getFileExtension(f5));
    }


}
