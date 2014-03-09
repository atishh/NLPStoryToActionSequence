//This is the main file
import java.io.*;
import java.util.*;

import edu.stanford.nlp.io.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.trees.*;
//import edu.stanford.nlp.semgraph.SemanticGraph; //to enable latter 
import edu.stanford.nlp.trees.semgraph.SemanticGraph;
import edu.stanford.nlp.util.*;
//import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation; //to enable latter
import edu.stanford.nlp.trees.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.dcoref.CorefChain.CorefMention;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
//import com.chaoticity.dependensee.*;


public class StanfordCoreNlpDemo {
  
  static public List<String> PotentialBackground;

  static String CollectAttributes(Node ActorNode)
  {
     String Attribute = "";
     //Currently we are not processing edges
     for (Edge e : ActorNode.outEdges)
     {
	if(e.label.equalsIgnoreCase("amod") || e.label.equalsIgnoreCase("rcmod"))
        {
            Node AttributeNode = e.target;
	    Attribute += AttributeNode.lex;
	    Attribute += " ";
            System.out.println("Attribute = "+Attribute);
        }
	if(e.label.equalsIgnoreCase("nsubj"))
	{
            Node AttributeNode = e.target;
	    if(IsNodeChildren(AttributeNode))
	    {
		ActorNode.mActorAttribute.bIsChildren = true;
                System.out.println("Node "+ActorNode.lex+" is a children");
	    }
	}
     }
     System.out.println("Attribute return = "+Attribute);
     return Attribute;
  }  

  static boolean IsNodeChildren(Node n) {
     if(n.lex.equalsIgnoreCase("children"))
     	return true;
     return false;
  }


  //Currently we are not processing edge, we will do that latter
  static boolean processGender(Node n, Gender gender, String attribute)
  {
       System.out.println("Inside processGender +1 Attribute = "+attribute);
       boolean IsGender = false;
       for (Node node : n.children) {
           if(node.ner.equalsIgnoreCase("PERSON"))
           {
               if(node.gender == Gender.GENDER_OTHER)
               {
		    node.gender = gender;
	            node.attribute += " ";
	            node.attribute += attribute;
                    IsGender = true;
                    System.out.println("Inside processGender Attribute = "+attribute);
	       }
	       else
	       {
	            node.attribute += " ";
	            node.attribute += attribute;
	       }
           }
	   else
           {
		boolean IsGenderInternal = processGender(node, gender, attribute);
		IsGender = IsGender | IsGenderInternal;
           }
       }
       return IsGender;
  }

