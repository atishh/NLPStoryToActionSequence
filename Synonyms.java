
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


public class Synonyms{

  static boolean IsChildrenKeyword(Node n) {
     if(n.lex.equalsIgnoreCase("children"))
     	return true;
     if(n.lex.equalsIgnoreCase("child"))
     	return true;
     return false;
  }
 
  static boolean IsParentKeyword(Node n) {
     if(n.lex.equalsIgnoreCase("parent"))
	return true;
     if(n.lex.equalsIgnoreCase("parents"))
	return true;
     return false;
  }

  static boolean IsFemaleKeyword(Node n) {
     if(n.lex.equalsIgnoreCase("woman"))
     	return true;
     if(n.lex.equalsIgnoreCase("lady"))
     	return true;
     if(n.lex.equalsIgnoreCase("girl"))
     	return true;
     if(n.lex.equalsIgnoreCase("actress"))
     	return true;
     return false;
  } 

  static boolean IsMaleKeyword(Node n) {
     if(n.lex.equalsIgnoreCase("man"))
     	return true;
     if(n.lex.equalsIgnoreCase("gentleman"))
     	return true;
     if(n.lex.equalsIgnoreCase("boy"))
     	return true;
     if(n.lex.equalsIgnoreCase("actor"))
     	return true;
     return false;
  } 


  static boolean IsFemalePronoun(Node n) {
     if(n.lex.equalsIgnoreCase("she"))
     	return true;
     if(n.lex.equalsIgnoreCase("her"))
     	return true;
     if(n.lex.equalsIgnoreCase("hers"))
     	return true;
     if(n.lex.equalsIgnoreCase("herself"))
     	return true;
     return false;
  }

  static boolean IsMalePronoun(Node n) {
     if(n.lex.equalsIgnoreCase("he"))
     	return true;
     if(n.lex.equalsIgnoreCase("his"))
     	return true;
     if(n.lex.equalsIgnoreCase("him"))
     	return true;
     if(n.lex.equalsIgnoreCase("himself"))
     	return true;
     return false;
  }

  static boolean IsAmbigiousPronoun(Node n) {
     if(n.lex.equalsIgnoreCase("they"))
     	return true;
     if(n.lex.equalsIgnoreCase("their"))
     	return true;
     return false;
  }
  

  static boolean isActionTypeStand(String action)
  {
       if(action.equalsIgnoreCase("stand"))
          return true;
       if(action.equalsIgnoreCase("stands"))
          return true;
       if(action.equalsIgnoreCase("standing"))
          return true;
       return false;
  }  
  static boolean isActionTypeLook(String action)
  {
       if(action.equalsIgnoreCase("look"))
          return true;
       if(action.equalsIgnoreCase("looks"))
          return true;
       if(action.equalsIgnoreCase("face"))
          return true;
       if(action.equalsIgnoreCase("faces"))
          return true;
       return false;
  } 

  static boolean isActionTypeStart(String action)
  {
       if(action.equalsIgnoreCase("start"))
          return true;
       if(action.equalsIgnoreCase("starts"))
          return true;
       return false;
  } 

  static boolean isActionTypeRoll(String action)
  {
       if(action.equalsIgnoreCase("roll"))
          return true;
       if(action.equalsIgnoreCase("rolls"))
          return true;
       return false;
  } 

  static boolean isActionTypeSay(String action)
  {
       if(action.equalsIgnoreCase("say"))
          return true;
       if(action.equalsIgnoreCase("says"))
          return true;
       return false;
  }
  
  static boolean isActionTypeWrite(String action)
  {
       if(action.equalsIgnoreCase("write"))
          return true;
       if(action.equalsIgnoreCase("writes"))
          return true;
       return false;
  } 
  
  static boolean isActionTypeWalk(String action)
  {
       if(action.equalsIgnoreCase("walk"))
          return true;
       if(action.equalsIgnoreCase("walks"))
          return true;
       if(action.equalsIgnoreCase("walking"))
          return true;
       if(action.equalsIgnoreCase("enter"))
          return true;
       if(action.equalsIgnoreCase("enters"))
          return true;
       if(action.equalsIgnoreCase("go"))
          return true;
       if(action.equalsIgnoreCase("goes"))
          return true;
       return false;
  } 
 
  static boolean isActionTypeRead(String action)
  {
       if(action.equalsIgnoreCase("read"))
          return true;
       if(action.equalsIgnoreCase("reads"))
          return true;
       if(action.equalsIgnoreCase("reading"))
          return true;
       if(action.equalsIgnoreCase("study"))
          return true;
       if(action.equalsIgnoreCase("learn"))
          return true;
       return false;
  } 


}


