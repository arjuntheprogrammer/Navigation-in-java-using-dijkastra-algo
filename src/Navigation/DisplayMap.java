package Navigation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import javax.swing.JToggleButton;


public class DisplayMap extends JPanel implements ActionListener, MouseListener, KeyListener, MouseMotionListener  {
	static int w,h,xs,ys;
	private static final long serialVersionUID = 1L;
	static Double zoomFactor = 0.02;
	static Double zoomFactor1 = 0.08;
	static Double moveFactor = 0.002;
	static Double moveFactor1 = 0.006;
	static Double ratio = 0.3;
	static int zoomcont = 0;
	int src=678, dest=1382;
	int parent[] =new int[1000000];
	Dijkastra dd=new Dijkastra();
	StringBuilder sb=new StringBuilder();
	static Vector<Node> nodes=new Vector<Node>(25000, 10);
	static Vector<Node> nodes_sort=new Vector<Node>(25000, 10);
    static Vector<Way> ways=new Vector<Way>(25000, 10);
    static Vector<Relation> relations=new Vector<Relation>(25000, 10);
    static int max_color; 	//updated by chanchur
    static int valid_nodes[];	//updated by chanchur
    public BufferedImage image;
    public BufferedImage src_marker, dst_marker;
    
    public Point drawPoint;
    public Point drawPoint1;
    static Double minLat=180.0, maxLat=0.0, minLong=180.0, maxLong=0.0;
    double lat2 =28.6317 ,lon2 = 77.2201;	//Source
	double lat1 =28.63566 ,lon1 = 77.21587 ;	//Destination
	public String str1,str2,str3="ASD",str4,str5;
	public JTextField textField;
    public JTextField textField_1;
	public boolean  getselectedafterplacesearch = false;
	public boolean getselecctedafterdirectionsearch =false;
    public boolean switch1=false;         //chanchur on or off
	static Vector<StringBuilder> trial1=new Vector<StringBuilder>(100,10);
    static Vector<Long> search_idn1=new Vector<Long>(100,10);
    
    static Vector<StringBuilder> trial2=new Vector<StringBuilder>(100,10);
    static Vector<Long> search_idn2=new Vector<Long>(100,10);
    
    JButton btnNewButton = new JButton("New button");
    public Double lon_search1=0.0, lat_search1=0.0;
    public Double lon_search2=0.0, lat_search2=0.0;
    public static int counter=0;
    
    double user_lat_dst =28.6317 ,user_lon_dst = 77.2201;	//Source
   	double user_lat_src =28.63566 ,user_lon_src = 77.21587 ;	//Destination
   	double dis_final=0,dis_dij=0;
	double near_lat_dst ,near_lon_dst;	//Source
	double near_lat_src ,near_lon_src;	//Destination
	boolean paint_way=false;

    
    Trie tn = new Trie(); //trie tree node_object 
    Trie tw= new Trie(); //trie tree way_object
    
