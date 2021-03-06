/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.chaoticity.dependensee;

/**
 *
 * @author aa496
 */
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
//import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;
import java.util.List;
import java.io.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Collection;
import javax.imageio.ImageIO;
import edu.stanford.nlp.io.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.trees.*;
//import edu.stanford.nlp.semgraph.SemanticGraph; for latest release enable latter
import edu.stanford.nlp.trees.semgraph.SemanticGraph;
import edu.stanford.nlp.util.*;


public class Main {
    
    private static TreebankLanguagePack tlp = new PennTreebankLanguagePack();
    private static GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
    
    public static Graph getGraph(CoreMap sentence, Tree tree, Collection<TypedDependency> tdl) throws Exception {
        ArrayList<TaggedWord> words = tree.taggedYield();
        GrammaticalStructure gs = gsf.newGrammaticalStructure(tree);
        Graph g = new Graph(words, sentence);
        System.out.println("Graph creation done successfully\n");
        for (TypedDependency td : tdl) {
            System.out.println("source index = " + td.gov().index());
            System.out.println("target index = " + td.dep().index());
            System.out.println(td.reln().toString());
            if(td.gov().index() != 0)
            	g.addEdge(td.gov().index() -1  , td.dep().index() -1 , td.reln().toString());
        }
        System.out.println("Graph creation done successfully2\n");
        /*
        try {
            System.out.println("Magic root is = " + GrammaticalStructure.getRoots(tdl).iterator().next().gov().toString());
            g.setRoot(GrammaticalStructure.getRoots(tdl).iterator().next().gov().toString());
        } catch (Exception ex) {
            System.err.println("Cannot find dependency graph root. Setting root to first");
            if (g.nodes.size() > 0) {
                g.setRoot(g.nodes.get(0).label);
            }
        }
        */
        return g;
    }

    private static int getNextHeight(Graph graph, Edge n) {
        int height = 3;
        boolean isFree = false;
        while (!isFree) {
            boolean overlapped = false;
            for (Edge e : graph.edges) {
                if (!e.visible || n == e) {
                    continue;
                }
                int eFirst = e.sourceIndex < e.targetIndex ? e.sourceIndex : e.targetIndex;
                int eSecond = e.sourceIndex < e.targetIndex ? e.targetIndex : e.sourceIndex;
                int nFirst = n.sourceIndex < n.targetIndex ? n.sourceIndex : n.targetIndex;
                int nSecond = n.sourceIndex < n.targetIndex ? n.targetIndex : n.sourceIndex;
                if (e.height == height
                        && ((nFirst > eFirst && nFirst < eSecond)
                        || (nSecond > eFirst && nSecond < eSecond)
                        || (eSecond > nFirst && eSecond < nSecond)
                        || (eSecond > nFirst && eSecond < nSecond)
                        || (n.targetIndex == eFirst)
                        || (n.targetIndex == eSecond))) {
                    overlapped = true;
                    //System.out.println("overlap = "+ n +" and " + e + " at height " + height);
                }
            }
            if (!overlapped) {
                isFree = true;
            } else {
                height++;
            }
            
        }
        return height;
    }
    
    public static void writeImage(Graph g, String outFile, int scale) throws Exception {
        //Graph g = getGraph(tree, tdl);
        BufferedImage image = createTextImage(g, scale);
        ImageIO.write(image, "png", new File(outFile));
    }
    
    private static BufferedImage createTextImage(Graph graph, int scale) throws Exception {
        
        Font wordFont = new Font("Arial", Font.PLAIN, 12 * scale);
        FontRenderContext frc = new FontRenderContext(null, true, false);
        
        int spaceHeight = 20 * scale;
        int spaceWidth = 20 * scale;
        double totalWidth = spaceWidth;

        // calculate word positions
        for (Integer i : graph.nodes.keySet()) {
            Node node = graph.nodes.get(i);
            TextLayout layout = new TextLayout(node.toString(), wordFont, frc);
            Rectangle2D bounds = layout.getBounds();
            node.position.setRect(totalWidth, 0, bounds.getWidth(), bounds.getHeight());
            totalWidth += node.position.getWidth() + spaceWidth;
        }
        int imageWidth = (int) Math.ceil(totalWidth);
        int imageHeight = spaceHeight * (6 * scale + graph.nodes.size());
        int baseline = imageHeight - 40 * scale;

        // create image
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setBackground(Color.white);
        g.clearRect(0, 0, imageWidth, imageHeight);
        g.setColor(Color.black);
        g.setFont(wordFont);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);


