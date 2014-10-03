/*
 * Main.java
 *
 * Created on 19 May 2005, 05:11
 */

package treelearner;

import java.util.*;

/**
 *
 * @author Mad_Fool
 */
public class TreeGenerator
{
    
    /** Creates a new instance of Main */
    public TreeGenerator()
    {
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        ArrayList list1 = new ArrayList();
        list1.add(new Attribute("A", "true"));
        list1.add(new Attribute("B", "true"));
        list1.add(new Attribute("C", "true"));
        
        ExampleData example1 = new ExampleData(list1, true);
        
        
        ArrayList list2 = new ArrayList();
        list2.add(new Attribute("A", "true"));
        list2.add(new Attribute("B", "false"));
        list2.add(new Attribute("C", "true"));
        
        ExampleData example2 = new ExampleData(list2, false);
        
        
        ArrayList list3 = new ArrayList();
        list2.add(new Attribute("A", "true"));
        list2.add(new Attribute("B", "false"));
        list2.add(new Attribute("C", "false"));
        
        ExampleData example3 = new ExampleData(list3, false);
        
        
        ArrayList examples = new ArrayList();
        examples.add(example1);
        examples.add(example2);
        examples.add(example3);
        
        Attribute attributeA = new Attribute("A", "true");
        Attribute attributeB = new Attribute("B", "true");
        Attribute attributeC = new Attribute("C", "true");
        
        ArrayList attributes = new ArrayList();
        attributes.add(attributeA);
        attributes.add(attributeB);
        attributes.add(attributeC);
        
        
        //generate tree
        TreeNode treeStartNode = generateTree(examples, attributes);
        
        //print tree
        displayTree(treeStartNode);
    }
    
    private static TreeNode generateTree(ArrayList examples, ArrayList attributes)
    {
        if(examples.isEmpty() || attributes.isEmpty())
        {
            return null;
        }
        
        Attribute bestAttribute = getBestAttribute(examples, attributes);
        //Attribute bestAttribute = myBestAttribute(examples, attributes);
        
        attributes.remove(bestAttribute);
        
        ArrayList trueExamples = new ArrayList();
        ArrayList falseExamples = new ArrayList();
        
        Iterator exampleList = examples.iterator();
        while(exampleList.hasNext())
        {
            ExampleData currentExample = (ExampleData)exampleList.next();
            
            if(currentExample.hasAttributeWithValue(bestAttribute, "true"))
            {
                trueExamples.add(currentExample);
            }
            else
            {
                falseExamples.add(currentExample);
            }
        }
        
        return new TreeNode(bestAttribute, generateTree(trueExamples, attributes), generateTree(falseExamples, attributes));
    }
    
    private static Attribute getBestAttribute(ArrayList examples, ArrayList attributes)
    {
        Attribute bestAttribute = null;
        double bestAttributeGain = 2;
        
        Iterator attributeList = attributes.iterator();
        while(attributeList.hasNext())
        {
            Attribute currentAttribute = (Attribute)attributeList.next();            
            
            double currentAttributeGain = 0;
            
            double truetrue = 0;
            double truefalse = 0;
            double falsetrue = 0;
            double falsefalse = 0;
            
            Iterator exampleList = examples.iterator();
            while(exampleList.hasNext())
            {
                ExampleData currentExample = (ExampleData)exampleList.next();
                
                if(currentExample.hasAttributeWithValue(currentAttribute, "true"))
                {
                    if (currentExample.getOverall())
                    {
                        truetrue++;
                    }
                    else
                    {
                        truefalse++;
                    }
                }
                else
                {
                    if (currentExample.getOverall())
                    {
                        falsetrue++;
                    }
                    else
                    {
                        falsefalse++;
                    }
                }
            }
            
            //truetrue and truefalse
            double totaltrue = truetrue + truefalse;
            double truetrueH = (-truetrue/totaltrue * (Math.log(truetrue/totaltrue) / (Math.log(2))));
            double truefalseH = (-truefalse/totaltrue * (Math.log(truefalse/totaltrue) / (Math.log(2))));
            currentAttributeGain += totaltrue / examples.size() * (truetrueH + truefalseH);
            
            //falsetrue and falsefalse
            double totalfalse = falsetrue + falsefalse;
            double falsetrueH = (-falsetrue/totalfalse * (Math.log(falsetrue/totalfalse) / (Math.log(2))));
            double falsefalseH = (-falsefalse/totalfalse * (Math.log(falsefalse/totalfalse) / (Math.log(2))));
            currentAttributeGain += totaltrue / examples.size() * (falsetrueH + falsefalseH);
            
            if(Double.isNaN(currentAttributeGain))
            {
                currentAttributeGain = 0;
            }
            
            if(currentAttributeGain < bestAttributeGain)
            {
                bestAttributeGain = currentAttributeGain;
                bestAttribute = currentAttribute;
            }
        }
        
        return bestAttribute;
    }
    
    private static void displayTree(TreeNode node)
    {
        String output = node.getAttribute().getName() + " expands into ";
        
        if(node.getTrueBranch() == null)
        {
            output = output + "an always true branch and ";
        }
        else
        {
            output = output + node.getTrueBranch().getAttribute().getName() + " and ";
        }
        if(node.getFalseBranch() == null)
        {
            output = output + "an always false branch";
        }
        else
        {
            output = output + node.getFalseBranch().getAttribute().getName();
        }
        
        System.out.println(output);
        
        if(node.getTrueBranch() != null)
        {
            displayTree(node.getTrueBranch());
        }
        if(node.getFalseBranch() != null)
        {
            displayTree(node.getFalseBranch());
        }
    }
    
    private static Attribute myBestAttribute(ArrayList examples, ArrayList attributes)
    {
        Attribute bestAttribute = null;
        int bestAttributeUtility = -1;
        
        Iterator attributeList = attributes.iterator();
        while(attributeList.hasNext())
        {
            Attribute currentAttribute = (Attribute)attributeList.next();
            
            int currentAttributeUtility = 0;
            
            Iterator exampleList = examples.iterator();
            while(exampleList.hasNext())
            {
                ExampleData currentExample = (ExampleData)exampleList.next();
                
                if(currentExample.getOverall() && currentExample.hasAttributeWithValue(currentAttribute, "true"))
                {
                    currentAttributeUtility++;
                }
            }
            
            if(currentAttributeUtility > bestAttributeUtility)
            {
                bestAttributeUtility = currentAttributeUtility;
                bestAttribute = currentAttribute;
            }
        }
        
        return bestAttribute;
    }
}
