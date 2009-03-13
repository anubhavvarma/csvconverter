

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oreditions.chrono.visitor;

import com.oreditions.chrono.model.typesize.*;

/**
 *
 * @author olivier
 */
public interface TSVisitor
{
    public void visit(TSHead head);
    public void visit(TSType type);
}
