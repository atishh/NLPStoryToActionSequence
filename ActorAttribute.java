//This is the attribute class of the actor. For example, whether actors are children, adults, beautiful etc.

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


public class ActorAttribute{

    public String  label;
    public String  sNicName;
    public boolean bIsMale;
    public boolean bIsFemale;
    public boolean bIsChildren;
    public boolean bIsAdult;
    public boolean bIsParent;
    public boolean bIsBeautiful;
    public boolean bIsSexy;
    public boolean bIsSmart;
    public String  sHairStyle;
    public String  sDressStyle;
    public boolean bIsFat;
    public boolean bIsShort;

    public ActorAttribute(String sLabel) {
        this.label 	= sLabel;
	sNicName 	= "";
	bIsMale 	= false;
	bIsFemale 	= false;
	bIsChildren 	= false;
	bIsAdult 	= false;
	bIsParent 	= false;
	bIsBeautiful 	= false;
	bIsSexy 	= false;
	bIsSmart 	= false;
	sHairStyle 	= "";
	sDressStyle 	= "";
	bIsFat 		= false;
	bIsShort 	= false;
    }

    public ActorAttribute() {
        label 		= "";
	sNicName 	= "";
	bIsMale 	= false;
	bIsFemale 	= false;
	bIsChildren 	= false;
	bIsAdult 	= false;
	bIsParent 	= false;
	bIsBeautiful 	= false;
	bIsSexy 	= false;
	bIsSmart 	= false;
	sHairStyle 	= "";
	sDressStyle 	= "";
	bIsFat 		= false;
	bIsShort 	= false;
    }

  public void populateActorAttribute(Node n)
  {
	//Process Children
	if(Synonyms.IsChildrenKeyword(n))
	{
  	     for (Edge e : n.outEdges)
  	     {
  	        if(e.label.equalsIgnoreCase("nsubj"))
  	        {
  	            Node TargetNode = e.target;
  	            ActorAttribute TargetNodeAttribute = TargetNode.mActorAttribute;
  	            TargetNodeAttribute.bIsChildren = true;
  	            System.out.println("Children found "+TargetNode.lex);
  	        }
	     }
	}
	//Process parent, sometimes it might happen that parent doesn't have name
	//in that case, make parents as actor.
        //1. Martin and Nora are parents; in this case bIgnoreParentKeyword will be true
        //   hence there is no need to make node parents as parent
        //2. They go out with their parents; in this case there is no name for the parents
	//   hence make node parents as parents and apparently as actor
	if(Synonyms.IsParentKeyword(n))
	{
	     boolean bIgnoreParentKeyword = false;
  	     for (Edge e : n.outEdges)
  	     {
  	        if(e.label.equalsIgnoreCase("nsubj"))
  	        {
	     	    bIgnoreParentKeyword = true;
  	            Node TargetNode = e.target;
  	            ActorAttribute TargetNodeAttribute = TargetNode.mActorAttribute;
  	            TargetNodeAttribute.bIsParent = true;
  	            System.out.println("Parent found "+TargetNode.lex);
  	        }
	     }
	     if(bIgnoreParentKeyword == false)
	     {
		n.mActorAttribute.bIsParent = true;
	     }
	}
   }

   public String getAttributeString()
   {
	String sReturnString = "";

	if(bIsMale)
		sReturnString += "male "; 	
	if(bIsFemale)
		sReturnString += "female "; 	
	if(bIsChildren) 	
		sReturnString += "children "; 	
	if(bIsAdult) 	
		sReturnString += "adult "; 	
	if(bIsParent) 	
		sReturnString += "parent "; 	
	if(bIsBeautiful) 	
		sReturnString += "beautiful "; 	
	if(bIsSexy)	
		sReturnString += "sexy "; 	
	if(bIsSmart) 	
		sReturnString += "smart "; 	
	sReturnString += sHairStyle; 	
	sReturnString += sDressStyle; 	
	if(bIsFat) 		
		sReturnString += "fat "; 	
	if(bIsShort)	
		sReturnString += "short";
 	
	return sReturnString;
   }
}

