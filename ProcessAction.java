
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.Map;
import java.util.*;

//Transitive action are of type noun verb noun
//The teenager earned a speeding ticket.

public class ProcessAction
{

  public static String processActionTypeWalk()
  {
     String OutFile = "walk";
     OutFile += "\n";
     Node root = g.root;
     String Actor = "";
     String Where = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj"))
        {
            Node ActorNode = e.target;
            Node ActualActorNode = null;
            if(!(ActorNode.gender == Gender.GENDER_FEMALE || ActorNode.gender == Gender.GENDER_MALE || ActorNode.isActorPronoun == true))
            {
		ActualActorNode = StanfordCoreNlpDemo.identifyTheActor(ActorNode);
            }
	    if(ActualActorNode != null)
		ActorNode = ActualActorNode;
            if(ActorNode.isActorPronoun)
		Actor = ActorNode.ActorNameInCasePronoun;
            else
	    	Actor = ActorNode.lex;
        }
	else if(e.label.equalsIgnoreCase("prep_in"))
        {
            //This is currently approximate
	    Where = e.target.lex;
        }
	else if(e.label.equalsIgnoreCase("prep_towards"))
        {
            //This is currently approximate
	    Where = e.target.lex;
        }
     }
     OutFile += Actor;
     OutFile += " \n";
     OutFile += "\n";//Actor2 is empty
     OutFile += Where;
     OutFile += " \n";
     StanfordCoreNlpDemo.PotentialBackground.add(Where);
     //OutFile += "Background:";
     OutFile += Where;
     OutFile += " \n";
     return OutFile;
  }
 

  public static String processActionTypeRead()
  {
     String OutFile = "read";
     OutFile += "\n";
     Node root = g.root;
     String Actor = "";
     String Where = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj"))
        {
            Node ActorNode = e.target;
            Node ActualActorNode = null;
            if(!(ActorNode.gender == Gender.GENDER_FEMALE || ActorNode.gender == Gender.GENDER_MALE || ActorNode.isActorPronoun == true))
            {
		ActualActorNode = StanfordCoreNlpDemo.identifyTheActor(ActorNode);
            }
	    if(ActualActorNode != null)
		ActorNode = ActualActorNode;
            if(ActorNode.isActorPronoun)
		Actor = ActorNode.ActorNameInCasePronoun;
            else
	    	Actor = ActorNode.lex;
        }
	else if(e.label.equalsIgnoreCase("dobj"))
        {
            //This is currently approximate
	    Where = e.target.lex;
        }
     }
     OutFile += Actor;
     OutFile += " \n";
     OutFile += "\n"; //Actor2 is empty
     OutFile += Where;
     OutFile += " \n";
     StanfordCoreNlpDemo.PotentialBackground.add(Where);
     //OutFile += "Background:";
     OutFile += Where;
     OutFile += " \n";
     return OutFile;
  }

  public static String processActionTypeStand()
  {
     String OutFile = "stand";
     OutFile += "\n";
     Node root = g.root;
     String Actor = "";
     String Where = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj"))
        {
            Node ActorNode = e.target;
            Node ActualActorNode = null;
            if(!(ActorNode.gender == Gender.GENDER_FEMALE || ActorNode.gender == Gender.GENDER_MALE || ActorNode.isActorPronoun == true))
            {
		ActualActorNode = StanfordCoreNlpDemo.identifyTheActor(ActorNode);
            }
	    if(ActualActorNode != null)
		ActorNode = ActualActorNode;
            if(ActorNode.isActorPronoun)
		Actor = ActorNode.ActorNameInCasePronoun;
            else
	    	Actor = ActorNode.lex;
        }
	else if(e.label.equalsIgnoreCase("prep_near"))
        {
            //This is currently approximate
	    Where = e.target.lex;
        }
     }
     OutFile += Actor;
     OutFile += " \n";
     OutFile += "\n"; //Actor2 is empty
     OutFile += Where;
     OutFile += " \n";
     StanfordCoreNlpDemo.PotentialBackground.add(Where);
     //OutFile += "Background:";
     OutFile += Where;
     OutFile += " \n";
     return OutFile;
  }

  public static String processActionTypeLook()
  {
     String OutFile = "look";
     OutFile += "\n";
     Node root = g.root;
     String Actor = "";
     String Where = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj"))
        {
            Node ActorNode = e.target;
            Node ActualActorNode = null;
            if(!(ActorNode.gender == Gender.GENDER_FEMALE || ActorNode.gender == Gender.GENDER_MALE || ActorNode.isActorPronoun == true))
            {
		ActualActorNode = StanfordCoreNlpDemo.identifyTheActor(ActorNode);
            }
	    if(ActualActorNode != null)
		ActorNode = ActualActorNode;
            if(ActorNode.isActorPronoun)
		Actor = ActorNode.ActorNameInCasePronoun;
            else
	    	Actor = ActorNode.lex;
        }
	else if(e.label.equalsIgnoreCase("prep_at"))
        {
            //This is currently approximate
	    Where = e.target.lex;
        }
	else if(e.label.equalsIgnoreCase("prep_to"))
        {
            //This is currently approximate
	    Where = e.target.lex;
        }
	else if(e.label.equalsIgnoreCase("prep"))
        {
            //This is currently approximate
	    Where = e.target.lex;
        }
     }
     OutFile += Actor;
     OutFile += " \n";
     OutFile += "\n"; //Actor2 is empty
     OutFile += Where;
     OutFile += " \n";
     OutFile += "\n";//Background is empty
     return OutFile;
  }

  public static String processActionTypeStart()
  {
     String OutFile = "start";
     OutFile += "\n";
     Node root = g.root;
     String Actor = "";
     String Where = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj"))
        {
            Node ActorNode = e.target;
            Node ActualActorNode = null;
            if(!(ActorNode.gender == Gender.GENDER_FEMALE || ActorNode.gender == Gender.GENDER_MALE || ActorNode.isActorPronoun == true))
            {
		ActualActorNode = StanfordCoreNlpDemo.identifyTheActor(ActorNode);
            }
	    if(ActualActorNode != null)
		ActorNode = ActualActorNode;
            if(ActorNode.isActorPronoun)
		Actor = ActorNode.ActorNameInCasePronoun;
            else
	    	Actor = ActorNode.lex;
        }
	else if(e.label.equalsIgnoreCase("xcomp"))
        {
            //This is currently approximate
	    Where = e.target.lex;
        }
     }
     OutFile += Actor;
     OutFile += " \n";
     OutFile += "\n"; //Actor2 is empty
     OutFile += Where;
     OutFile += " \n";
     OutFile += "\n"; //Background is empty
     return OutFile;
  }

  public static String processActionTypeRoll()
  {
     String OutFile = "roll";
     OutFile += "\n";
     Node root = g.root;
     String Actor = "";
     String Where = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nn"))
        {
            Node ActorNode = e.target;
            Node ActualActorNode = null;
            if(!(ActorNode.gender == Gender.GENDER_FEMALE || ActorNode.gender == Gender.GENDER_MALE || ActorNode.isActorPronoun == true))
            {
		ActualActorNode = StanfordCoreNlpDemo.identifyTheActor(ActorNode);
            }
	    if(ActualActorNode != null)
		ActorNode = ActualActorNode;
            if(ActorNode.isActorPronoun)
		Actor = ActorNode.ActorNameInCasePronoun;
            else
	    	Actor = ActorNode.lex;
        }
     }
     OutFile += Actor;
     OutFile += " \n";
     OutFile += "\n"; //Actor2 is empty
     OutFile += "\n"; //Where is empty
     OutFile += "\n"; //Background is empty
     return OutFile;
  }

  public static String processSayWhat()
  {
     String What = "";
     boolean bIsInsideDoubleQuotes = false;
     for (Integer i : g.nodes.keySet()) 
     {
     	Node n = g.nodes.get(i);
        if("\"".equals(n.pos))
        {
	   bIsInsideDoubleQuotes = !bIsInsideDoubleQuotes;
        }
        else if("``".equals(n.pos))
        {
	   bIsInsideDoubleQuotes = true;
        }
        else if("''".equals(n.pos))
        {
	   bIsInsideDoubleQuotes = false;
        }
        else if(bIsInsideDoubleQuotes == true)
        {
	   What += n.lex;
           What += " ";
        }
     }
     return What;
  }
   
  public static String processActionTypeSay()
  {
     String OutFile = "say";
     OutFile += "\n";
     Node root = g.root;
     String Actor = "";
     String What = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj"))
        {
            Node ActorNode = e.target;
            Node ActualActorNode = null;
            if(!(ActorNode.gender == Gender.GENDER_FEMALE || ActorNode.gender == Gender.GENDER_MALE || ActorNode.isActorPronoun == true))
            {
		ActualActorNode = StanfordCoreNlpDemo.identifyTheActor(ActorNode);
            }
	    if(ActualActorNode != null)
		ActorNode = ActualActorNode;
            if(ActorNode.isActorPronoun)
		Actor = ActorNode.ActorNameInCasePronoun;
            else
	    	Actor = ActorNode.lex;
        }
     }

     What = processSayWhat();     

     OutFile += Actor;
     OutFile += " \n";
     OutFile += "\n"; //Actor2 is empty
     OutFile += What;
     OutFile += "\n";
     OutFile += "\n"; //Background is empty
     return OutFile;
  }

  public static String processActionTypeWrite()
  {
     String OutFile = "write";
     OutFile += "\n";
     Node root = g.root;
     String Actor = "";
     String What = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj"))
        {
            Node ActorNode = e.target;
            Node ActualActorNode = null;
            if(!(ActorNode.gender == Gender.GENDER_FEMALE || ActorNode.gender == Gender.GENDER_MALE || ActorNode.isActorPronoun == true))
            {
		ActualActorNode = StanfordCoreNlpDemo.identifyTheActor(ActorNode);
            }
	    if(ActualActorNode != null)
		ActorNode = ActualActorNode;
            if(ActorNode.isActorPronoun)
		Actor = ActorNode.ActorNameInCasePronoun;
            else
	    	Actor = ActorNode.lex;
        }
     }

     What = processSayWhat();     

     OutFile += Actor;
     OutFile += " \n";
     OutFile += "\n";//Actor2 is empty
     OutFile += What;
     OutFile += "\n";
     OutFile += "\n";//Background is empty
     return OutFile;
  }

  //public static Map<String, Runnable> processActionMap = new TreeMap<String, Runnable>(String.CASE_INSENSITIVE_ORDER);
  public static Map<String, Runnable> processActionMap = new HashMap<String, Runnable>();
  public static Graph g;
  public static String OutString;
  public static void init()
  {
	//processActionMap.put("walk", ProcessAction::processActionTypeWalk);
	processActionMap.put("walk", new Runnable() { public void run() { OutString = processActionTypeWalk(); } });
	processActionMap.put("walks", new Runnable() { public void run() { OutString = processActionTypeWalk(); } });
	processActionMap.put("walking", new Runnable() { public void run() { OutString = processActionTypeWalk(); } });
	processActionMap.put("go", new Runnable() { public void run() { OutString = processActionTypeWalk(); } });
	processActionMap.put("goes", new Runnable() { public void run() { OutString = processActionTypeWalk(); } });
	processActionMap.put("enter", new Runnable() { public void run() { OutString = processActionTypeWalk(); } });
	processActionMap.put("enters", new Runnable() { public void run() { OutString = processActionTypeWalk(); } });

	processActionMap.put("read", new Runnable() { public void run() { OutString = processActionTypeRead(); } });
	processActionMap.put("reads", new Runnable() { public void run() { OutString = processActionTypeRead(); } });
	processActionMap.put("reading", new Runnable() { public void run() { OutString = processActionTypeRead(); } });
	processActionMap.put("study", new Runnable() { public void run() { OutString = processActionTypeRead(); } });
	processActionMap.put("learn", new Runnable() { public void run() { OutString = processActionTypeRead(); } });

	processActionMap.put("write", new Runnable() { public void run() { OutString = processActionTypeWrite(); } });
	processActionMap.put("writes", new Runnable() { public void run() { OutString = processActionTypeWrite(); } });

	processActionMap.put("say", new Runnable() { public void run() { OutString = processActionTypeSay(); } });
	processActionMap.put("says", new Runnable() { public void run() { OutString = processActionTypeSay(); } });

	processActionMap.put("roll", new Runnable() { public void run() { OutString = processActionTypeRoll(); } });
	processActionMap.put("rolls", new Runnable() { public void run() { OutString = processActionTypeRoll(); } });
	
	processActionMap.put("start", new Runnable() { public void run() { OutString = processActionTypeStart(); } });
	processActionMap.put("starts", new Runnable() { public void run() { OutString = processActionTypeStart(); } });
  
	processActionMap.put("look", new Runnable() { public void run() { OutString = processActionTypeLook(); } });
	processActionMap.put("looks", new Runnable() { public void run() { OutString = processActionTypeLook(); } });
	processActionMap.put("face", new Runnable() { public void run() { OutString = processActionTypeLook(); } });
	processActionMap.put("faces", new Runnable() { public void run() { OutString = processActionTypeLook(); } });

	processActionMap.put("stand", new Runnable() { public void run() { OutString = processActionTypeStand(); } });
	processActionMap.put("stands", new Runnable() { public void run() { OutString = processActionTypeStand(); } });
	processActionMap.put("standing", new Runnable() { public void run() { OutString = processActionTypeStand(); } });
	
  }
 
  public static void ProcessActionLookUp(String ActionType)
  {
	OutString = "";
	Runnable r = processActionMap.get(ActionType.toLowerCase());
	if (r != null)
	{
	    r.run();
	}
	else
	{
	    System.out.println("Currently Action Type "+ActionType+" is not supported");
	}
  }
}

