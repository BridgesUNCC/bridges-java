package edu.uncc.cs.bridges_vs1.drivers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import edu.uncc.cs.bridges.BSTNode;
import edu.uncc.cs.bridgesV2.validation.*;
import edu.uncc.cs.bridgesV2.*;
import edu.uncc.cs.bridgesV2.base.ADTVisualizer;
import edu.uncc.cs.bridgesV2.base.BSTElement;
import edu.uncc.cs.bridgesV2.base.Element;
import edu.uncc.cs.bridgesV2.base.TreeElement;
import edu.uncc.cs.bridgesV2.connect.Bridges;
import edu.uncc.cs.bridgesV2.connect.DataFormatter;
import edu.uncc.cs.bridgesV2.data_src_dependent.EarthquakeTweet;
import edu.uncc.cs.bridgesV2.data_src_dependent.Tweet;
import edu.uncc.cs.bridgesV2.data_src_dependent.TwitterAccount;
/**
 * 
 * @author Mihai Mehedint
 *
 */
public class TreeEarthquakeTweeterDriver {
	public static final int maxElements = 10; //number of Followers from Twitter
	public static BSTElement<Double, EarthquakeTweet> root;
	/**
	 * The populate method enQueues a specified number of Tweeter followers
	 * @param root is the first element of the queue
	 * @param queue the current queue
	 * @param max is the number of followers enqueued by this method
	 * @return the queue populated
	 * @throws Exception 
	 */
	public static ADTVisualizer<EarthquakeTweet> populate(List<EarthquakeTweet> aList, ADTVisualizer<EarthquakeTweet> tree) throws Exception{
		if (aList.size()!=0){
			PrintWriter writer = new PrintWriter(System.getProperty("user.home")+ File.separator+"EQ_Tweets_500.txt", "UTF-8");
			//Set<Map.Entry<String, Element<EarthquakeTweet>>> existingElements=tree.entrySet();
			for(int i=0; i<aList.size();i++){
				//if (!existingElements.contains(aList.get(i))){
					double aValue = aList.get(i).getMagnitude(); 
					BSTElement<Double, EarthquakeTweet> anEarthquake = new BSTElement<Double, EarthquakeTweet>(aValue, aList.get(i)); 
					anEarthquake.getVisualizer().setSize((int)aValue);
					insertNode(root, anEarthquake);
					double size = (aValue-5)*10;//object size range is 0-50; the actual value is adjusted for a better visualization
					anEarthquake.setLabel(aList.get(i).getContent());
					String s =aList.get(i).getContent();
					
					anEarthquake.arrangeLabel(s, 1);
					/**
					anEarthquake.DIVIDE_KEY="(#earthquake )";
					anEarthquake.INSERT_STRING =""; 
					s = anEarthquake.arrangeLabel(s, 0);
					anEarthquake.DIVIDE_KEY="(, )";
					anEarthquake.INSERT_STRING =" "; 
					s = anEarthquake.arrangeLabel(s, 0);
					anEarthquake.DIVIDE_KEY="( http.*$)";
					anEarthquake.INSERT_STRING =""; 
					s = anEarthquake.arrangeLabel(s, 0);
					anEarthquake.DIVIDE_KEY="( UTC)";
					anEarthquake.INSERT_STRING ="";
					s = anEarthquake.arrangeLabel(s, 0);
					anEarthquake.DIVIDE_KEY="(\\s+)";
					anEarthquake.INSERT_STRING ="_"; 
					s = anEarthquake.arrangeLabel(s, 0);
					anEarthquake.DIVIDE_KEY="(?<=M)(_)";
					anEarthquake.INSERT_STRING =" "; 
					s = anEarthquake.arrangeLabel(s, 0);
					anEarthquake.DIVIDE_KEY="((?<=[0-9]+)(_))|((_)(?=[0-9]+\\.?[0-9]?))";
					anEarthquake.INSERT_STRING =" "; 
					s = anEarthquake.arrangeLabel(s, 0);
					s=s.substring(0,s.lastIndexOf("_"))+" "+s.substring(s.lastIndexOf("_")+1);
					writer.println(s);*/
				//}	
			}
			writer.close();
		}
		return tree;
	}
	
