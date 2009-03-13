/**
 * 
 */
package com.oreditions.chrono.visitor;

import com.oreditions.chrono.model.calendar.*;

/**
 * @author orey
 *
 */
public interface CalendarVisitor
{
	public void visit(CCalendar cal);
	public void visit(CYear year);
	public void visit(CMonth month);
	public void visit(CDay day);
}
