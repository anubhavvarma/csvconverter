
```
/*
 * This class converts a French string, usually encoded in ISO 8859-1 in HTML.
 * You can use this class in your programs. Copyleft 1001nuits.
 */

package foo.util;

import java.util.HashMap;

/**
 * @author 1001nuits www.1001nuits.org
 * This class is containing a singleton.
 **/
public class ISO8859_1toHTML
{
    protected HashMap<Integer, String> map = new HashMap<Integer, String>();
    protected static ISO8859_1toHTML singleton = null;

    /**
     * Constructor is not public (singleton pattern)
     */
    protected ISO8859_1toHTML()
    {
        //Initialize map
        map.put(new Integer(34), "&quot;");
        map.put(new Integer(38), "&amp;");
        map.put(new Integer(60), "&lt;");
        map.put(new Integer(62), "&gt;");
        map.put(new Integer(160), "&nbsp;");
        map.put(new Integer(161), "&iexcl;");
        map.put(new Integer(162), "&cent;");
        map.put(new Integer(163), "&pound;");
        map.put(new Integer(164), "&curren;");
        map.put(new Integer(165), "&yen;");
        map.put(new Integer(166), "&brvbar;");
        map.put(new Integer(167), "&sect;");
        map.put(new Integer(168), "&uml;");
        map.put(new Integer(169), "&copy;");
        map.put(new Integer(170), "&ordf;");
        map.put(new Integer(171), "&laquo;");
        map.put(new Integer(172), "&not;");
        map.put(new Integer(173), "&shy;");
        map.put(new Integer(174), "&reg;");
        map.put(new Integer(175), "&macr;");
        map.put(new Integer(176), "&deg;");
        map.put(new Integer(177), "&plusmn;");
        map.put(new Integer(178), "&sup2;");
        map.put(new Integer(179), "&sup3;");
        map.put(new Integer(180), "&acute;");
        map.put(new Integer(181), "&micro;");
        map.put(new Integer(182), "&para;");
        map.put(new Integer(183), "&middot;");
        map.put(new Integer(184), "&cedil;");
        map.put(new Integer(185), "&sup1;");
        map.put(new Integer(186), "&ordm;");
        map.put(new Integer(187), "&raquo;");
        map.put(new Integer(188), "&frac14;");
        map.put(new Integer(189), "&frac12;");
        map.put(new Integer(190), "&frac34;");
        map.put(new Integer(191), "&iquest;");
        map.put(new Integer(192), "&Agrave;");
        map.put(new Integer(193), "&Aacute;");
        map.put(new Integer(194), "&Acirc;");
        map.put(new Integer(195), "&Atilde;");
        map.put(new Integer(196), "&Auml;");
        map.put(new Integer(197), "&Aring;");
        map.put(new Integer(198), "&AElig;");
        map.put(new Integer(199), "&Ccedil;");
        map.put(new Integer(200), "&Egrave;");
        map.put(new Integer(201), "&Eacute;");
        map.put(new Integer(202), "&Ecirc;");
        map.put(new Integer(203), "&Euml;");
        map.put(new Integer(204), "&Igrave;");
        map.put(new Integer(205), "&Iacute;");
        map.put(new Integer(206), "&Icirc;");
        map.put(new Integer(207), "&Iuml;");
        map.put(new Integer(208), "&ETH;");
        map.put(new Integer(209), "&Ntilde;");
        map.put(new Integer(210), "&Ograve;");
        map.put(new Integer(211), "&Oacute;");
        map.put(new Integer(212), "&Ocirc;");
        map.put(new Integer(213), "&Otilde;");
        map.put(new Integer(214), "&Ouml;");
        map.put(new Integer(215), "&times;");
        map.put(new Integer(216), "&Oslash;");
        map.put(new Integer(217), "&Ugrave;");
        map.put(new Integer(218), "&Uacute;");
        map.put(new Integer(219), "&Ucirc;");
        map.put(new Integer(220), "&Uuml;");
        map.put(new Integer(221), "&Yacute;");
        map.put(new Integer(222), "&THORN;");
        map.put(new Integer(223), "&szlig;");
        map.put(new Integer(224), "&agrave;");
        map.put(new Integer(225), "&aacute;");
        map.put(new Integer(226), "&acirc;");
        map.put(new Integer(227), "&atilde;");
        map.put(new Integer(228), "&auml;");
        map.put(new Integer(229), "&aring;");
        map.put(new Integer(230), "&aelig;");
        map.put(new Integer(231), "&ccedil;");
        map.put(new Integer(232), "&egrave;");
        map.put(new Integer(233), "&eacute;");
        map.put(new Integer(234), "&ecirc;");
        map.put(new Integer(235), "&euml;");
        map.put(new Integer(236), "&igrave;");
        map.put(new Integer(237), "&iacute;");
        map.put(new Integer(238), "&icirc;");
        map.put(new Integer(239), "&iuml;");
        map.put(new Integer(240), "&eth;");
        map.put(new Integer(241), "&ntilde;");
        map.put(new Integer(242), "&ograve;");
        map.put(new Integer(243), "&oacute;");
        map.put(new Integer(244), "&ocirc;");
        map.put(new Integer(245), "&otilde;");
        map.put(new Integer(246), "&ouml;");
        map.put(new Integer(247), "&divide;");
        map.put(new Integer(248), "&oslash;");
        map.put(new Integer(249), "&ugrave;");
        map.put(new Integer(250), "&uacute;");
        map.put(new Integer(251), "&ucirc;");
        map.put(new Integer(252), "&uuml;");
        map.put(new Integer(253), "&yacute;");
        map.put(new Integer(254), "&thorn;");
        map.put(new Integer(255), "&yuml;");
    }

    /**
     * You can open this API if you wish.
     **/
    protected String getValue(Integer ref)
    {
        return map.get(ref);
    }

    /**
     * This static method returns a string with HTML characters in it.
     **/
    public static String convertASCIItoHTML(String input)
    {
        //If there is no instance of the class, create it.
        if (singleton==null)
            singleton = new ISO8859_1toHTML();

        String output = "";

        for (int i=0;i<input.length();i++)
        {
            // Get ascii code for char at i
            // Other method: int codePoint = String.valueOf(x).codePointAt(0);
            Integer num = new Integer((int) input.charAt(i));
            String value = singleton.getValue(num);
            if (value==null)
                output += input.substring(i, i+1);
            else
                output += value;
        }
        return output;
    }

    //Test runnable method
    public static void main(String[] args)
    {
        String output = ISO8859_1toHTML.convertASCIItoHTML("Chaîne aérien amère hôtel");
        System.out.println(output);
    }
}

```