	 public void mouseDragged(MouseEvent e) {	
		
    }
	 public void mousePressed(MouseEvent e ) {
		 	xs = e.getX();
	         ys = e.getY();
		 
	        repaint();
	    }
	 public void mouseReleased(MouseEvent e) {
		 int xf = e.getX();
	        int yf = e.getY();
	       
	       
	        
	        
	        
	        if(zoomcont==0)
	        {       zoomFactor=0.02;
			zoomFactor1=0.08;
	        	moveFactor=0.002;
	            moveFactor1=0.006;
	    	        	
	        }
	        if(zoomcont==1)
	        {zoomFactor=0.02;
			zoomFactor1=0.08;
	        	moveFactor=0.0006;
	            moveFactor1=0.004;
	    	        	
	        }
	        if(zoomcont==2)
	        {        zoomFactor=0.016;
			zoomFactor1=0.015;
			
	        	moveFactor=0.002;
	            moveFactor1=0.002;
	    	        	
	        }
	        if(zoomcont==3)
	        {zoomFactor=0.005;
			zoomFactor1=0.009;
			
	        	moveFactor=0.002;
	            moveFactor1=0.0035;
	    	        	
	        }
	        if(zoomcont==4)
	        {zoomFactor=0.0005;
			zoomFactor1=0.002;
			
	        	moveFactor=0.008;
	            moveFactor1=0.023;
	    	        	
	        }
	        if(zoomcont==5)
	        {zoomFactor=0.0005;
			zoomFactor1=0.002;
			
	        	moveFactor=0.0045;
	            moveFactor1=0.023;
	    	        	
	        }
	        if(zoomcont==6)
	        {zoomFactor=0.001;
			zoomFactor1=0.001;
			
	        	moveFactor=0.007;
	            moveFactor1=0.01;
	    	        	
	        }
	        if(zoomcont==7)
	        {zoomFactor=0.001;
			zoomFactor1=0.001;
			
	        	moveFactor=0.008;
	            moveFactor1=0.009;
	    	        	
	        }
	        if(zoomcont==8)
	        {zoomFactor=0.001;
			zoomFactor1=0.001;
			
	        	moveFactor=0.005;
	            moveFactor1=0.006;
	    	        	
	        }
	        maxLong+=(xs-xf)*moveFactor*zoomFactor1;
			minLong+=(xs-xf)*moveFactor*zoomFactor1;
			
			maxLat+=(yf-ys)*moveFactor1*zoomFactor;
			minLat+=(yf-ys)*moveFactor1*zoomFactor;
	
			repaint();
		}
	 public void mouseMoved(MouseEvent f) 
	{
				
			
      }
	 public DisplayMap() {

              
		
		 try {this.addMouseListener(this); 
		    this.addKeyListener( this);
		    image = ImageIO.read(getClass().getResource("abc.png"));
		    src_marker = ImageIO.read(getClass().getResource("marker_green.png"));
		    dst_marker = ImageIO.read(getClass().getResource("marker_red.png"));
			
					loadNodes();
			} catch (IOException e) {
				e.printStackTrace();
			}
		setLayout(new MigLayout("", "[71px][79px][][][][grow][grow][][][-19.00][][13.00,grow][][86.00][][239.00][-36.00,grow][][][][][]", "[23px][][53.00][52.00][grow][35.00,grow][grow]"));
		
		textField = new JTextField();
		add(textField, "cell 1 0 7 1,growx");
		textField.setColumns(10);
		
		add(btnNewButton, "cell 21 3");
		btnNewButton.setEnabled(false);
		btnNewButton.setVisible(false);

		str1=textField.getText();
		JButton btnZoomIn = new JButton("+");
		btnZoomIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(zoomcont>=1)
				{zoomFactor=0.004*4;
				zoomFactor1=0.003*5;
				
				}
				 if(zoomcont>=2)
				{zoomFactor=0.005;
				zoomFactor1=0.009;
				
				}
				 if(zoomcont>=3)
				{zoomFactor=0.0005;
				zoomFactor1=0.002;
				
				}
			    if(zoomcont>=6)
				{zoomFactor=0.001;
				zoomFactor1=0.001;
				
				}
				if(zoomcont<8)
				{
				//Zoom In Code
				if( (maxLat-zoomFactor)>(minLat+zoomFactor)  && (maxLong-zoomFactor1)>(minLong+zoomFactor1)){
				maxLat-=zoomFactor;
				minLat+=zoomFactor;
				maxLong-=zoomFactor1;
				minLong+=zoomFactor1;
				zoomcont++;
				repaint();}
				}
			}
		});
		
		final JComboBox comboBox = new JComboBox();
		add(comboBox, "cell 10 0 7 1,growx");
		 final JComboBox comboBox_1 = new JComboBox();
			add(comboBox_1, "cell 10 1 7 1,growx");
		          comboBox_1.setVisible(false);
		
	

		add(btnZoomIn, "cell 21 0,growx,aligny top");
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 1 7 1,growx");
		textField_1.setColumns(10);
		str2=textField_1.getText();
			
		textField_1.setVisible(false);
		
		final JButton btnNavigate = new JButton("Navigate");
		add(btnNavigate, "cell 17 1");
		JRadioButton rdbtnOff = new JRadioButton("OFF",true);
		add(rdbtnOff, "flowx,cell 21 2");
		
		JRadioButton rdbtnOn = new JRadioButton("ON");
		add(rdbtnOn, "cell 21 2");
		
		final JRadioButton rdbtn1 = new JRadioButton("Directions");
		add(rdbtn1, "cell 9 1");
		ButtonGroup group2=new ButtonGroup();
		group2.add(rdbtnOn);
		group2.add(rdbtnOff);
		final JButton btnSearch = new JButton("Search");
		add(btnSearch, "cell 17 0");
	
		final JRadioButton rdbtn2 = new JRadioButton("Place search",true);
		add(rdbtn2, "cell 9 0");
		rdbtnOn.addItemListener(new ItemListener() {public void itemStateChanged(ItemEvent e)
		{ if(e.getStateChange()==1)
		     {switch1=true;
		     
		     
		     
		     paint_way=false;
		     
		     textField_1.setVisible(false);
		     comboBox_1.setVisible(false);
		     textField.setVisible(false);
		     comboBox.setVisible(false);
              rdbtn1.setVisible(false);                      
              rdbtn2.setVisible(false);
              btnSearch.setVisible(false);
              btnNavigate.setVisible(false);
              btnNewButton.setVisible(false);
		     repaint();
		     }
	}
	});
		rdbtnOff.addItemListener(new ItemListener() {public void itemStateChanged(ItemEvent e)
		{ if(e.getStateChange()==1)
		     {switch1=false;
		     paint_way=false;
		     if(rdbtn1.isSelected())
		     { textField_1.setVisible(true);
		     comboBox_1.setVisible(true);}
		     textField.setVisible(true);
		     comboBox.setVisible(true);
              rdbtn1.setVisible(true);                      
              rdbtn2.setVisible(true);
              btnSearch.setVisible(true); 
              btnNavigate.setVisible(true);
              btnNewButton.setVisible(false);
              repaint();}
	}
	});
		
		
		add(rdbtn2, "cell 9 0");
		rdbtn1.addItemListener(new ItemListener() {public void itemStateChanged(ItemEvent e)
		{ if(e.getStateChange()==1)
		     {textField_1.setVisible(true);
		     comboBox_1.setVisible(true);
				}
	}
	});

		rdbtn2.addItemListener(new ItemListener() {public void itemStateChanged(ItemEvent e)
			{ if(e.getStateChange()==1)
			     {textField_1.setVisible(false);
			     comboBox_1.setVisible(false);
					}
		}
		});

		ButtonGroup group=new ButtonGroup();
		group.add(rdbtn2);
		group.add(rdbtn1);
				
		JButton btnZoomOut = new JButton("-");
		btnZoomOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(zoomcont>0)
				{		zoomcont--;
				if(zoomcont==0)
				{zoomFactor=0.02;
				zoomFactor1=0.08;
				
				}
				 if(zoomcont==1)
				{zoomFactor=     0.016;
				zoomFactor1=      0.015;
				
				}
				 if(zoomcont==2)
				{zoomFactor=    0.005;
				zoomFactor1=  0.009;
				
				}
				 if(zoomcont==4||zoomcont==5||zoomcont==3)
				 {
					 zoomFactor=0.0005;    
						zoomFactor1=0.002;
						

				 }
			    if(zoomcont==7 ||zoomcont==6)
				{zoomFactor=0.001;     
				zoomFactor1=0.001;
				
				}
				if( (maxLat+zoomFactor)>(minLat-zoomFactor)  && (maxLong+zoomFactor1)>(minLong-zoomFactor1)){
					
				maxLat+=zoomFactor;
				minLat-=zoomFactor;
				maxLong+=zoomFactor1;
				minLong-=zoomFactor1;
				repaint();}
				}
			}
		});
		
        btnNavigate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			
				if(getselectedafterplacesearch  )
				{System.out.println(comboBox.getItemAt(comboBox.getSelectedIndex()));
				
					if(comboBox.getSelectedIndex()<search_idn1.size()){
						int ind=BinarySearch.BSearch(nodes, 0, nodes.size(), search_idn1.elementAt(comboBox.getSelectedIndex()));
						System.out.println(" lon= "+nodes.elementAt(ind).lon+" lat="+nodes.elementAt(ind).lat);
						lon_search1=nodes.elementAt(ind).lon;
						lat_search1=nodes.elementAt(ind).lat;
					     
						repaint();
					
					}
					
				}
				if(getselecctedafterdirectionsearch==true)
				{
				
					if(comboBox.getSelectedIndex()<search_idn1.size()){
						int ind=BinarySearch.BSearch(nodes, 0, nodes.size(), search_idn1.elementAt(comboBox.getSelectedIndex()));
						System.out.println(" lon= "+nodes.elementAt(ind).lon+" lat="+nodes.elementAt(ind).lat);
						lon_search1=nodes.elementAt(ind).lon;
						lat_search1=nodes.elementAt(ind).lat;
						repaint();
					}
				
					if(comboBox_1.getSelectedIndex()<search_idn2.size()){
						int ind=BinarySearch.BSearch(nodes, 0, nodes.size(), search_idn2.elementAt(comboBox_1.getSelectedIndex()));
						System.out.println(" lon= "+nodes.elementAt(ind).lon+" lat="+nodes.elementAt(ind).lat);
						lon_search2=nodes.elementAt(ind).lon;
						lat_search2=nodes.elementAt(ind).lat;
						repaint();
					}
						paint_way=true;		   	
					user_lon_src = lon_search1;
					user_lat_src = lat_search1;
					
					
			    	
			    	user_lon_dst =	lon_search2;
			    	user_lat_dst = lat_search2;
			    	   
			    	   
			    	   System.out.println(nodes.elementAt(src).id + " , " +nodes.elementAt(dest).id);
			   		
			   		
			    	   src=nearestNode(user_lat_src,user_lon_src,true);
			   		
			   		
			   		
			    	   dest=nearestNode(user_lat_dst,user_lon_dst,false);
			   		
			   		
			   		System.out.println(nodes.elementAt(src).id + " , " +nodes.elementAt(dest).id);
			   		
			       	dis_dij=dd.dij(nodes, ways, parent,src, dest);
			       	counter=0;
			   		if(dis_dij==Integer.MAX_VALUE)
			   			dis_dij=0;
			   	                                 //CHANCHUR CODE HERE :P			
			   		btnNewButton.setVisible(true);
			   		
					
					repaint();
				}
				
				
				
				
        	
        }});
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				str1=textField.getText();
				
				str2=textField_1.getText();
				btnNewButton.setVisible(false);
				
				if(rdbtn1.isSelected())
				{	getselectedafterplacesearch=false;
				getselecctedafterdirectionsearch=false;	
				str1=textField.getText();
				    str2=textField_1.getText();
                    
				    trial1.removeAllElements();
					search_idn1.removeAllElements();
					
					sb=new StringBuilder();
					sb.append(str1);
					
					tn.search1(sb,trial1, search_idn1);
					
					System.out.println("\n searching way=>");
					
					str4=str1;
					int i;
					int item=comboBox.getItemCount();
					for ( i=0;i<item;i++)
					{comboBox.removeItemAt(0);}
					
					for ( i=0;i<trial1.size();i++)
					 {comboBox.addItem(trial1.elementAt(i));}

                
					trial2.removeAllElements();
					search_idn2.removeAllElements();
					
					sb=new StringBuilder();
					sb.append(str2);
					
					tn.search1(sb,trial2, search_idn2);
					
					str5=str2;
					
					
					 item=comboBox_1.getItemCount();
					for ( i=0;i<item;i++)
					{comboBox_1.removeItemAt(0);}
					
					for ( i=0;i<trial2.size();i++)
					 {comboBox_1.addItem(trial2.elementAt(i));}

					getselecctedafterdirectionsearch=true;
					
				}
			
				if(rdbtn2.isSelected()  ){
					getselecctedafterdirectionsearch=false;	
					getselectedafterplacesearch=false;
					
					str3=str1;
					System.out.println(str3);
					trial1.removeAllElements();
					search_idn1.removeAllElements();
					
					sb=new StringBuilder();
					sb.append(str1);
					
					tn.search1(sb,trial1, search_idn1);
					
					System.out.println("\n searching way=>");
					
					
					int i;
					int item=comboBox.getItemCount();
					for ( i=0;i<item;i++)
					{comboBox.removeItemAt(0);}
					
					for ( i=0;i<trial1.size();i++)
					 {comboBox.addItem(trial1.elementAt(i));}
					getselectedafterplacesearch=true;
				}
								}});	
		
		
		
		add(btnZoomOut, "cell 21 1,growx,aligny top");
		
		
	
		 
		
	}	


    int CeilIndex(double lat) //return index of node nearest to given lat in nodes_sort
    {
    
    	int l=-1;
    	int r=nodes_sort.size();
    	while((r-l) > 1)
    	{
    		int m= (r+l)/2;
    		if(nodes_sort.elementAt(m).lat >= lat)
    			r=m;
    		else
    			l=m;
    		
    	}
    	return r;
    }
    
