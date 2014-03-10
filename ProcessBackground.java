import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.Map;
import java.util.*;

public class ProcessBackground
{

  public static String processBackgroundTypeCamp()
  {
     String OutFile = "Background: ";
     OutFile += "camp ";
     OutFile += " \n";
     return OutFile;
  }

  public static String processBackgroundTypeLiving()
  {
     String OutFile = "Background: ";
     OutFile += "live ";
     Node root = g.root;
     for (Edge e : root.outEdges)
     {
	if((e.label.equalsIgnoreCase("prep_in")) || (e.label.equalsIgnoreCase("prep_by")))
        {
            Node BackgroundNode = e.target;
	    OutFile += BackgroundNode.lex;
	    OutFile += " ";
        }
     }
     OutFile += " \n";
     return OutFile;
  }

  public static String processBackgroundTypeTiny()
  {
     String OutFile = "Background: ";
     OutFile += "tiny ";
     Node root = g.root;
     for (Edge e : root.outEdges)
     {
	if((e.label.equalsIgnoreCase("nsubj")))
        {
            Node BackgroundNode = e.target;
	    OutFile += BackgroundNode.lex;
	    OutFile += " ";
        }
     }
     OutFile += " \n";
     return OutFile;
  }

  public static String processBackgroundTypeComfort()
  {
     String OutFile = "Background: ";
     OutFile += "comfort ";
     Node root = g.root;
     for (Edge e : root.outEdges)
     {
	if((e.label.equalsIgnoreCase("nsubj")))
        {
            Node BackgroundNode = e.target;
	    OutFile += BackgroundNode.lex;
	    OutFile += " ";
        }
     }
     OutFile += " \n";
     return OutFile;
  }


  public static Map<String, Runnable> processBackgroundMap = new HashMap<String, Runnable>();
  public static Graph g;
  public static String OutString;
  public static boolean bBackgroundNotSupported;
 
  public static void init()
  {
	//processBackgroundMap.put("walk", ProcessAction::processActionTypeWalk);
	processBackgroundMap.put("camp", new Runnable() { public void run() { OutString = processBackgroundTypeCamp(); } });
	processBackgroundMap.put("camping", new Runnable() { public void run() { OutString = processBackgroundTypeCamp(); } });

	processBackgroundMap.put("living", new Runnable() { public void run() { OutString = processBackgroundTypeLiving(); } });
	processBackgroundMap.put("live", new Runnable() { public void run() { OutString = processBackgroundTypeLiving(); } });

	processBackgroundMap.put("tiny", new Runnable() { public void run() { OutString = processBackgroundTypeTiny(); } });
	processBackgroundMap.put("comfortable", new Runnable() { public void run() { OutString = processBackgroundTypeComfort(); } });
  }
 
  public static void ProcessBackgroundLookUp(String BackgroundType)
  {
	OutString = "";
        bBackgroundNotSupported = false;
	Runnable r = processBackgroundMap.get(BackgroundType.toLowerCase());
	if (r != null)
	{
	    r.run();
	}
	else
	{
            bBackgroundNotSupported = true;
	    System.out.println("Currently Background Type "+BackgroundType+" is not supported");
	}
  }

}
