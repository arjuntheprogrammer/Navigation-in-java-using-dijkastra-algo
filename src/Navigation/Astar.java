package Navigation;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Vector;

class Heur{
	Double f,g,h;
	int id; 
	//Heur parent;
}


public class Astar extends Object{

	double shortest_dist(Vector<Node> nodes, int[] parent, int src, int dest){
		Arrays.fill(parent, -1);
		Dist d=new Dist();
		double dist[] =new double[nodes.size()+10];
		Vector <Heur> open=new Vector<Heur>(250, 10);
		Vector <Heur> close = new Vector<Heur>(250, 10);
		//Dictionary parent = new Hashtable();
		System.out.println("here");
		
		Heur H=new Heur();
		H.id=src;
		H.f=0.0;
		H.g=0.0;
		H.h=0.0;
		//H.parent=null;
		
		
		open.addElement(H);
		
		int u, mini=0, found=0;
		while(open.size()!=0 && found!=1){
			System.out.println("here1");
			
			mini=minIndex(open);
			u=open.elementAt(mini).id;
			
			Heur temp=new Heur();
			temp=open.elementAt(mini);			
			
			
			for(int i=0;i<nodes.elementAt(u).adj.size();i++){
				
				 //parent.put(nodes.elementAt(u).adj.elementAt(i),u);
				 
				
				
				Heur child=new Heur();
				
				child.id=nodes.elementAt(u).adj.elementAt(i);
				child.g=open.elementAt(mini).g + nodes.elementAt(u).weight.elementAt(child.id);
				child.h=d.calculateDistance(nodes.elementAt(child.id).lat,  nodes.elementAt(child.id).lon,nodes.elementAt(dest).lat,  nodes.elementAt(dest).lon );
				child.f=child.h+child.g;
				//child.parent=open.elementAt(mini);
				//parent[child.id]=u;
				
				if(nodes.elementAt(u).adj.elementAt(i)==dest){
					 
					found=1;
					System.out.println("before inner  return ");
					
					 return child.f;
				}
				
				int f=0;
				for(int j=0; j<open.size();j++){
					if(open.elementAt(j).id==child.id){
						if(child.f<open.elementAt(j).f){
							open.setElementAt(child, j);
							parent[child.id]=u;
							f=1;
							
						}
						else{
							continue;
						}
					}
					
				}
				if(f==0)
				for(int j=0; j<close.size();j++){
					if( open.elementAt(j).id==child.id){ 
						if (child.f<close.elementAt(j).f){
						
							open.addElement(child);
							
							close.removeElementAt(j);
							f=1;
							parent[child.id]=u;
						}
						else{
							continue;
						}
					}
				}
				
				
				if(f==0){
					parent[child.id]=u;
					open.addElement(child);
				}
				

			
			}
			
			open.remove(mini);
			close.addElement(temp);
		}
			
		
		
		System.out.println("before return ");
		
		return -1.0;
		
	}
	
	int minIndex(Vector<Heur> open) {
		double min=Double.MAX_VALUE;
		int index=0;
		for(int i = 0; i <open.size(); i++){
			if(open.elementAt(i).f<min){
				min=open.elementAt(i).f;
				index=i;
			}
		}
		
		return index;
	}

}

/*
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

*/