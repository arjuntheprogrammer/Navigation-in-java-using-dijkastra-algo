package Navigation;

import java.util.Arrays;
import java.util.Vector;

import org.omg.CORBA.INTERNAL;

public class Dijkastra extends Object{
	double dij(Vector<Node> nodes,  Vector<Way> ways, int[] parent, int src, int des){
		double dist[] =new double[nodes.size()+10];
		Boolean visit[]=new  Boolean[nodes.size()+10] ;
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(parent, -1);
		Arrays.fill(visit, false);
		 
		 dist[src]=0;
		 
		 for (int count = 0; count < nodes.size()-1; count++)
	     {
			
			 int u=mindist(dist, visit, nodes.size());
			 visit[u] = true;
		         int flag=0;
				 int i;
			     for( i=0;i<nodes.elementAt(u).adj.size();i++){
			        	 int temp=nodes.elementAt(u).adj.elementAt(i);
			        	 if(!visit[temp] )
			        	 {
			        		 flag=1;
			        	 }
				
			        if ( flag==1 && dist[u]+nodes.elementAt(u).weight.elementAt(i) < dist[temp])
			         {	
			        	 dist[temp] = dist[u]+nodes.elementAt(u).weight.elementAt(i);
			        	 //System.out.println("v="+v);
			        	 parent[temp]=u;
			         }
			     	 flag=0;
		       }
			     
			     if(visit[des]==true)
			    	 break;
	     }
			//System.out.println("done");

		 for(int jjj=0;jjj<nodes.size();jjj++) {
		//	System.out.println(" node no = "+jjj+" dist="+dist[jjj]);
		//	System.out.println("parent="+parent[jjj]+"\n");
			
		}
		
		 return dist[des];
	}

	private int mindist(double[] dist, Boolean[] visit, int V) {
		double min=Integer.MAX_VALUE;
				int min_index=0;
		 
		for (int v = 0; v < V; v++)
		     {
				if (visit[v] == false && dist[v] <= min)
			     {   min = dist[v];
			 		min_index = v;}
			     }
		 return min_index;
	}
}