int nearestNode(double lat, double lon, boolean type)//returns nearest node index in nodes vector // type = true for source ,false for dest. 
	{
		int i=CeilIndex(lat);
		int j=i;
		int min_node1=0,min_node2=0;
		double min_dis = Double.MAX_VALUE;
		double lon1,lon2,lat1,lat2,loni=0,lati=0;
		double a1,a2,b1,b2,c1,c2;
		double dis=0;
		while(j<nodes_sort.size() )
		{
		
			lon1=nodes_sort.elementAt(j).lon;
			lat1=nodes_sort.elementAt(j).lat;
			dis=Dist.calculateDistance(lat, lon, lat1, lon1);
			
			if(dis<min_dis)
			{
				min_dis=dis;
				min_node1=BinarySearch.BSearch(nodes, 0, nodes.size(), nodes_sort.elementAt(j).id);
				
				if(type==true)
				{
					near_lon_src=lon1;
					near_lat_src=lat1;
					System.out.println("bb : "+loni + " , " + lati);
				}
				else
				{
					near_lon_dst=lon1;
					near_lat_dst=lat1;
				}
			}
			for(int k=0;k<nodes_sort.elementAt(j).adj.size();k++)
			{
				lon2=nodes.elementAt(nodes_sort.elementAt(j).adj.elementAt(k)).lon;
				lat2=nodes.elementAt(nodes_sort.elementAt(j).adj.elementAt(k)).lat;
				
				
				
				a1=lon2-lon1;
				b1=lat1-lat2;
				c1=(-a1*lat1)-(b1*lon1);
				a2=b1;
				b2=-a1;
				c2=-a2*lat - b2*lon;
				
				lati=(b1*c2-b2*c1)/(a1*b2-b1*a2);
				loni=(a2*c1-a1*c2)/(a1*b2-b1*a2);
				

				if((loni<=lon1 && loni>=lon2) || (loni>=lon1 && loni<=lon2)) 
				{
					dis=Dist.calculateDistance(lat, lon, lati, loni);
			if(dis<min_dis)
					{
						min_dis=dis;
						min_node1=BinarySearch.BSearch(nodes, 0, nodes.size(), nodes_sort.elementAt(j).id);
						min_node2=nodes_sort.elementAt(j).adj.elementAt(k);
						if(type==true)
						{
							near_lon_src=loni;
							near_lat_src=lati;
							System.out.println("bb : "+loni + " , " + lati);
						}
						else
						{
							near_lon_dst=loni;
							near_lat_dst=lati;
						}
					}
					
					
					
					
				}
			}
			j++;
		}
		j=i-1;
		while(j>=0)
		{
		
			lon1=nodes_sort.elementAt(j).lon;
			lat1=nodes_sort.elementAt(j).lat;
			
			dis=Dist.calculateDistance(lat, lon, lat1, lon1);
			
			if(dis<min_dis)
			{
				min_dis=dis;
				min_node1=BinarySearch.BSearch(nodes, 0, nodes.size(), nodes_sort.elementAt(j).id);
			
				if(type==true)
				{
					near_lon_src=lon1;
					near_lat_src=lat1;
					System.out.println("bb : "+loni + " , " + lati);
				}
				else
				{
					near_lon_dst=lon1;
					near_lat_dst=lat1;
				}
			}
			
			for(int k=0;k<nodes_sort.elementAt(j).adj.size();k++)
			{
				lon2=nodes.elementAt(nodes_sort.elementAt(j).adj.elementAt(k)).lon;
				lat2=nodes.elementAt(nodes_sort.elementAt(j).adj.elementAt(k)).lat;
				
				
				
				a1=lon2-lon1;
				b1=lat1-lat2;
				c1=(-a1*lat1)-(b1*lon1);
				a2=b1;
				b2=-a1;
				c2=-a2*lat - b2*lon;
				
				loni=b1*c2-b2*c2;
				lati=a2*c1-a1*c2;
				if((loni<=lon1 && loni>=lon2) || (loni>=lon1 && loni<=lon2)) 
				{
					dis=Dist.calculateDistance(lat, lon, lati, loni);
					if(dis<min_dis)
					{
						min_dis=dis;
						min_node1=BinarySearch.BSearch(nodes, 0, nodes.size(), nodes_sort.elementAt(j).id);
						min_node2=nodes_sort.elementAt(j).adj.elementAt(k);
						if(type==true)
						{
							near_lon_src=loni;
							near_lat_src=lati;
							System.out.println("bb : "+loni + " , " + lati);
						}
						else
						{
							near_lon_dst=loni;
							near_lat_dst=lati;
						}
					}
					
				}
			}
			j--;
		}
		
		return min_node1;
		
	}
    
	    
	
	void loadNodes() throws IOException
	{
	
	/****************************************************************/
		FileReader ff0=new FileReader("arjun.txt");
		BufferedReader fin0 =new BufferedReader(ff0);
		String line0;
		Double lon0, lat0;
		StringBuilder kk0= new StringBuilder(),vv0 = new StringBuilder();
		Long id0,id00;
		Node nn0=new Node();
		
		while((line0=fin0.readLine())!=null){
			
			StringTokenizer st0=new StringTokenizer(line0);
			id0=Long.parseLong(st0.nextToken());;
			nn0.id=id0;
			
		    lat0=Double.parseDouble(st0.nextToken());
			nn0.lat=lat0;
			if(lat0<minLat)
				minLat=lat0;
			if(lat0>maxLat)
				maxLat=lat0;
			
			lon0=Double.parseDouble(st0.nextToken());
			nn0.lon=lon0;
			if(lon0<minLong)
				minLong=lon0;
			if(lon0>maxLong)
				maxLong=lon0;
			
			
			if(st0.hasMoreTokens()){
				kk0=new StringBuilder();
				
				kk0.append(st0.nextToken());
				
				
				//System.out.println("kk="+kk0);
				
				nn0.k.addElement(kk0);
				vv0=new StringBuilder();
				vv0.append(st0.nextToken());
			
				while(st0.hasMoreTokens()){
				vv0.append(" ");
					vv0.append(st0.nextToken());
					}
				
				//System.out.println("vv="+vv0);
				nn0.v.addElement(vv0);
				
				if(("name").contentEquals(kk0)){
					
					tn.insert(vv0, id0);
				}
			}
	   	
			else 
			{
				sb=new StringBuilder();
				sb.append("-1");
				
				nn0.k.addElement(sb);
				
				sb=new StringBuilder();
				sb.append("-1");
				nn0.v.addElement(sb);

				
			}
			
				while((line0=fin0.readLine())!= null){
					StringTokenizer st00=new StringTokenizer(line0);
					id00=Long.parseLong(st00.nextToken());
						
					if(id0!=id00)
							{
							nodes.addElement(nn0);
							id0=id00;
							
							Node nn00=new Node();
							nn0=nn00;
							nn0.id=id00;
							
						    lat0=Double.parseDouble(st00.nextToken());
							nn0.lat=lat0;
							if(lat0<minLat)
								minLat=lat0;
							if(lat0>maxLat)
								maxLat=lat0;
							
							lon0=Double.parseDouble(st00.nextToken());
							nn0.lon=lon0;
							if(lon0<minLong)
								minLong=lon0;
							if(lon0>maxLong)
								maxLong=lon0;
							
							
								if(st00.hasMoreTokens()){
									kk0=new StringBuilder();
									kk0.append(st00.nextToken());
									
								//	System.out.println("kk="+kk0);
									nn0.k.addElement(kk0);
									vv0=new StringBuilder();
									vv0.append(st00.nextToken());
									
									while(st00.hasMoreTokens()){
									vv0.append(" ");
										vv0.append(st00.nextToken());
										}
									
									
								//	System.out.println("vv="+vv0);
									nn0.v.addElement(vv0);
									
									if(("name").contentEquals(kk0)){
										tn.insert(vv0, id00);
									}
									
								}
						   	
								else 
								{
									sb=new StringBuilder();
									sb.append("-1");
									nn0.k.addElement(sb);
									//System.out.print("kk=-1 ");
									
									sb=new StringBuilder();
									sb.append("-1");
									nn0.v.addElement(sb);
									//System.out.println("vv=-1");
									
									
									
								}
							continue;
							}
						
						id0=id00;
						lat0=Double.parseDouble(st00.nextToken());
						lon0=Double.parseDouble(st00.nextToken());
						if(st00.hasMoreTokens()){
							kk0=new StringBuilder();;
							kk0.append(st00.nextToken());
							//System.out.println("kk="+kk0);
							nn0.k.addElement(kk0);
							
							vv0=new StringBuilder();
							vv0.append(st00.nextToken());
							
							while(st00.hasMoreTokens()){
							vv0.append(" ");
								vv0.append(st00.nextToken());
								}
							
							//System.out.println("vv="+vv0);
							nn0.v.addElement(vv0);
							
							if(("name").contentEquals(kk0)){
								tn.insert(vv0, id0);
							}
							
						}
				   	
						else 
						{
							sb=new StringBuilder();
							sb.append("-1");
							nn0.k.addElement(sb);
							//System.out.print("kk=-1 ");

							sb=new StringBuilder();
							sb.append("-1");
							nn0.v.addElement(sb);
							//System.out.println("vv=-1");
							
							
						}
					
				}
				nodes.addElement(nn0);
				break;
				
			
		
		}
		
		
		
	/****************************************************************/	
		
		
		
		
		
		FileReader ff1=new FileReader("arjun1.txt");
		BufferedReader fin1 =new BufferedReader(ff1);
		String line1;
		StringBuilder kk1=new StringBuilder(),vv1=new StringBuilder();
		StringBuilder x=new StringBuilder();
		Long id1,id11,ref1;
		Way nn1=new Way();
		
		while((line1=fin1.readLine())!= null){
			StringTokenizer st1=new StringTokenizer(line1);
			
			id1=Long.parseLong(st1.nextToken());;
			
			nn1.id=id1;
			//System.out.println("id1="+id1 + " ");
			ref1=Long.parseLong(st1.nextToken());
			
			nn1.ref.addElement(ref1);
			
			
			while((line1=fin1.readLine())!= null ){
				StringTokenizer st11=new StringTokenizer(line1);
				
				id11=Long.parseLong(st11.nextToken());;
			
			//	System.out.println("id11= "+id11 + " id1="+id1);
				
				if(!id1.equals(id11)){
				//	System.out.println("arjun");
					
					ways.addElement(nn1);
					id1=id11;
					Way nn11=new Way();
					nn1=nn11;
					nn1.id=id1;
					ref1=Long.parseLong(st11.nextToken());
					nn1.ref.addElement(ref1);
					continue;
				}
				//System.out.println("arjun");
				
				id1=id11;
				nn1.id=id11;
				ref1=null;
				
				x=new StringBuilder();
				x.append(st11.nextToken());
				if(((int)(x.charAt(0)))>=48 && ((int)(x.charAt(0)))<=57 )
				{
					ref1=Long.parseLong(x.toString());
					nn1.ref.addElement(ref1);
				//	System.out.println("ref1= "+ref1);
					
				}
				else{
					sb=new StringBuilder();
					nn1.k.addElement(sb.append(x));
				//	System.out.println("*k= "+x);
					
					
					vv1=new StringBuilder();
					vv1.append(st11.nextToken());
					
					while(st11.hasMoreTokens()){
					vv1.append(" ");
						vv1.append(st11.nextToken());
					}
					
					nn1.v.addElement(vv1);
			//		System.out.println("*v= "+vv1 + " ");
					
					if(("name").contentEquals(x)){
						tw.insert(vv1, id11);
					}
				}
				
			
			}
			ways.addElement(nn1);
			
			break;
		
		}
		

		/****************************************************************/
		
		
		FileReader ff2=new FileReader("arjun2.txt");
		BufferedReader fin2 =new BufferedReader(ff2);
		String line2;
		StringBuilder kk2=new StringBuilder(),vv2=new StringBuilder(),x2=new StringBuilder();
		Long id2,id22, ref2  ;
		StringBuilder type2=new StringBuilder() ,  role2=new StringBuilder();
		
		//////////////////id   type   ref    role   k   v///////////
		Relation nn2=new Relation();
		
		while((line2=fin2.readLine())!= null){
			StringTokenizer st2=new StringTokenizer(line2);
			
			id2=Long.parseLong(st2.nextToken());;
			
			nn2.id=id2;
			//System.out.println("id2="+id2 + " ");
			sb=new StringBuilder();
			sb.append(st2.nextToken());
			type2=sb;
			ref2=Long.parseLong(st2.nextToken());
			nn2.type.addElement(type2);
		//	System.out.println("type="+type2 + " ");
			
			if(type2.equals("way"))
			{
				nn2.refw.addElement(ref2);
				if(st2.hasMoreTokens()){
					role2=new StringBuilder();
					role2.append(st2.nextToken());
					nn2.rolew.addElement(role2);
			
				}
				else{
					nn2.rolew.addElement(null);
				}
			}
			else if(type2.equals("node"))
			{
				nn2.refn.addElement(ref2);
				if(st2.hasMoreTokens()){
					role2=new StringBuilder();
					role2.append(st2.nextToken());
					
					nn2.rolen.addElement(role2);
			
				}
				else{
					nn2.rolen.addElement(null);
				}
			}
			else if(type2.equals("relation"))
			{
				nn2.refr.addElement(ref2);
				if(st2.hasMoreTokens()){
					role2=new StringBuilder();
					role2.append(st2.nextToken());
					nn2.roler.addElement(role2);
			
				}
				else{
					nn2.roler.addElement(null);
				}
			}
			
			while((line2=fin2.readLine())!= null ){
				StringTokenizer st22=new StringTokenizer(line2);
				
				id22=Long.parseLong(st22.nextToken());;
			
			//System.out.println("id22= "+id22 );
				
				if(!id2.equals(id22)){
				//	System.out.println("arjun");
					
					relations.addElement(nn2);
					id2=id22;
					Relation nn22=new Relation();
					nn2=nn22;
					nn2.id=id2;
					type2=new StringBuilder();
					type2.append(st22.nextToken());
							
					ref2=Long.parseLong(st22.nextToken());
					nn2.type.addElement(type2);
					
					if(type2.equals("way"))
					{
						nn2.refw.addElement(ref2);
						if(st22.hasMoreTokens()){
							role2=new StringBuilder();
							role2.append(st22.nextToken());
							
							nn2.rolew.addElement(role2);
					
						}
						else{
							nn2.rolew.addElement(null);
						}
					}
					else if(type2.equals("node"))
					{
						nn2.refn.addElement(ref2);
						if(st22.hasMoreTokens()){
							role2=new StringBuilder();
							role2.append(st22.nextToken());
							
							nn2.rolen.addElement(role2);
					
						}
						else{
							nn2.rolen.addElement(null);
						}
					}
					else if(type2.equals("relation"))
					{
						nn2.refr.addElement(ref2);
						if(st22.hasMoreTokens()){
						
							role2=new StringBuilder();
							role2.append(st22.nextToken());
							
							nn2.roler.addElement(role2);
					
						}
						else{
							nn2.roler.addElement(null);
						}
					}
					
					
					
					////////////
					continue;
				}
				//System.out.println("arjun");
				
				id2=id22;
				nn2.id=id22;
				ref2=null;
				
				x2=new StringBuilder();
				x2.append(st22.nextToken());
				
				if(x2.equals("node")|| x2.equals("way") || x2.equals("relation"))
				{
				//	ref2=Long.parseLong(x);
				//	nn2.ref.addElement(ref1);
				//	System.out.println("ref1= "+ref1);
					/////////////////
					
					//type2=st2.nextToken();  x2
					ref2=Long.parseLong(st22.nextToken());
					nn2.type.addElement(x2);
					
					if(x2.equals("way"))
					{
						nn2.refw.addElement(ref2);
						if(st22.hasMoreTokens()){
							role2=new StringBuilder();
							role2.append(st22.nextToken());
							
							nn2.rolew.addElement(role2);
					
						}
						else{
							nn2.rolew.addElement(null);
						}
					}
					else if(x2.equals("node"))
					{
						nn2.refn.addElement(ref2);
						if(st22.hasMoreTokens()){
							role2=new StringBuilder();
							role2.append(st22.nextToken());
							
							nn2.rolen.addElement(role2);
					
						}
						else{
							nn2.rolen.addElement(null);
						}
					}
					else if(x2.equals("relation"))
					{
						nn2.refr.addElement(ref2);
						if(st22.hasMoreTokens()){

							role2=new StringBuilder();
							role2.append(st22.nextToken());
							
							nn2.roler.addElement(role2);
					
						}
						else{
							nn2.roler.addElement(null);
						}
					}
					
					
					///////////
					
					
				}
				else{
					
					nn2.k.addElement(x2);
				//	System.out.println("*k= "+x);
					
					vv2=new StringBuilder();
					vv2.append(st22.nextToken());
					
					
					while(st22.hasMoreTokens())
					{
						vv2=new StringBuilder();
						vv2.append(" ");
						vv2.append(st22.nextToken());
						
					}
						
					
					nn2.v.addElement(vv2);
			//		System.out.println("*v= "+vv1 + " ");
					
				}
				
			
			}
			relations.addElement(nn2);
			
			break;
		
		}		
		
		

		
		int kk,flag=0,ind1 = 0, ind2=0;
		
		valid_nodes = new int[nodes.size()];
		int max=Integer.MIN_VALUE;
		int color_nodes[]= new int[nodes.size()];
		
		BinarySearch b=new BinarySearch();
		Dist d=new Dist();
		for(int i=0;i<nodes.size();i++)
		{
			valid_nodes[i]=i;
			color_nodes[i]=1;
		}
		
		String ss1="highway";
		for(int i=0;i<ways.size();i++){
			flag=0;
			for( kk=0; kk< ways.elementAt(i).k.size();kk++){
				//StringBuffer ss=new StringBuffer();
				//ss.append("hihway");
				if(ss1.contentEquals(ways.elementAt(i).k.elementAt(kk))){
					flag=1;
					//System.out.println("here222" );
					break;
				}
			}
			if(flag==0)
			{
				 //System.out.println("here" );
					
				continue;
			}
			
			
			 	StringBuilder s=ways.elementAt(i).v.elementAt(kk);
				if(("motorway".contentEquals(s)) || "trunk".contentEquals(s) || "primary".contentEquals(s) || "secondary".contentEquals(s) ||
						"tertiary".contentEquals(s) || "unclassified".contentEquals(s) || "residential".contentEquals(s) || "service".contentEquals(s) ||
						"motorway_link".contentEquals(s) || "trunk_link".contentEquals(s) || "living_street".contentEquals(s) || "pedestrian".contentEquals(s) ||
								"track".contentEquals(s) || "primary_link".contentEquals(s) || "secondary_link".contentEquals(s) || "tertiary_link".contentEquals(s) || "bridleway".contentEquals(s)|| "path".contentEquals(s) )
					{
			
			
			if(ways.elementAt(i).ref.size()>0)
			ind1=b.BSearch(nodes, 0, nodes.size() , ways.elementAt(i).ref.elementAt(0));
      	    
			for(int j=1; j< ways.elementAt(i).ref.size();j++){
				ind2=b.BSearch(nodes, 0, nodes.size() , ways.elementAt(i).ref.elementAt(j));
				
				double dis = d.calculateDistance(nodes.elementAt(ind1).lat,nodes.elementAt(ind1).lon,nodes.elementAt(ind2).lat,nodes.elementAt(ind2).lon);
				nodes.elementAt(ind1).type=true;
				nodes.elementAt(ind2).type=true;
				
//Node Validation	// Updated by chanchur
				
				if(valid_nodes[ind1] != valid_nodes[ind2])
				{
					int temp=valid_nodes[ind2];
					valid_nodes[ind2]=valid_nodes[ind1];
					color_nodes[temp]--;
					color_nodes[valid_nodes[ind1]]++;
					if(color_nodes[temp]>0)
					{
						for(int it=0;it<nodes.size();it++)
						{
							if(valid_nodes[it] == temp)
								valid_nodes[it]=valid_nodes[ind1];
						}
						color_nodes[valid_nodes[ind1]]+=color_nodes[temp];
						color_nodes[temp]=0;
					}
					if(color_nodes[valid_nodes[ind1]]>max)
					{
						max=color_nodes[valid_nodes[ind1]];
						max_color=valid_nodes[ind1];
					}
				}
				
				if(dis==0.0)
				{
					 System.out.println(""+nodes.elementAt(ind1).lat+" " +nodes.elementAt(ind1).lat+" \n"+nodes.elementAt(ind2).lat+" "+nodes.elementAt(ind2).lat+ "\n\n");
					continue;
				}
			
				nodes.elementAt(ind1).adj.addElement(ind2);
				nodes.elementAt(ind1).weight.addElement(dis);
				
				
				StringBuilder ra=null ,oneway_ans=null;
				int jun=-1;
				for(int ii=0; ii<ways.elementAt(i).k.size();ii++){
					if(("junction").contentEquals(ways.elementAt(i).k.elementAt(ii)))
					 jun=ii;
				}
				if(jun!=-1)
					ra = ways.elementAt(i).v.elementAt(jun);
				
				int ow=-1;
				for(int ii=0; ii<ways.elementAt(i).k.size();ii++){
					if(("oneway").contentEquals(ways.elementAt(i).k.elementAt(ii)))
					 ow=ii;
				}	
				
				if(ow!=-1)
					oneway_ans=ways.elementAt(i).v.elementAt(ow);
				
			

				if((jun!=-1 && ("roundabout").contentEquals(ra) || (ow!=-1 && ("yes").contentEquals(oneway_ans) )  ))
				{
					
				}
				else
				{
					nodes.elementAt(ind2).adj.addElement(ind1);
					nodes.elementAt(ind2).weight.addElement(dis);
				}
			
			
				ind1=ind2;
	          }}
			
	     }
	         
		
		
				
		nodeSort();
			
    	
	}
	 
	/************************************************************************************/
	
		public void paint(Graphics g) {
		h=this.getHeight();
		w=this.getWidth();
        super.paint(g);
        
    	
        
        BinarySearch b=new BinarySearch();
        int index1, index2,refsize;
        Double lon1, lat1, lat2, lon2;
        Color col=Color.GREEN;
        ((Graphics2D) g).setStroke(new BasicStroke(1));
        
        
        for(int i=0;i<nodes.size();i++){
        	lon1=nodes.elementAt(i).lon;
    		lat1=nodes.elementAt(i).lat;
    			
    		int locX1 = (int) ((lon1-minLong)*(this.getWidth())/(maxLong-minLong));
        	int locY1 = (int) ((maxLat-(lat1))*(this.getHeight())/(maxLat-minLat));

        	
        	for(int j=0; j< nodes.elementAt(i).adj.size();j++){
        		
        		int ind=nodes.elementAt(i).adj.elementAt(j);
        		lon2=nodes.elementAt(ind).lon;
        		lat2=nodes.elementAt(ind).lat;
        	
        		
        		int locX2 = (int) ((lon2-minLong)*(this.getWidth())/(maxLong-minLong));
            	int locY2 = (int) ((maxLat-(lat2))*(this.getHeight())/(maxLat-minLat));
            	
            	//why this...
            	//updated by chanchur
            	
        		if(valid_nodes[i]==max_color)
        			g.setColor(col);
        		else
        			g.setColor(Color.blue);
        	
        		if(locX1<this.getWidth()-100 && locX2<this.getWidth()-100)
        		g.drawLine(locX1+10, locY1+10, locX2+10, locY2+10);
        		 
        		
        	}    
        }
            
        for(int i=0;i<nodes.size();i++){
        	//if((nodes.elementAt(i).lat!=77.02  && nodes.elementAt(i).lat!= ) || (nodes.elementAt(i).lat!=  && nodes.elementAt(i).lat!= ) )
        		//continue;
        	int locX = (int) ((((Node) nodes.elementAt(i)).lon-minLong)*(this.getWidth())/(maxLong-minLong));
        	int locY = (int) (((maxLat-((Node) nodes.elementAt(i)).lat))*(this.getHeight())/(maxLat-minLat));;
        	g.setColor(Color.DARK_GRAY);
        // draw a line (there is no drawPoint..)
        	//if(locX<this.getWidth()-100)
        	//if(i==src || i==dest)
        	//	g.drawLine(locX+10, locY+10, locX+10, locY+10);
        }
        // why this..
        int src1=0,dest1;
        dest1=parent[dest];
        if (paint_way==true)
        	counter++;
        if(paint_way==true && src !=dest )
        {
        	int temp=dest;
        	dest=parent[dest];
	        while(true){
	        	if(parent[dest]==-1)
	        		break;
	        	else if(parent[parent[dest]]==-1)
	        	{
	        		src1=dest;
	        		src=parent[dest];
	        		break;
	        	}
	        	lon1=nodes.elementAt(dest).lon;
	        lat1=nodes.elementAt(dest).lat;
	        
	        lon2=nodes.elementAt(parent[dest]).lon;
	        lat2=nodes.elementAt(parent[dest]).lat;
	        
	        int locX1 = (int) ((lon1-minLong)*(this.getWidth())/(maxLong-minLong));
	    	int locY1 = (int) ((maxLat-(lat1))*(this.getHeight())/(maxLat-minLat));
	
	    	int locX2 = (int) ((lon2-minLong)*(this.getWidth())/(maxLong-minLong));
	    	int locY2 = (int) ((maxLat-(lat2))*(this.getHeight())/(maxLat-minLat));
	    	((Graphics2D) g).setStroke(new BasicStroke(2));
	    
	    	 g.setColor(Color.BLUE);
		
			//if(locX1<this.getWidth()-100 && locX2<this.getWidth()-100){
			g.drawLine(locX1+10, locY1+10, locX2+10, locY2+10);
				
			dest=parent[dest];
			
	        }
	        dest=temp;
	        //source
	        dis_final=0;
	        lon1=nodes.elementAt(src).lon;
	        lat1=nodes.elementAt(src).lat;
	        //System.out.println("hh : "+user_lat_src +" , "+user_lon_src);
	        //System.out.println("hh : "+near_lat_src +" , "+near_lon_src);
	        lon2=nodes.elementAt(src1).lon;
	        lat2=nodes.elementAt(src1).lat;
	        
	        if(((near_lat_src>=lat1 && near_lat_src<=lat2) || (near_lat_src>=lat2 && near_lat_src<=lat1)) && ((near_lon_src>=lon1 && near_lon_src<=lon2) || (near_lon_src>=lon2 && near_lon_src<=lon1)))
	        {
	        	int locX1 = (int) ((near_lon_src-minLong)*(this.getWidth())/(maxLong-minLong));
		    	int locY1 = (int) ((maxLat-(near_lat_src))*(this.getHeight())/(maxLat-minLat));
		
		    	int locX2 = (int) ((lon2-minLong)*(this.getWidth())/(maxLong-minLong));
		    	int locY2 = (int) ((maxLat-(lat2))*(this.getHeight())/(maxLat-minLat));
		    	//g.set
		    	//g.setColor(Color.BLACK);
		    	g.drawLine(locX1+10, locY1+10, locX2+10, locY2+10);
		    	dis_final-=Dist.calculateDistance(near_lat_src, near_lon_src, lat1, lon1);
	        }
	        else
	        {
	        	int locX1 = (int) ((near_lon_src-minLong)*(this.getWidth())/(maxLong-minLong));
		    	int locY1 = (int) ((maxLat-(near_lat_src))*(this.getHeight())/(maxLat-minLat));
		
		    	int locX2 = (int) ((lon1-minLong)*(this.getWidth())/(maxLong-minLong));
		    	int locY2 = (int) ((maxLat-(lat1))*(this.getHeight())/(maxLat-minLat));
		    	//g.setColor(Color.BLACK);
		    	g.drawLine(locX1+10, locY1+10, locX2+10, locY2+10);
		    	locX1 = (int) ((lon2-minLong)*(this.getWidth())/(maxLong-minLong));
		    	locY1 = (int) ((maxLat-(lat2))*(this.getHeight())/(maxLat-minLat));
		    	g.drawLine(locX1+10, locY1+10, locX2+10, locY2+10);
		    	dis_final+=Dist.calculateDistance(near_lat_src, near_lon_src, lat1, lon1);
	        }
	        
	        
	        //destination
	        
	        lon1=nodes.elementAt(dest).lon;
	        lat1=nodes.elementAt(dest).lat;
	        
	        lon2=nodes.elementAt(dest1).lon;
	        lat2=nodes.elementAt(dest1).lat;
	        
	        if(((near_lat_dst>=lat1 && near_lat_dst<=lat2) || (near_lat_dst>=lat2 && near_lat_dst<=lat1)) || ((near_lon_dst>=lon1 && near_lon_dst<=lon2) || (near_lon_dst>=lon2 && near_lon_dst<=lon1)))
	        {
	        	
	        	int locX1 = (int) ((near_lon_dst-minLong)*(this.getWidth())/(maxLong-minLong));
		    	int locY1 = (int) ((maxLat-(near_lat_dst))*(this.getHeight())/(maxLat-minLat));
		
		    	int locX2 = (int) ((lon2-minLong)*(this.getWidth())/(maxLong-minLong));
		    	int locY2 = (int) ((maxLat-(lat2))*(this.getHeight())/(maxLat-minLat));
		    	//g.setColor(Color.BLACK);
		    	dis_final-=Dist.calculateDistance(near_lat_dst, near_lon_dst, lat1, lon1);
		    	g.drawLine(locX1+10, locY1+10, locX2+10, locY2+10);
	        }
	        else
	        {
	        	int locX1 = (int) ((near_lon_dst-minLong)*(this.getWidth())/(maxLong-minLong));
		    	int locY1 = (int) ((maxLat-(near_lat_dst))*(this.getHeight())/(maxLat-minLat));
		
		    	int locX2 = (int) ((lon1-minLong)*(this.getWidth())/(maxLong-minLong));
		    	int locY2 = (int) ((maxLat-(lat1))*(this.getHeight())/(maxLat-minLat));
		    	//g.setColor(Color.BLACK);
		    	g.drawLine(locX1+10, locY1+10, locX2+10, locY2+10);
		    	locX1 = (int) ((lon2-minLong)*(this.getWidth())/(maxLong-minLong));
		    	locY1 = (int) ((maxLat-(lat2))*(this.getHeight())/(maxLat-minLat));
		    	g.drawLine(locX1+10, locY1+10, locX2+10, locY2+10);
		    	dis_final+=Dist.calculateDistance(near_lat_dst, near_lon_dst, lat1, lon1);
	        }
	        
        }
       
       // g.drawLine(a1+10, a2+10, a1+10, a2+10);
        
        
   		if(paint_way==true && counter==1)
        {
   			dis_dij+=dis_final;
   			Double ans = new BigDecimal(dis_dij).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
   			btnNewButton.setText("Distance="+ans+" km");
   		}
  
     for(int i=0;i<ways.size();i++){
        
    	 
    	 
    	 int j=0;
        	index1=b.BSearch(nodes, 0, nodes.size() , ways.elementAt(i).ref.elementAt(j));
        	//System.out.println("\nlon="+nodes.elementAt(index1).lon+ "  lat="+ nodes.elementAt(index1).lat);

        	if(index1==-1)
        		{//System.out.println(" not found1 ="+ways.elementAt(i).ref.elementAt(j) );
        			continue;
        		}	
        	
        	//System.out.println(" found arieal ");
        	lon1=nodes.elementAt(index1).lon;
        	lat1=nodes.elementAt(index1).lat;
        	refsize=ways.elementAt(i).ref.size();
        	//System.out.println("\nat 0="+ways.elementAt(i).ref.elementAt(0));

//        	System.out.println("at last="+ways.elementAt(i).ref.elementAt(refsize-1));
            	
        	
        	for( j=1;j<ways.elementAt(i).ref.size();j++){
        		index2=b.BSearch(nodes, 0, nodes.size() , ways.elementAt(i).ref.elementAt(j));
        		
        		if(index2==-1)
            		{System.out.println(" not found2 "+ways.elementAt(i).ref.elementAt(j));
            		continue;
            		}
        		//System.out.println("  found2 ");
        		lon2=nodes.elementAt(index2).lon;
            	lat2=nodes.elementAt(index2).lat;
            	
        		
        		int locX1 = (int) ((lon1-minLong)*(this.getWidth())/(maxLong-minLong));
            	int locY1 = (int) ((maxLat-(lat1))*(this.getHeight())/(maxLat-minLat));

        		int locX2 = (int) ((lon2-minLong)*(this.getWidth())/(maxLong-minLong));
            	int locY2 = (int) ((maxLat-(lat2))*(this.getHeight())/(maxLat-minLat));
            	
            	if(ways.elementAt(i).ref.elementAt(0).equals(ways.elementAt(i).ref.elementAt(refsize-1))){
            		//System.out.println("\ntrue");
            				g.setColor(Color.RED);
                //	if(locX1<this.getWidth()-100 && locX2<this.getWidth()-100)
              //  		g.drawLine(locX1+10, locY1+10, locX2+10, locY2+10);
                	
            		
            	}	
            	else{
            		
        				col=Color.BLACK;
            		
            		g.setColor(col);
           // 	if(locX1<this.getWidth()-100 && locX2<this.getWidth()-100)
            //		g.drawLine(locX1+10, locY1+10, locX2+10, locY2+10);
            	}
        	index1=index2;
        	lon1=lon2;
        	lat1=lat2;
        	
        	}
                
        	
        	
        
            
     }
     
		}
     
     

	public void nodeSort()//Sort Nodes in nodes_sort vector on basis of lat // called in loadNodes function after creating adj list
	{
		
		//updated by chanchur
		for(int i=0;i<nodes.size();i++)
		{
			if(nodes.elementAt(i).type==true && valid_nodes[i]==max_color)
			{
				nodes_sort.addElement(nodes.elementAt(i));
			}
		}
		Collections.sort(nodes_sort);
	}
	
	public int count1=0;
	int a1,a2;
