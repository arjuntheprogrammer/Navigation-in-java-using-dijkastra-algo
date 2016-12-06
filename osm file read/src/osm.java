import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
public class osm {

static String trim1(String a){
	Character c=a.charAt(0);
	String b="";
	if(c.compareTo('i')==0){
		for(int i=4;i<a.length()-1;i++)
		{
			b=b+ a.charAt(i);
			
		}
		
	}
	else if(c.compareTo('l')==0){
		for(int i=5;i<a.length()-1;i++)
		{
			b=b+ a.charAt(i);
			
		}
		
	}
	else if(c.compareTo('r')==0){
		for(int i=6;i<a.length()-3;i++)
		{
			b=b+ a.charAt(i);
			
		}
		
	}
	else if(c.compareTo('k')==0){
		for(int i=3;i<a.length()-1;i++)
		{
			b=b+ a.charAt(i);
			
		}
		
	}
	else if(c.compareTo('v')==0){
		for(int i=3;i<a.length()-3;i++)
		{
			b=b+ a.charAt(i);
			
		}
		
	}
	
	
	return b;
}

static void loadNodes() throws IOException
{

	
	final String JBDC_DRIVER="com.mysql.jdbc.Driver";
	final String DB_URL="jdbc:mysql://localhost/delhimap";
	
	final String USER ="root";
	final String PASS ="";
	Connection conn=null;
		Statement stmt=null;
		try{
			//Class.forName(JBDC_DRIVER);
		      Class.forName("com.mysql.jdbc.Driver");

			System.out.println("connecting to database...");
			conn=DriverManager.getConnection(DB_URL, USER, PASS);
			 stmt=conn.createStatement();
			//conn=DriverManager.getConnection("jdbc:mysql://localhost?user=root&password=");//connection with database

			
//			System.out.println("insert into  database...");
			//stmt=conn.createStatement();
			//String sql="INSERT INTO node " +   "VALUES ( )";
			//stmt.executeUpdate(sql);
			
			//System.out.println("database creates successfully");
			
		


	
	
	FileReader ff1=new FileReader("new-delhi_india.osm");
	BufferedReader fin1 =new BufferedReader(ff1);
	String line1;
	String k1="",v1="",lon1="", lat1="", id1="",x1="";
	String k2="",v2="",ref2="",  id2="";
	
	//Set<String> key = new HashSet<String>();
	
	StringTokenizer st1;
	int i=0;
	StringBuilder query = new StringBuilder();
	StringBuilder kk = new StringBuilder();
	StringBuilder vv = new StringBuilder();
	query.append("INSERT INTO node VALUES ");
	while((line1=fin1.readLine())!=null){
		
		st1=new StringTokenizer(line1);
		kk.delete(0, kk.length());
		vv.delete(0, vv.length());


			x1=st1.nextToken();
			//System.out.println("k="+k1);
			if(x1.equals("<node")){
			
					id1=trim1(st1.nextToken());
					lat1=trim1(st1.nextToken());
					lon1=trim1(st1.nextToken());
					//System.out.println("id1="+id1+"  lat1="+lat1+"  lon1="+lon1);
					
					
					 
					 
					 
					//reading 
					for(int jjj=0;jjj<5;jjj++)
					x1=st1.nextToken();
					
					Character xx1=x1.charAt(x1.length()-2);
		
					if(xx1.compareTo('/')!=0){
						k1="";
						v1="";
						while((line1=fin1.readLine())!=null){
							k1="";
							v1="";
							
							st1=new StringTokenizer(line1);
							 x1=st1.nextToken();
								
							 if(x1.equals("<tag")){
									
								 k1=trim1(st1.nextToken());
								 //key.add(k1);
								 v1+=st1.nextToken();
								 while(st1.hasMoreTokens()){
									 v1+=" ";
									 v1+=st1.nextToken();}
								 	v1=trim1(v1);
								 	
								kk.append(k1+" $ ");
								vv.append(v1+" $ ");
								
									
							 }
							 else{
							
								
								 break;
							 }
							
						}
						
					}
	
					
					//reading end
					
					//inserting
					
					if(i==10000)
					 {
						
						 query.append("("+id1+","+lat1+","+lon1+",'"+kk.toString()+"','"+vv.toString()+"' ) ");
						 stmt.executeUpdate(query.toString());
						query.delete(0, query.length());
						query.append("INSERT INTO node VALUES ");
						i=0;
						
					 }
					 else
					 {
						 query.append("("+id1+","+lat1+","+lon1+",'"+kk.toString()+"','"+vv.toString()+"' ),");
						 i++;
					 }
					
					//insertion end
				
				
			}
			if(x1.equals("<way")){
				id2=trim1(st1.nextToken());
				//System.out.println("id2="+id2);
				while((line1=fin1.readLine())!=null){
					
					st1=new StringTokenizer(line1);
					 x1=st1.nextToken();

					 if(x1.equals("<nd")) {
							
						 ref2=trim1(st1.nextToken());
						 
						 	
					//	 System.out.println("ref2="+ref2);
							
					 }
					 else if(x1.equals("<tag")){
						 
						 k2=trim1(st1.nextToken());
						 v2="";
						 v2+=st1.nextToken();
						 while(st1.hasMoreTokens()){
							 v2+=" ";
							 v2+=st1.nextToken();}
						 	v2=trim1(v2);
						// System.out.println("k2="+k2+"  v2="+v2);
						 
					 }
					 
					 else{
							
						 break;
					 }
					 
				}
				
			}
			
			
			
		}
	 query.deleteCharAt(query.length()-1);
	 stmt.executeUpdate(query.toString());
	
	/*Iterator<String> it=key.iterator();
	while(it.hasNext())
	{
		System.out.println(it.next());
	}
		System.out.println(key.size());*/
	
		}	
		catch(Exception e){
			e.printStackTrace();
			
		}
			
}


		
	public static void main(String ar[]) throws IOException{
		loadNodes();
	}

}
