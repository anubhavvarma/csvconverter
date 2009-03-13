/**
 * 
 */
package com.oreditions.chrono.rendering;

import com.oreditions.chrono.visitor.*;
import com.oreditions.chrono.engine.Config;
import com.oreditions.chrono.model.calendar.*;
import com.oreditions.chrono.model.typesize.*;
import java.io.*;

/**
 * @author orey
 *
 */
public class HTMLRenderer
{
    //Constants
    protected static final String DEFAULT_DIR = "./";
    protected static final String DEFAULT_NAME = "Calendar";
    protected static final String DEFAULT_EXT = ".html";
    protected static final String OTHER_EXT = ".htm";
    protected static final String LEFT = "left";
    protected static final String RIGHT = "right";

    //variables
    protected String framefilename = null;
    protected String leftframe = null, rightframe = null;
    
    protected CCalendar cal = null;
    protected HTMLCalendarVisitor cvisitor = null;
    protected TSHead head = null;
    protected HTMLTSVisitor tsvisitor = null;


    /**
	 * Constructor
	 * 
	 * @param cal
	 */
	public HTMLRenderer()
    {
        //first the HTML renderer must create frames.


    }
    
    public void setCCalendar(CCalendar cal)
    {
        this.cal = cal;
        cvisitor = new HTMLCalendarVisitor();
    }

    public void setTSHead(TSHead head)
    {
        this.head = head;
        tsvisitor = new HTMLTSVisitor();
	}
	
	public void dump()
	{
        //Prepare structure
        


        try
        {
            System.out.println("Dump begun...");
            
            //Opening html file frame name
            framefilename = Config.getInstance().getOutputFilename();
            checkOutputFileNameAndBuildFrameNames();

            //Create the print stream for frame set
            FileOutputStream output = new FileOutputStream(new File(framefilename));
            PrintStream ps = new PrintStream(output);
            buildFrames(ps);
            ps.flush();
            ps.close();

            //Create the print streams for the two frames
            FileOutputStream left = new FileOutputStream(new File(leftframe));
            PrintStream leftps = new PrintStream(left);
            FileOutputStream right = new FileOutputStream(new File(rightframe));
            PrintStream rightps = new PrintStream(right);

            cvisitor.initialize(leftps, rightps, RIGHT, rightframe);
                      
            cal.acceptVisitor(cvisitor);
            leftps.flush();
            leftps.close();
            rightps.flush();
            rightps.close();
            System.out.println("Dump end.");
        }
        catch (IOException e)
        {
            System.err.println("HMLCCalendarRenderer : IO Exception in dump method.");
            e.printStackTrace();
        }
	}

    protected void checkOutputFileNameAndBuildFrameNames()
    {
        if (framefilename == null)
        {
            framefilename = DEFAULT_DIR + DEFAULT_NAME + DEFAULT_EXT;
            leftframe = DEFAULT_DIR + DEFAULT_NAME + LEFT + DEFAULT_EXT;
            rightframe = DEFAULT_DIR + DEFAULT_NAME + RIGHT + DEFAULT_EXT;
            return;
        }
        if ( (!framefilename.endsWith(DEFAULT_EXT)) && (!framefilename.endsWith(OTHER_EXT)) )
        {
            leftframe = framefilename + LEFT + DEFAULT_EXT;
            rightframe = framefilename + RIGHT + DEFAULT_EXT;
            framefilename += DEFAULT_EXT;
            return;
        }
        String temp = null;
        if (framefilename.endsWith(DEFAULT_EXT))
        {
            temp = framefilename.substring(0, framefilename.length() - DEFAULT_EXT.length());
            leftframe = temp + LEFT + DEFAULT_EXT;
            rightframe = temp + RIGHT + DEFAULT_EXT;
            return;
        }
        if (framefilename.endsWith(OTHER_EXT))
        {
            temp = framefilename.substring(0, framefilename.length() - OTHER_EXT.length());
            leftframe = temp + LEFT + OTHER_EXT;
            rightframe = temp + RIGHT + OTHER_EXT;
            return;
        }
        throw new RuntimeException("HMLCCalendarRenderer : Error in checkOutputFileNameAndBuildFrameNames method");
    }

    protected void buildFrames(PrintStream ps)
    {
        ps.println("<HTML><HEAD><TITLE>Calendar explorer</TITLE>"
            +"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" /></HEAD>");
        ps.println("<FRAMESET COLS=\"20%,80%\"><FRAME SRC=\"file://" + leftframe + "\" NAME=\"" + LEFT + "\">");
        ps.println("<FRAME SRC=\"file://" + rightframe + "\" NAME=\"" + RIGHT + "\"></FRAMESET>");
        ps.println("</HTML>");
    }

}
