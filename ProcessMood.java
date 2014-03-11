import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.Map;
import java.util.*;

public class ProcessMood
{

  public static String processMoodTypeHungry()
  {
     String OutFile = "Mood: ";
     OutFile += "hungry ";
     Node root = g.root;
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj") || e.label.equalsIgnoreCase("nn") || e.label.equalsIgnoreCase("nsubjpass"))
        {
            Node ActorNode = e.target;
            OutFile += ProcessAction.getActor(ActorNode);
	    OutFile += " ";
        }	
     }
     OutFile += " \n";
     return OutFile;
  }

  public static String processMoodTypeExcited()
  {
     String OutFile = "Mood: ";
     OutFile += "excited ";
     Node root = g.root;
     for (Edge e : root.outEdges)
     {
	if(e.label.equalsIgnoreCase("nsubj") || e.label.equalsIgnoreCase("nn") || e.label.equalsIgnoreCase("nsubjpass"))
        {
            Node ActorNode = e.target;
            OutFile += ProcessAction.getActor(ActorNode);
	    OutFile += " ";
        }	
     }
     OutFile += " \n";
     return OutFile;
  }


  public static Map<String, Runnable> processMoodMap = new HashMap<String, Runnable>();
  public static Graph g;
  public static String OutString;
  public static boolean bMoodNotSupported;
 
  public static void init()
  {
	//processMoodMap.put("walk", ProcessAction::processActionTypeWalk);
	processMoodMap.put("hungry", new Runnable() { public void run() { OutString = processMoodTypeHungry(); } });
	
	processMoodMap.put("excited", new Runnable() { public void run() { OutString = processMoodTypeExcited(); } });
	processMoodMap.put("happy", new Runnable() { public void run() { OutString = processMoodTypeExcited(); } });

  }
 
  public static void ProcessMoodLookUp(String MoodType)
  {
	OutString = "";
        bMoodNotSupported = false;
	Runnable r = processMoodMap.get(MoodType.toLowerCase());
	if (r != null)
	{
	    r.run();
	}
	else
	{
            bMoodNotSupported = true;
	    System.out.println("Currently Mood Type "+MoodType+" is not supported");
	}
  }

}
