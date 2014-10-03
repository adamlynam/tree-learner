/*
 * ExampleData.java
 *
 * Created on 19 May 2005, 06:17
 */

package treelearner;

import java.util.*;

/**
 *
 * @author Mad_Fool
 */
public class ExampleData
{
    private ArrayList attributes;
    private boolean overall;
    
    /** Creates a new instance of ExampleData */
    public ExampleData(ArrayList newAttributes, boolean newOverall)
    {
        attributes = newAttributes;
        overall = newOverall;
    }
    
    public boolean hasAttributeWithValue(Attribute testAttribute, String value)
    {
        Iterator attributeList = attributes.iterator();
        while(attributeList.hasNext())
        {
            Attribute currentAttribute = (Attribute)attributeList.next();
            
            if (currentAttribute.getName().compareTo(testAttribute.getName()) == 0 && currentAttribute.getValue().compareTo(value) == 0)
            {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean getOverall()
    {
        return overall;
    }
}