  //Currently the assumption is that a action is done by only one actor which is a very bad assumptions.
  static Node identifyTheActor(Node ActorNode)
  {
     if(ActorNode.gender == Gender.GENDER_FEMALE || ActorNode.gender == Gender.GENDER_MALE || ActorNode.isActorPronoun == true)
     {
         return ActorNode;
     }
     //Currently we are not processing edges
     for (Node node : ActorNode.children) {
           if(node.gender == Gender.GENDER_FEMALE || node.gender == Gender.GENDER_MALE || node.isActorPronoun == true)
           {
		return node;
           }
	   else
           {
		Node n = identifyTheActor(node);
		if(n != null)
		    return n;
           }
      }

      //If we still not able to determine the actors.Then process RepresentativeMentionList in case it is pronoun,
      //like they their
      if(Synonyms.IsAmbigiousPronoun(ActorNode))
      {
	    Node returnNode = null;
	    //Return the last node
            for (Node n : ActorNode.RepresentativeMentionList) {
		returnNode = n;
            }
	    if(returnNode != null)
		return returnNode;
      }

      return null;
  }  


 
  public static void main(String[] args) throws IOException, Exception {

    PotentialBackground = new ArrayList<String>();
    PrintWriter out;
    if (args.length > 1) {
      out = new PrintWriter(args[1]);
    } else {
      out = new PrintWriter(System.out);
    }
    PrintWriter xmlOut = null;
    if (args.length > 2) {
      xmlOut = new PrintWriter(args[2]);
    }
    Properties props = new Properties();
    props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    Annotation annotation;
    if (args.length > 0) {
      annotation = new Annotation(IOUtils.slurpFileNoExceptions(args[0]));
    } else {
      annotation = new Annotation("Kosgi Santosh sent an email to Stanford University. He didn't get a reply.");
    }

    GenderAnnotator myGenderAnnotation = new GenderAnnotator();
    pipeline.addAnnotator(myGenderAnnotation);
    pipeline.annotate(annotation);

    out.println("Starting Gender Annotator\n");

    //myGenderAnnotation.annotate(annotation);

    out.println("Ending Gender Annotator\n");
    
    System.out.println("Petty Printing starts\n");
    pipeline.prettyPrint(annotation, out);
    System.out.println("Petty Printing ends\n");
    if (xmlOut != null) {
      pipeline.xmlPrint(annotation, xmlOut);
    }
    // An Annotation is a Map and you can get and use the various analyses individually.
    // For instance, this gets the parse tree of the first sentence in the text.
    List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
    if (sentences != null && sentences.size() > 0) {
      CoreMap sentence = sentences.get(0);
      Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
      out.println();
      out.println("The first sentence parsed is:");
      tree.pennPrint(out);
    }


    // these are all the sentences in this document
    // a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
    //List<CoreMap> sentences = annotation.get(SentencesAnnotation.class);

    TreeMap<Integer, Graph> graphs;
    graphs = new TreeMap<Integer, Graph>();
    
         
    int counter = 0;

    for(CoreMap sentence: sentences) {
      // traversing the words in the current sentence
      // a CoreLabel is a CoreMap with additional token-specific methods
      for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
        // this is the text of the token
        String word = token.get(CoreAnnotations.TextAnnotation.class);
        // this is the POS tag of the token
        String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
        // this is the NER label of the token
        String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);       
    	//System.out.println("Printing word starts\n");
        //System.out.println(word);
    	//System.out.println("Printing word ends\n");
    	//System.out.println("Printing pos starts\n");
        //System.out.println(pos);
    	//System.out.println("Printing pos ends\n");
    	//System.out.println("Printing ne starts\n");
        //System.out.println(ne);
    	//System.out.println("Printing ne ends\n");
      }

      // this is the parse tree of the current sentence
      Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
      tree.pennPrint(out);
      System.out.println("atish\n");
      System.out.println(tree.toString());
      System.out.println("kumar dependency starts\n");
        TreebankLanguagePack tlp = new PennTreebankLanguagePack();
	GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
	GrammaticalStructure gs = gsf.newGrammaticalStructure(tree);
        Collection tdl = gs.typedDependenciesCollapsed();
        System.out.println(tdl);
        counter++;
        //String image = "image";
        //image +=counter;
        //image +=".png";
     	SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
        IndexedWord root = dependencies.getFirstRoot();
        //Graph g = Main.getGraph(sentence, tree, tdl);
        Graph g = Main.getGraph(sentence, tree, dependencies.typedDependencies());
        System.out.println("root is " + root.word());
        g.setRoot(root.word());
        graphs.put(counter-1, g);

        //Main.writeImage(g,image,3);
        System.out.println("kumar dependency ends\n");

      final StringBuilder sb = new StringBuilder();
      for ( final Tree t : tree.getLeaves() ) {
     	sb.append(t.toString()).append(" ");
      }
      System.out.println(sb);
      // this is the Stanford dependency graph of the current sentence
     System.out.println("CollapsedCCProcessedDependenciesAnnotation prettyprint starts\n");
     dependencies.prettyPrint();
     System.out.println("CollapsedCCProcessedDependenciesAnnotation prettyprint ends\n");