        // draw words
        for (Integer i : graph.nodes.keySet()) {
            Node node = graph.nodes.get(i);
            node.position.setRect(node.position.getX(), baseline - spaceHeight, node.position.getWidth(), node.position.getHeight());
            g.drawString(node.toString(), (int) node.position.getX(), (int) node.position.getY());
        }
        
        Font posFont = new Font("Arial", Font.PLAIN, 8 * scale);
        g.setColor(Color.darkGray);
        g.setFont(posFont);
        for (Integer i : graph.nodes.keySet()) {
            Node node = graph.nodes.get(i);
            node.position.setRect(node.position.getX(), baseline - 10 * scale, node.position.getWidth(), node.position.getHeight());
            g.drawString(node.pos, (int) node.position.getX(), (int) node.position.getY());
        }

	// draw ner
        for (Integer i : graph.nodes.keySet()) {
            Node node = graph.nodes.get(i);
            node.position.setRect(node.position.getX(), baseline - 2 * scale, node.position.getWidth(), node.position.getHeight());
            g.drawString(node.ner, (int) node.position.getX(), (int) node.position.getY());
        }

        // draw coref representative mention list
        for (Integer i : graph.nodes.keySet()) {
            Node node = graph.nodes.get(i);
            node.position.setRect(node.position.getX(), baseline + 10 * scale, node.position.getWidth(), node.position.getHeight());
            String s = "";
            boolean bDraw = false;
           // System.out.println("outside draw coref");
            for (Node n : node.RepresentativeMentionList) {
             //   System.out.println("inside draw coref");
                s += n.toString();
                s += " ";   
                bDraw = true;
            }
            if(bDraw == true)
            	g.drawString(s, (int) node.position.getX(), (int) node.position.getY());
            else
            	g.drawString("0", (int) node.position.getX(), (int) node.position.getY());
        }

        // draw coref mention list
        for (Integer i : graph.nodes.keySet()) {
            Node node = graph.nodes.get(i);
            node.position.setRect(node.position.getX(), baseline + 20 * scale, node.position.getWidth(), node.position.getHeight());
            String s = "";
            boolean bDraw = false;
            //System.out.println("outside draw coref");
            for (Node n : node.MentionList) {
              //  System.out.println("inside draw coref");
                s += n.toString();
                s += " ";   
                bDraw = true;
            }
            if(bDraw == true)
            	g.drawString(s, (int) node.position.getX(), (int) node.position.getY());
            else
            	g.drawString("0", (int) node.position.getX(), (int) node.position.getY());
        }

        // draw gender
        for (Integer i : graph.nodes.keySet()) {
            Node node = graph.nodes.get(i);
            node.position.setRect(node.position.getX(), baseline + 30 * scale, node.position.getWidth(), node.position.getHeight());
            String gender = "";
            if(node.gender == Gender.GENDER_MALE)
		gender = "MALE";
	    else if(node.gender == Gender.GENDER_FEMALE)
		gender = "FEMALE";
	    else
		gender = "OTHER";
            g.drawString(gender, (int) node.position.getX(), (int) node.position.getY());
        }
	
        // draw ActorNameInCasePronoun
        for (Integer i : graph.nodes.keySet()) {
            Node node = graph.nodes.get(i);
            node.position.setRect(node.position.getX(), baseline + 40 * scale, node.position.getWidth(), node.position.getHeight());
            String s = "";
            if(node.isActorPronoun == true)
                s = node.ActorNameInCasePronoun;
            g.drawString(s, (int) node.position.getX(), (int) node.position.getY());
        }

        
        g.setColor(Color.black);