	/**
	 * Inserting nodes in the tree
	 * @param <E>
	 * @param rt
	 * @param newNode
	 * @return
	 * @throws Exception
	 */
	private static <E> BSTElement<?, E> insertNode(BSTElement<?, E> rt, BSTElement<?, E> newNode) throws Exception{
		if(rt == null){			
			return newNode;			
		//}else if(newNode.getVisualizer().getSize() < rt.getVisualizer().getSize()){
		}else if((Double)newNode.getKey() < (Double)rt.getKey()){	
			if(rt.getLeft() == null){
				rt.setLeft(newNode); 				
			}else{
				rt.setLeft(insertNode(rt.getLeft(), newNode));				
			}				
		}else{
			if(rt.getRight() == null){
				rt.setRight(newNode);				
			}else{				
				rt.setRight(insertNode(rt.getRight(), newNode));				
			}				
		}
		return rt;
	}
	
	
	public static void main(String[] args) throws Exception{
		//OutputLog aLog = new OutputLog();
		//TreeVisualizer<EarthquakeTweet> tree = new TreeVisualizer<EarthquakeTweet>();
		Bridges<EarthquakeTweet> bridge = new Bridges<EarthquakeTweet>(12, "300587042698", "mmehedin@uncc.edu");
		//Bridge.init(73, "300587042698", tree, "mmehedin@uncc.edu");
		
		// Any actual user on Twitter; in this case we use the earthquake account
		TwitterAccount name = new TwitterAccount("earthquake"); 
		
		//retrieve a list of 5 tweets 
		List<Tweet> TweetList = DataFormatter.getAssociations(name, maxElements);
		
		//and convert them to EarthquakeTweets 
		//EarthquakeTweet is a special tweet containing also the magnitude field
		List<EarthquakeTweet> EarthquakeTweetList = DataFormatter.convertTweet (TweetList);
		
		//the the tweets to the tree
		populate(EarthquakeTweetList, bridge.getVisualizer());
		root = new BSTElement<Double, EarthquakeTweet>(0.0, EarthquakeTweetList.get(0));
		root.getVisualizer().setSize((int)(EarthquakeTweetList.get(0).getMagnitude()));
		root.setKey(EarthquakeTweetList.get(0).getMagnitude());
		root.getVisualizer().setColor("red");
		System.out.println(root.getLabel());
		bridge.setDataStructure(root, "tree");
		root.setRight(root.getLeft());
		bridge.visualize();
		
		//remove minimum
		//tree.rMin();
				
		bridge.visualize();
		
		//get a second list of earthquakes
		List<Tweet> aSecondTweetList = new ArrayList<>();
		
		DataFormatter.next(aSecondTweetList, 10);
		
		//add the new batch to the tree
		populate(DataFormatter.convertTweet (aSecondTweetList), bridge.getVisualizer());
		
		
		//remove node method
		//remove root, this will cause errors
		//tree.removeN(tree.getRoot());
		
		bridge.visualize();;
		
		//remove minimum
		//tree.rMin();
		
		//get another snapshot of the tree
		bridge.visualize();
		
		//This is another removeN statement
		//tree.removeN(tree.getRoot());
		//tree.removeN(tree.getRoot());
		
		//find and trace the route to max using the yellow color
		//tree.fMax();
				
		//System.out.println("Is the number found: " + tree.find(7));
		//find the min value in the tree
		//tree.fMin();
		
		//Bridge.update();
		
		//To see the JSON content
		System.out.println("\nThe JSON is: ");
		bridge.toggleJSONdisplay();
		bridge.visualize();
		
		//aLog.returnStream();
	}

}
