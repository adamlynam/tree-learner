/*
 * TreeNode.java
 *
 * Created on 19 May 2005, 05:59
 */

package treelearner;

/**
 *
 * @author Mad_Fool
 */
public class TreeNode
{
    
    private TreeNode ifTrue;
    private TreeNode ifFalse;
    private Attribute attribute;
    
    /** Creates a new instance of TreeNode */
    public TreeNode(Attribute newAttribute, TreeNode newTrueNode, TreeNode newFalseNode)
    {
        attribute = newAttribute;
        ifTrue = newTrueNode;
        ifFalse = newFalseNode;
    }
    
    public Attribute getAttribute()
    {
        return attribute;
    }
    
    public TreeNode getTrueBranch()
    {
        return ifTrue;
    }
    
    public TreeNode getFalseBranch()
    {
        return ifFalse;
    }
}
