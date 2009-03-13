/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oreditions.chrono.visitor;

import com.oreditions.chrono.rendering.HTMLInit;
import com.oreditions.chrono.model.typesize.TSHead;
import com.oreditions.chrono.model.typesize.TSType;
import java.io.PrintStream;
import java.util.Collection;
import java.util.TreeMap;

/**
 *
 * @author olivier
 */
public class HTMLTSVisitor extends HTMLInit implements TSVisitor
{

    public HTMLTSVisitor(){super();}

    public void visit(TSHead head)
    {
        _kindof_ = "Type Size";
        initializeHeaders();

        TreeMap<String, TSType> types = head.getTypesTree();

        //Main loop
        Collection<TSType> col = types.descendingMap().values();
        for (TSType t : col)
        {
            //@todo implement the specific HTML actions
            //@todo implement the visiting of low level

        }

        closeTrailers();
    }

    public void visit(TSType type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
