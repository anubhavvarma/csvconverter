/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oreditions.chrono.model.typesize;

import com.oreditions.chrono.visitor.*;
import java.io.File;
import java.util.TreeMap;

/**
 *
 * @author olivier
 */
public class TSType implements TSVisitable
{
    protected String ext = null;
    protected TreeMap<Long, File> sizes = new TreeMap<Long, File>();

    public TSType(String ext)
    {
        this.ext = ext;
    }

    public void add(File f)
    {
        sizes.put(f.length(), f);
    }

	public void acceptVisitor(TSVisitor vis)
    {
        vis.visit(this);
    }

}