     System.out.printf("ROOT(root-0, %s-%d)%n", root.word(), root.index());
     System.out.println("CollapsedCCProcessedDependenciesAnnotation toString starts\n");
     System.out.println(dependencies.toString("readable"));
     System.out.println("CollapsedCCProcessedDependenciesAnnotation toString ends\n");
     System.out.println(dependencies.toList());
    }
    
    

    
   
    // This is the coreference link graph
    // Each chain stores a set of mentions that link to each other,
    // along with a method for getting the most representative mention
    // Both sentence and token offsets start at 1!
    Map<Integer, CorefChain> graph = 
      annotation.get(CorefChainAnnotation.class);
    //System.out.println(graph.toString("readable"));

	Set s=graph.entrySet();

        //Move next key and value of Map by iterator
        Iterator it=s.iterator();

        while(it.hasNext())
        {
            // key=value separator this by Map.Entry to get key and value
            Map.Entry m =(Map.Entry)it.next();

            // getKey is used to get key of Map
            int key=(Integer)m.getKey();

            // getValue is used to get value of key in Map
            CorefChain value=(CorefChain)m.getValue();

            System.out.println("Key :"+key+" Value "+value.toString());
        }


        /*
        //Convert paragrah into sentences
        String paragraph = "My first sentence. My second sentence.";
        Reader reader = new StringReader(paragraph);
        DocumentPreprocessor dp = new DocumentPreprocessor(reader);
        
        List<String> sentenceList = new LinkedList<String>();
        Iterator<List<HasWord>> it2 = dp.iterator();
        while (it2.hasNext()) {
           StringBuilder sentenceSb = new StringBuilder();
           List<HasWord> sentence = it2.next();
           for (HasWord token : sentence) {
              if(sentenceSb.length()>1) {
                 sentenceSb.append(" ");
              }
              sentenceSb.append(token);
           }
           sentenceList.add(sentenceSb.toString());
        }
        
        for(String sentence:sentenceList) {
           System.out.println(sentence);
        }
        */

       for(Map.Entry<Integer, CorefChain> entry : graph.entrySet()) {
            CorefChain c = entry.getValue();

            //this is because it prints out a lot of self references which aren't that useful
            if(c.getMentionsInTextualOrder().size() <= 1)
                continue;

            //sentNum = sentenceNo.
            CorefMention cm = c.getRepresentativeMention();
            String clust = "";
            List<CoreLabel> tks = annotation.get(SentencesAnnotation.class).get(cm.sentNum-1).get(TokensAnnotation.class);
            Graph g = graphs.get(cm.sentNum-1);
            List<Node> RepresentativeMentionList;
            RepresentativeMentionList = new ArrayList<Node>();
            Node n;
            for(int i = cm.startIndex-1; i < cm.endIndex-1; i++)
            {   
                n = g.nodes.get(i);
                RepresentativeMentionList.add(n);
                System.out.println("Nodes added in representativeMentionList are " + n.toString());
                clust += tks.get(i).get(TextAnnotation.class) + " ";
	    }
            clust = clust.trim();
            System.out.println("representative mention: \"" + clust + "\" is mentioned by:");

            List<Node> MentionList;
            MentionList = new ArrayList<Node>();

            //for(CorefMention m : c.getCorefMentions()){
            for(CorefMention m : c.getMentionsInTextualOrder()){
                String clust2 = "";
                tks = annotation.get(SentencesAnnotation.class).get(m.sentNum-1).get(TokensAnnotation.class);
                g = graphs.get(m.sentNum-1);
            	List<Node> nodes;
            	nodes = new ArrayList<Node>();

                for(int i = m.startIndex-1; i < m.endIndex-1; i++)
                {
                    n = g.nodes.get(i);
                    nodes.add(n);
                    System.out.println("Nodes having representative mentions are " + n.toString());
                    //n.RepresentativeMentionList = RepresentativeMentionList;
                    clust2 += tks.get(i).get(TextAnnotation.class) + " ";
		}
                clust2 = clust2.trim();
                //don't need the self mention
                if(clust.equals(clust2))
                    continue;
		
            	for (Node no : nodes) {
                    no.RepresentativeMentionList = RepresentativeMentionList;
                    MentionList.add(no);
            	}
		
                System.out.println("\t" + clust2);
            }
           for (Node no : RepresentativeMentionList) {
               no.MentionList = MentionList;
           }
	    
        }
    
    //populate attribute of all the nodes
    for (Integer i : graphs.keySet()) {
    	Graph g = graphs.get(i);
        for (Integer j : g.nodes.keySet()) 
        {
              Node n = g.nodes.get(j);
	      n.mActorAttribute.populateActorAttribute(n);
	}
    }    

    //gender calculation (based on representative and mention list)
    counter = 0;
    for (Integer i : graphs.keySet()) {
        counter++;
    	Graph g = graphs.get(i);
        for (Integer j : g.nodes.keySet()) 
        {
              Node n = g.nodes.get(j);
              for (Node no : n.MentionList) {
                   if(Synonyms.IsFemalePronoun(no))
                   {
                   	String attribute = CollectAttributes(no);
			n.gender = Gender.GENDER_FEMALE;
                        n.attribute += " ";
                        n.attribute += attribute;
                   }
                   else if(Synonyms.IsMalePronoun(no))
                   {
                   	String attribute = CollectAttributes(no);
			n.gender = Gender.GENDER_MALE;
                        n.attribute += " ";
                        n.attribute += attribute;
                   }
              }
        }
    }

    //gender calculation (based on keyword and named entity recognition)
    for (Integer i : graphs.keySet()) {
    	Graph g = graphs.get(i);
        for (Integer j : g.nodes.keySet()) 
        {
              Node n = g.nodes.get(j);
              if(Synonyms.IsFemaleKeyword(n))
              {
                   String attribute = CollectAttributes(n);
	           boolean bIsGender = processGender(n, Gender.GENDER_FEMALE, attribute);
              }
              else if(Synonyms.IsMaleKeyword(n))
              {
                   String attribute = CollectAttributes(n);
	           boolean bIsGender = processGender(n, Gender.GENDER_MALE, attribute);
              }
        }
    }
    
    //by default make all the children, if no gender is specified as female
    //TODO we can check corpus to find a particular name can be male or female
    //be default make parent as male
    for (Integer i : graphs.keySet()) {
    	Graph g = graphs.get(i);
        for (Integer j : g.nodes.keySet()) 
        {
              Node n = g.nodes.get(j);
	      if(n.mActorAttribute.bIsChildren == true)
	      {
		if(n.gender == Gender.GENDER_OTHER)
			n.gender = Gender.GENDER_FEMALE;
	      }
	      if(n.mActorAttribute.bIsParent == true)
	      {
		if(n.gender == Gender.GENDER_OTHER)
			n.gender = Gender.GENDER_MALE;
	      }
	}
    }    

    //Calculate no. of Actors and their attributes. Actors are always dynamic object.
    Hashtable ActorsFemale = new Hashtable();
    Hashtable ActorsMale = new Hashtable();
    for (Integer i : graphs.keySet()) {
        Graph g = graphs.get(i);
        String genderName = "";
        boolean isPreviousGenderMale = false;
        boolean isPreviousGenderFemale = false;
        String attribute = "";
        for (Integer j : g.nodes.keySet()) 
        {
              Node n = g.nodes.get(j);
              if(n.gender == Gender.GENDER_FEMALE)
	      {
                isPreviousGenderFemale = true;
                genderName += n.lex;
                genderName += " ";
                String attributeForThisNode = CollectAttributes(n);
		attribute += n.attribute;
		attribute += " ";
		attribute += attributeForThisNode;
		attribute += " ";
		attribute += n.mActorAttribute.getAttributeString();
		attribute += " ";
              }
              else if(n.gender == Gender.GENDER_MALE)
	      {
                isPreviousGenderMale = true;
                genderName += n.lex;
                genderName += " ";
                String attributeForThisNode = CollectAttributes(n);
		attribute += n.attribute;
		attribute += " ";
		attribute += attributeForThisNode;
		attribute += " ";
	      }
	      else
	      {
                if(isPreviousGenderFemale == true)
	        {
		   ActorsFemale.put(genderName, attribute);
	        }
                else if(isPreviousGenderMale == true)
		{
		   ActorsMale.put(genderName, attribute);
		}
		isPreviousGenderFemale = false;
		isPreviousGenderMale = false;
                genderName = "";
		attribute = "";
 	      }
        }
    }       
   

    //populate ActorNameInCasePronoun
    for (Integer j : graphs.keySet()) {
        Graph g = graphs.get(j);
        for (Integer i : g.nodes.keySet()) {
            Node node = g.nodes.get(i);
            String actor = "";
            for (Node n : node.RepresentativeMentionList) {
                actor += n.toString();
                actor += " ";   
            }
            if(ActorsFemale.containsKey(actor))
	    {
		node.isActorPronoun = true;
                node.ActorNameInCasePronoun = actor;
            }
            else if(ActorsMale.containsKey(actor))
	    {
		node.isActorPronoun = true;
                node.ActorNameInCasePronoun = actor;
            }
        }
    }


    String OutString = ""; //This string will be used to write to a file, which will be 
                      //read by C++;    
    String BackGround = "";

    OutString += ActorsFemale.size();
    OutString += "\n";

    Set hashtableFemaleKeys = ActorsFemale.keySet();
    Iterator itFemale=hashtableFemaleKeys.iterator();

    System.out.println("The names of females are ");
    while(itFemale.hasNext())
    {
        String FemaleKey = (String)itFemale.next();
        System.out.println(FemaleKey);
	OutString += FemaleKey;
	OutString += "\n";
        String attribute = (String)ActorsFemale.get(FemaleKey);
	OutString += attribute;
	OutString += "\n";
    }
    //OutString += "\n";

    OutString += ActorsMale.size();
    OutString += "\n";

    Set hashtableMaleKeys = ActorsMale.keySet();
    Iterator itMale=hashtableMaleKeys.iterator();

    System.out.println("The names of males are ");
    while(itMale.hasNext())
    {
        String MaleKey = (String)itMale.next();
        System.out.println(MaleKey);
	OutString += MaleKey;
	OutString += "\n";
        String attribute = (String)ActorsMale.get(MaleKey);
	OutString += attribute;
	OutString += "\n";
    }
    //OutString += "\n";


    counter = 0;
    int nNoOfActions = 0;
    ProcessAction.init();
    ProcessBackground.init();
    for (Integer i : graphs.keySet()) {
        counter++;
    	Graph g = graphs.get(i);
        ProcessAction.g = g;
        ProcessAction.ProcessActionLookUp(g.root.lex);
        if(ProcessAction.bActionNotSupported == false)
	{
	     OutString += ProcessAction.OutString;
	     nNoOfActions++;
	}
        else
	{
	     //verb might be to change background or mood of an actor
             ProcessBackground.g = g;
             ProcessBackground.ProcessBackgroundLookUp(g.root.lex);
             if(ProcessBackground.bBackgroundNotSupported == false)
	     {
	     	OutString += ProcessBackground.OutString;
	     }
	     else
	     {
		//might be change of mood swing of actor TODO
	     }
	}
        //OutString += "\n";
    }

    /*
    for (String s : PotentialBackground)
    {
       
    }
    */

    System.out.println("Content of Outfile is" + OutString);
    //Writing OutString to a file which will be eventually read by C++;
    PrintWriter outFile = new PrintWriter("outfile.txt");
    outFile.println(OutString);    
    outFile.close();
    String OutString2 = "";
    OutString2 += nNoOfActions;
    outFile = new PrintWriter("noofaction.txt");
    outFile.println(OutString2);    
    outFile.close();    

    //Printing image file
    counter = 0;
    for (Integer i : graphs.keySet()) {
        counter++;
     	System.out.println("printing image of sentence no " + counter);
    	Graph g = graphs.get(i);
     	System.out.println("printing root " + g.root.lex);
        String image = "image";
        image +=counter;
        image +=".png";
        Main.writeImage(g,image,3);
    }
         
/*
    for (Integer i : graphs.keySet()) {
	Graph g = graphs.get(i);
        Main.printGraph(g);
    }
*/

/*
        String sentence="My dog also likes eating sausage.";
        LexicalizedParser lp = new LexicalizedParser("resources/stanford-parser-2011-06-27/grammar/englishPCFG.ser.gz");
        TokenizerFactory tf = PTBTokenizer.factory(false, new WordTokenFactory());
        TreePrint tp = new TreePrint("penn,typedDependenciesCollapsed");
        
        List tokens = tf.getTokenizer(new StringReader(sentence)).tokenize();
        lp.parse(tokens); // parse the tokens
        Tree t = lp.getBestParse();
        
        TreebankLanguagePack languagePack = new PennTreebankLanguagePack();
        GrammaticalStructure structure = languagePack.grammaticalStructureFactory().newGrammaticalStructure(t);
        Collection<TypedDependency> typedDependencies = structure.typedDependenciesCollapsed();
        
        for(TypedDependency td : typedDependencies) {
          if(td.reln().equals(EnglishGrammaticalRelations.NOMINAL_SUBJECT)) {
            System.out.println(td);
          }
        }
*/


  }

}