        // draw lines
        int lineDistance = 5 * scale;
        int arrowBase = 2 * scale;
        int maxHeight = 0;
        for (Integer i : graph.nodes.keySet()) {
            Node node = graph.nodes.get(i);
            int spacer = (int) node.position.getWidth() / 2 - (node.outEdges.size() / 2 * lineDistance);
            for (Edge e : node.outEdges) {
                int height = getNextHeight(graph, e);
                if (height > maxHeight) {
                    maxHeight = height;
                }
                e.height = height;
                int targetSpacer = (int) e.target.position.getWidth() / 2 - ((e.target.outEdges.size() + 2) / 2 * lineDistance);
                // horizontal line
                g.drawLine(
                        (int) e.source.position.getX() + spacer,
                        baseline - (height * spaceHeight),
                        (int) e.target.position.getX() + targetSpacer,
                        baseline - (height * spaceHeight));

                // source vertical line
                g.drawLine(
                        (int) e.source.position.getX() + spacer,
                        baseline - (height * spaceHeight),
                        (int) e.source.position.getX() + spacer,
                        baseline - spaceHeight * 2);


                // target vertical line
                g.drawLine(
                        (int) e.target.position.getX() + targetSpacer,
                        baseline - (height * spaceHeight),
                        (int) e.target.position.getX() + targetSpacer,
                        baseline - spaceHeight * 2);

                // target arrowhead
                g.drawLine(
                        (int) e.target.position.getX() - arrowBase + targetSpacer,
                        baseline - spaceHeight * 2 - 4 * scale,
                        (int) e.target.position.getX() + targetSpacer,
                        baseline - spaceHeight * 2);
                g.drawLine(
                        (int) e.target.position.getX() + arrowBase + targetSpacer,
                        baseline - spaceHeight * 2 - 4 * scale,
                        (int) e.target.position.getX() + targetSpacer,
                        baseline - spaceHeight * 2);
                e.visible = true;
                spacer += lineDistance;
            }
            
        }

        //draw relation labels

        Font relFont = new Font("Arial", Font.PLAIN, 10 * scale);
        g.setColor(Color.blue);
        g.setFont(relFont);
        
        for (Integer i : graph.nodes.keySet()) {
            Node node = graph.nodes.get(i);
            int spacer = (int) node.position.getWidth() / 2 - (node.outEdges.size() / 2 * lineDistance);
            for (Edge e : node.outEdges) {
                int targetSpacer = (int) e.target.position.getWidth() / 2 - ((e.target.outEdges.size() + 2) / 2 * lineDistance);
                int x = (int) (e.source.position.getX() < e.target.position.getX()
                        ? e.source.position.getX() + spacer
                        : e.target.position.getX() + targetSpacer);
                TextLayout layout = new TextLayout(e.label, relFont, frc);
                Rectangle2D bounds = layout.getBounds();
                int clearWidth = (int) Math.ceil(bounds.getWidth());
                int clearHeight = (int) Math.ceil(bounds.getHeight()) + 2 * scale;
                g.clearRect(x, baseline - (e.height * spaceHeight) - clearHeight - 2 * scale,
                        clearWidth, clearHeight);
                g.drawString(e.label, x, baseline - (e.height * spaceHeight) - 3 * scale);
                
                spacer += lineDistance;
            }
        }
        
        g.dispose();
        int ystart = imageHeight - spaceHeight * (maxHeight + 3 * scale);
        return image.getSubimage(0, ystart, imageWidth, imageHeight - ystart);
    }

    public static void printGraph(Graph graph) throws Exception {

        for (Integer i : graph.nodes.keySet()) {
            Node node = graph.nodes.get(i);
            System.out.println(node.label + " ");
            for (Edge e : node.outEdges) {
            	System.out.println(e.label + " ");
            }
            System.out.println("\n");
	}

    }

}
