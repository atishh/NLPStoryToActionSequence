
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
//More Specifically actor verb action.
public class ProcessAction
{

  public static String getActor(Node ActorNode)
  {
	String Actor = "";
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
	
	return Actor+" ";
  }
  
  public static String processActionTypeWalk()
  {
     String OutFile = "Action:walk";
     OutFile += "\n";
     Node root = RootNode;
     String Actor = "";
     String Where = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj") || e.label.equalsIgnoreCase("nn"))
        {
            Node ActorNode = e.target;
            Actor += getActor(ActorNode);
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
	else if(e.label.equalsIgnoreCase("prep_to"))
        {
            //This is currently approximate
	    Where = e.target.lex;
        }
	else if(e.label.equalsIgnoreCase("prep_into"))
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

  public static String processActionTypeSwim()
  {
     String OutFile = "Action:swim";
     OutFile += "\n";
     Node root = RootNode;
     String Actor = "";
     String Where = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj") || e.label.equalsIgnoreCase("nn"))
        {
            Node ActorNode = e.target;
            Actor += getActor(ActorNode);
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
	else if(e.label.equalsIgnoreCase("prep_to"))
        {
            //This is currently approximate
	    Where = e.target.lex;
        }
	else if(e.label.equalsIgnoreCase("prep_for"))
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


  public static String processActionTypeRun()
  {
     String OutFile = "Action:run";
     OutFile += "\n";
     Node root = RootNode;
     String Actor = "";
     String Where = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj") || e.label.equalsIgnoreCase("nn"))
        {
            Node ActorNode = e.target;
            Actor += getActor(ActorNode);
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
	else if(e.label.equalsIgnoreCase("prep_to"))
        {
            //This is currently approximate
	    Where = e.target.lex;
        }
	else if(e.label.equalsIgnoreCase("advmod"))
        {
     	    for (Edge e1 : e.target.outEdges)
     	    {
		if(e1.label.equalsIgnoreCase("prep_to"))
        	{
            	    //This is currently approximate
	    	    Where = e1.target.lex;
        	}
	    }
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
 
  public static String processActionTypeStop()
  {
     String OutFile = "Action:stop";
     OutFile += "\n";
     Node root = RootNode;
     String Actor = "";
     String Where = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj") || e.label.equalsIgnoreCase("nn"))
        {
            Node ActorNode = e.target;
            Actor += getActor(ActorNode);
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
	else if(e.label.equalsIgnoreCase("prep_to"))
        {
            //This is currently approximate
	    Where = e.target.lex;
        }
	else if(e.label.equalsIgnoreCase("advmod"))
        {
     	    for (Edge e1 : e.target.outEdges)
     	    {
		if(e1.label.equalsIgnoreCase("prep_to"))
        	{
            	    //This is currently approximate
	    	    Where = e1.target.lex;
        	}
	    }
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
     String OutFile = "Action:read";
     OutFile += "\n";
     Node root = RootNode;
     String Actor = "";
     String Where = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj") || e.label.equalsIgnoreCase("nn"))
        {
            Node ActorNode = e.target;
            Actor += getActor(ActorNode);
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
     String OutFile = "Action:stand";
     OutFile += "\n";
     Node root = RootNode;
     String Actor = "";
     String Where = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj") || e.label.equalsIgnoreCase("nn"))
        {
            Node ActorNode = e.target;
            Actor += getActor(ActorNode);
        }
	else if(e.label.equalsIgnoreCase("prep_near"))
        {
            //This is currently approximate
	    Where = e.target.lex;
        }
	else if(e.label.equalsIgnoreCase("prep_in"))
        {
            //This is currently approximate
	    Where = e.target.lex;
        }
	else if(e.label.equalsIgnoreCase("advmod"))
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

  public static String processActionTypeSit()
  {
     String OutFile = "Action:sit";
     OutFile += "\n";
     Node root = RootNode;
     String Actor = "";
     String Where = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj") || e.label.equalsIgnoreCase("nn"))
        {
            Node ActorNode = e.target;
            Actor += getActor(ActorNode);
        }
	else if(e.label.equalsIgnoreCase("prep_near"))
        {
            //This is currently approximate
	    Where = e.target.lex;
        }
	else if(e.label.equalsIgnoreCase("prep_in"))
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
     String OutFile = "Action:look";
     OutFile += "\n";
     Node root = RootNode;
     String Actor = "";
     String Where = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj") || e.label.equalsIgnoreCase("nn"))
        {
            Node ActorNode = e.target;
            Actor += getActor(ActorNode);
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
	else if(e.label.equalsIgnoreCase("ccomp") || e.label.equalsIgnoreCase("dobj"))
	{
	    //This means that there might be another action going on.
            AnotherActionNode = e.target;
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
     String OutFile = "Action:start";
     OutFile += "\n";
     Node root = RootNode;
     String Actor = "";
     String Where = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj") || e.label.equalsIgnoreCase("nn"))
        {
            Node ActorNode = e.target;
            Actor += getActor(ActorNode);
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
     String OutFile = "Action:roll";
     OutFile += "\n";
     Node root = RootNode;
     String Actor = "";
     String Where = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nn"))
        {
            Node ActorNode = e.target;
            Actor += getActor(ActorNode);
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
     String OutFile = "Action:say";
     OutFile += "\n";
     Node root = RootNode;
     String Actor = "";
     String What = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj") || e.label.equalsIgnoreCase("nn"))
        {
            Node ActorNode = e.target;
            Actor += getActor(ActorNode);
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
     String OutFile = "Action:write";
     OutFile += "\n";
     Node root = RootNode;
     String Actor = "";
     String What = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj") || e.label.equalsIgnoreCase("nn"))
        {
            Node ActorNode = e.target;
            Actor += getActor(ActorNode);
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

  public static String processActionTypeMake()
  {
     String OutFile = "Action:make";
     OutFile += "\n";
     Node root = RootNode;
     String Actor = "";
     String Where = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj") || e.label.equalsIgnoreCase("nn") || e.label.equalsIgnoreCase("nsubjpass"))
        {
            Node ActorNode = e.target;
            Actor += getActor(ActorNode);
        }
	else if(e.label.equalsIgnoreCase("prep_of"))
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
  
  public static String processActionTypeEat()
  {
     String OutFile = "Action:eat";
     OutFile += "\n";
     Node root = RootNode;
     String Actor = "";
     String Where = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj") || e.label.equalsIgnoreCase("nn"))
        {
            Node ActorNode = e.target;
            Actor += getActor(ActorNode);
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
  
  public static String processActionTypeDrink()
  {
     String OutFile = "Action:drink";
     OutFile += "\n";
     Node root = RootNode;
     String Actor = "";
     String Where = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj") || e.label.equalsIgnoreCase("nn"))
        {
            Node ActorNode = e.target;
            Actor += getActor(ActorNode);
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

  public static String processActionTypeJoin()
  {
     String OutFile = "Action:join";
     OutFile += "\n";
     Node root = RootNode;
     String Actor = "";
     String Where = "";
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj") || e.label.equalsIgnoreCase("nn"))
        {
            Node ActorNode = e.target;
            Actor += getActor(ActorNode);
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

 
  //public static Map<String, Runnable> processActionMap = new TreeMap<String, Runnable>(String.CASE_INSENSITIVE_ORDER);
  public static Map<String, Runnable> processActionMap = new HashMap<String, Runnable>();
  public static Graph g;
  public static String OutString;
  public static boolean bActionNotSupported;
  public static Node AnotherActionNode;
  public static Node RootNode;
  public static void init()
  {
	//processActionMap.put("walk", ProcessAction::processActionTypeWalk);
	processActionMap.put("walk", new Runnable() { public void run() { OutString += processActionTypeWalk(); } });
	processActionMap.put("walks", new Runnable() { public void run() { OutString += processActionTypeWalk(); } });
	processActionMap.put("walking", new Runnable() { public void run() { OutString += processActionTypeWalk(); } });
	processActionMap.put("go", new Runnable() { public void run() { OutString += processActionTypeWalk(); } });
	processActionMap.put("goes", new Runnable() { public void run() { OutString += processActionTypeWalk(); } });
	processActionMap.put("enter", new Runnable() { public void run() { OutString += processActionTypeWalk(); } });
	processActionMap.put("enters", new Runnable() { public void run() { OutString += processActionTypeWalk(); } });
	processActionMap.put("come", new Runnable() { public void run() { OutString += processActionTypeWalk(); } });
	processActionMap.put("went", new Runnable() { public void run() { OutString += processActionTypeWalk(); } });

	processActionMap.put("ran", new Runnable() { public void run() { OutString += processActionTypeRun(); } });

	processActionMap.put("stop", new Runnable() { public void run() { OutString += processActionTypeStop(); } });
	processActionMap.put("stopped", new Runnable() { public void run() { OutString += processActionTypeStop(); } });


	processActionMap.put("read", new Runnable() { public void run() { OutString += processActionTypeRead(); } });
	processActionMap.put("reads", new Runnable() { public void run() { OutString += processActionTypeRead(); } });
	processActionMap.put("reading", new Runnable() { public void run() { OutString += processActionTypeRead(); } });
	processActionMap.put("study", new Runnable() { public void run() { OutString += processActionTypeRead(); } });
	processActionMap.put("learn", new Runnable() { public void run() { OutString += processActionTypeRead(); } });

	processActionMap.put("write", new Runnable() { public void run() { OutString += processActionTypeWrite(); } });
	processActionMap.put("writes", new Runnable() { public void run() { OutString += processActionTypeWrite(); } });

	processActionMap.put("say", new Runnable() { public void run() { OutString += processActionTypeSay(); } });
	processActionMap.put("says", new Runnable() { public void run() { OutString += processActionTypeSay(); } });
	processActionMap.put("shouted", new Runnable() { public void run() { OutString += processActionTypeSay(); } });
	processActionMap.put("replied", new Runnable() { public void run() { OutString += processActionTypeSay(); } });

	processActionMap.put("roll", new Runnable() { public void run() { OutString += processActionTypeRoll(); } });
	processActionMap.put("rolls", new Runnable() { public void run() { OutString += processActionTypeRoll(); } });
	
	processActionMap.put("start", new Runnable() { public void run() { OutString += processActionTypeStart(); } });
	processActionMap.put("starts", new Runnable() { public void run() { OutString += processActionTypeStart(); } });
	processActionMap.put("started", new Runnable() { public void run() { OutString += processActionTypeStart(); } });
  
	processActionMap.put("look", new Runnable() { public void run() { OutString += processActionTypeLook(); } });
	processActionMap.put("looks", new Runnable() { public void run() { OutString += processActionTypeLook(); } });
	processActionMap.put("looked", new Runnable() { public void run() { OutString += processActionTypeLook(); } });
	processActionMap.put("face", new Runnable() { public void run() { OutString += processActionTypeLook(); } });
	processActionMap.put("faces", new Runnable() { public void run() { OutString += processActionTypeLook(); } });
	processActionMap.put("see", new Runnable() { public void run() { OutString += processActionTypeLook(); } });
	processActionMap.put("saw", new Runnable() { public void run() { OutString += processActionTypeLook(); } });
	processActionMap.put("gaze", new Runnable() { public void run() { OutString += processActionTypeLook(); } });
	processActionMap.put("gazed", new Runnable() { public void run() { OutString += processActionTypeLook(); } });

	processActionMap.put("stand", new Runnable() { public void run() { OutString += processActionTypeStand(); } });
	processActionMap.put("stood", new Runnable() { public void run() { OutString += processActionTypeStand(); } });
	processActionMap.put("stands", new Runnable() { public void run() { OutString += processActionTypeStand(); } });
	processActionMap.put("standing", new Runnable() { public void run() { OutString += processActionTypeStand(); } });

	processActionMap.put("sit", new Runnable() { public void run() { OutString += processActionTypeSit(); } });
	processActionMap.put("sits", new Runnable() { public void run() { OutString += processActionTypeSit(); } });

	processActionMap.put("make", new Runnable() { public void run() { OutString += processActionTypeMake(); } });
	processActionMap.put("made", new Runnable() { public void run() { OutString += processActionTypeMake(); } });

	processActionMap.put("swim", new Runnable() { public void run() { OutString += processActionTypeSwim(); } });
	processActionMap.put("swam", new Runnable() { public void run() { OutString += processActionTypeSwim(); } });
	processActionMap.put("dive", new Runnable() { public void run() { OutString += processActionTypeSwim(); } });

	processActionMap.put("eat", new Runnable() { public void run() { OutString += processActionTypeEat(); } });
	processActionMap.put("eats", new Runnable() { public void run() { OutString += processActionTypeEat(); } });
	processActionMap.put("scoop", new Runnable() { public void run() { OutString += processActionTypeEat(); } });

	processActionMap.put("drink", new Runnable() { public void run() { OutString += processActionTypeDrink(); } });
	processActionMap.put("drinks", new Runnable() { public void run() { OutString += processActionTypeDrink(); } });

	processActionMap.put("join", new Runnable() { public void run() { OutString += processActionTypeJoin(); } });
	processActionMap.put("joined", new Runnable() { public void run() { OutString += processActionTypeJoin(); } });

  }
 
  public static void ProcessActionWrapper(String ActionType)
  {
	RootNode = g.root;
	OutString = "";
        bActionNotSupported = false;
        AnotherActionNode = null;
        
	Runnable r = processActionMap.get(ActionType.toLowerCase());
	if (r != null)
	{
	    r.run();
	}
	else
	{
            bActionNotSupported = true;
	    System.out.println("Currently Action Type "+ActionType+" is not supported");
	}

	if(AnotherActionNode != null)
	{
		RootNode = AnotherActionNode;
		r = processActionMap.get(RootNode.lex.toLowerCase());
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
}

