/*
 * Attribute.java
 *
 * Created on 19 May 2005, 06:21
 */

package treelearner;

/**
 *
 * @author Mad_Fool
 */
public class Attribute {
    
    private String name;
    private String value;
    
    /** Creates a new instance of Attribute */
    public Attribute(String newName, String newValue)
    {
        name = newName;
        value = newValue;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getValue()
    {
        return value;
    }
}