public void mouseClicked(MouseEvent e) {
		
	if(switch1==true)
	{        btnNewButton.setVisible(false);
	count1++;
    	//drawPoint = new Point(e.getPoint());
       if(count1%2==1)
       {
    	   paint_way=false;
    	   drawPoint = new Point(e.getPoint());
    	   
    	   
    	   //int locX1 = (int) ((lon1-minLong)*(this.getWidth())/(maxLong-minLong));
	    	//int locY1 = (int) ((maxLat-(lat1))*(this.getHeight())/(maxLat-minLat));
	
	
    	   
    	   
       }
       else if(count1%2==0 && count1!=0)
	{btnNewButton.setVisible(false);
    	   drawPoint1 = new Point(e.getPoint());
    	   paint_way=true;
    	   
	}
       user_lon_src = (double) ((drawPoint.x-10)*((maxLong-minLong)/this.getWidth()))+minLong;
		user_lat_src = maxLat - (double) ((drawPoint.y-10)*((maxLat-minLat)/this.getHeight()));
		
		user_lon_dst = (double) ((drawPoint1.x-10)*((maxLong-minLong)/this.getWidth()))+minLong;
    	user_lat_dst = maxLat - (double) ((drawPoint1.y-10)*((maxLat-minLat)/this.getHeight()));
    	
	if(paint_way==true)
	{
		user_lon_src = (double) ((drawPoint.x-10)*((maxLong-minLong)/this.getWidth()))+minLong;
		user_lat_src = maxLat - (double) ((drawPoint.y-10)*((maxLat-minLat)/this.getHeight()));
		
		a1 = (int) ((int) ((user_lon_src)-minLong)*(this.getWidth())/(maxLong-minLong));
    	a2 = (int) ((maxLat-user_lat_src)*(this.getHeight())/(maxLat-minLat));
	
    	
    	user_lon_dst = (double) ((drawPoint1.x-10)*((maxLong-minLong)/this.getWidth()))+minLong;
    	user_lat_dst = maxLat - (double) ((drawPoint1.y-10)*((maxLat-minLat)/this.getHeight()));
    	   
    	   
    	   System.out.println(nodes.elementAt(src).id + " , " +nodes.elementAt(dest).id);
   		
   		
    	   src=nearestNode(user_lat_src,user_lon_src,true);
   		
   		
   		
    	   dest=nearestNode(user_lat_dst,user_lon_dst,false);
   		
   		
   		System.out.println(nodes.elementAt(src).id + " , " +nodes.elementAt(dest).id);
   		
       	dis_dij=dd.dij(nodes, ways, parent,src, dest);
       	counter=0;
   		if(dis_dij==Integer.MAX_VALUE)
   			dis_dij=0;
   		
   		
   		btnNewButton.setVisible(true);
   		//dis_dij.setPrecision
//   		Double ans = new BigDecimal(dis_dij).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	//	btnNewButton.setText("Distance="+ans+" km");
	
	}
    	   repaint();
	
	}
	}	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x=1, y=30;
        Graphics2D g2d = (Graphics2D) g.create();
        
        if (drawPoint != null && switch1==true) {
        	
        	
      
    		
        	 int locXs = (int) ((user_lon_src-minLong)*(this.getWidth())/(maxLong-minLong));
           	int locYs = (int) ((maxLat-(user_lat_src))*(this.getHeight())/(maxLat-minLat));
             	
            g2d.drawImage(src_marker, locXs-x, locYs-y, this);
            //g2d.drawImage(image, drawPoint.x-18, drawPoint.y-27, this);   
        }
        if (drawPoint1 != null && switch1==true) {
        	 int locXs = (int) ((user_lon_dst-minLong)*(this.getWidth())/(maxLong-minLong));
            	int locYs = (int) ((maxLat-(user_lat_dst))*(this.getHeight())/(maxLat-minLat));
             
        	
            g2d.drawImage(dst_marker, locXs-x, locYs-y, this);
            //g2d.drawImage(image, drawPoint.x-18, drawPoint.y-27, this);   
        }
        int locXs = (int) ((lon_search1-minLong)*(this.getWidth())/(maxLong-minLong));
    	int locYs = (int) ((maxLat-(lat_search1))*(this.getHeight())/(maxLat-minLat));
        if(getselectedafterplacesearch  && switch1==false)
    	{       	
        	
        	g2d.drawImage(src_marker, locXs-x, locYs-y, this);
    	}
    	
        if(getselecctedafterdirectionsearch  && switch1==false)
        {
        	    locXs = (int) ((lon_search1-minLong)*(this.getWidth())/(maxLong-minLong));
           	 locYs = (int) ((maxLat-(lat_search1))*(this.getHeight())/(maxLat-minLat));
           	g2d.drawImage(src_marker, locXs-x, locYs-y, this);
        	
            locXs = (int) ((lon_search2-minLong)*(this.getWidth())/(maxLong-minLong));
          	 locYs = (int) ((maxLat-(lat_search2))*(this.getHeight())/(maxLat-minLat));
          	g2d.drawImage(dst_marker , locXs-x, locYs-y, this);
       	
        	
        	
        	
        }	
        g2d.dispose();
    }
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}