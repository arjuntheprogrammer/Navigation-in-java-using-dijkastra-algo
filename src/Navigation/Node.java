package Navigation;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;


public class Node extends Object implements Comparable {
	public Double lon, lat;
    public long id;
	boolean type = false; // true if part of any way. else false
    Vector <StringBuilder> k=new Vector<StringBuilder>(250, 10);
	Vector <StringBuilder> v=new Vector<StringBuilder>(250, 10);
	Vector <Integer> adj=new Vector<Integer>(250, 10);
	Vector <Integer> adj_type = new Vector<Integer>(250, 10);
	Vector <Double> weight=new Vector<Double>(250, 10);
	
	public long getID()
	{
		return id;
	}

	public int compareTo(Object e) {
		Node n = (Node) e;
		int result = this.lat.compareTo(n.lat);
		
		return result;
	}
}

 class Way {
		public long id ;
		Vector <StringBuilder> k=new Vector<StringBuilder>(250, 10);
		Vector <StringBuilder> v=new Vector<StringBuilder>(250, 10);
		Vector <Long> ref=new Vector<Long>(250, 10);
	
}
 class Relation {
		
		public long id ;
		Vector <StringBuilder> type=new Vector<StringBuilder>(250, 10);
		
		Vector <Long> refn=new Vector<Long>(250, 10);
		Vector <Long> refw=new Vector<Long>(250, 10);
		Vector <Long> refr=new Vector<Long>(250, 10);
		
		Vector <StringBuilder> rolen=new Vector<StringBuilder>(250, 10);
		Vector <StringBuilder> rolew=new Vector<StringBuilder>(250, 10);
		Vector <StringBuilder> roler=new Vector<StringBuilder>(250, 10);
		
		Vector <StringBuilder> k=new Vector<StringBuilder>(250, 10);
		Vector <StringBuilder> v=new Vector<StringBuilder>(250, 10);

 }
  
 class BinarySearch {
	 
	    public  static int BSearch(Vector<Node> nodes, int start, int end, long key) {
	         
	        if (start <= end) {
	            int mid = start + (end - start) / 2; 
	            if (key < nodes.elementAt(mid).id) {
	                return BSearch(nodes, start, mid, key);
	            } 
	            
	            else if (key > nodes.elementAt(mid).id) {
	               return BSearch(nodes, mid+1, end , key);
	                 
	            }
	            
	            else {
	                return mid;  
	            }
	        }
	        return -1; 
	    }
}
 
class Dist
 {
 	public final static double AVERAGE_RADIUS_OF_EARTH = 6371;
	 public static double calculateDistance(double userLat, double userLng,
	   double venueLat, double venueLng) {
	
	     double latDistance = Math.toRadians(userLat - venueLat);
	     double lngDistance = Math.toRadians(userLng - venueLng);
	
	     double a = Math.sin(latDistance / 2.0) * Math.sin(latDistance / 2.0)
	       + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(venueLat))
	       * Math.sin(lngDistance / 2.0) * Math.sin(lngDistance / 2.0);
	
	     double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	
	     return (AVERAGE_RADIUS_OF_EARTH * c);
	 }
 
 }


class TrieNode 
{
    char content; 
    boolean isEnd; 
    int count;
    long id;
    LinkedList<TrieNode> childList; 
 
    /* Constructor */
    public TrieNode(char c)
    {
        childList = new LinkedList<TrieNode>();
        isEnd = false;
        
        content = c;
        count = 0;
    }  
    public TrieNode subNode(char c)
    {
        if (childList != null)
            for (TrieNode eachChild : childList)
                if (eachChild.content == c)
                    return eachChild;
        return null;
    }
}

class Trie
{
    private TrieNode root;
 
     /* Constructor */
    public Trie()
    {
        root = new TrieNode(' '); 
    }
     /* Function to insert word */
    public void insert(StringBuilder word, Long id1)
    {
    	//StringBuilder sb=new StringBuilder();
    	//sb.append(word.toString().toLowerCase());
    	if (search(word) == true) 
            return;    
        
        TrieNode current = root; 
        for (char ch : word.toString().toLowerCase().toCharArray() )
        {
            TrieNode child = current.subNode(ch);
            if (child != null)
                current = child;
            else 
            {
                 current.childList.add(new TrieNode(ch));
                 current = current.subNode(ch);
            }
            current.count++;
        }
        current.isEnd = true;
        current.id=id1;
    }
    /* Function to search for word */
    public boolean search(StringBuilder word)
    {
    	
    	//StringBuilder sb=new StringBuilder();
    	//sb.append(word.toString().toLowerCase());
    	
        TrieNode current = root;  
        for (char ch : word.toString().toLowerCase().toCharArray() )
        {
            if (current.subNode(ch) == null)
                return false;
            else
                current = current.subNode(ch);
        }      
        
        if (current.isEnd == true) 
            return true;
        return false;
    }
    
    public void dfs(TrieNode current, StringBuilder word, Vector<StringBuilder> trial, Vector<Long> search_id ){
    	if (current.isEnd == true && current.count==0) 
    	{ 
    		
    		System.out.print(" word="+word+" ");
    		System.out.println(" id="+current.id+" ");
    		StringBuilder sb=new StringBuilder();
    		sb.append(word.toString().toLowerCase());
    		trial.addElement(sb);
    		search_id.addElement(current.id);
    		
    	
    	}
    	else{
    		if (current.isEnd == true ) 
        	{ 
        		System.out.print(" word="+word+" ");
        		System.out.println(" id="+current.id+" ");
        		
        		StringBuilder sb=new StringBuilder();
        		sb.append(word.toString().toLowerCase());
        		trial.addElement(sb);
        		
        		search_id.addElement(current.id);
        		
        	}
		   		for (TrieNode eachChild : current.childList)
	            {
		   			StringBuilder word1=new StringBuilder();
		   			word1.append(word);
		   			word1=word1.append(eachChild.content); 
		   			dfs(eachChild, word1, trial, search_id);
				 	
	            }
		    	
		    
    	}
    }
    public boolean search1(StringBuilder word, Vector<StringBuilder> trial, Vector<Long> search_id)
    {
        TrieNode current = root;  
        for (char ch : word.toString().toLowerCase().toCharArray() )
        {
            if (current.subNode(ch) == null)
                return false;
            else
                current = current.subNode(ch);
        }      
        
       dfs(current , word, trial, search_id);
        
        return true;
    }
  }
