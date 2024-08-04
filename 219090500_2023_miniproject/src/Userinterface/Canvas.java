package Userinterface;

//import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
//import java.util.ArrayList;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;
import institution.Institution;


public class Canvas extends javafx.scene.canvas.Canvas{
	
	public final int Wsize = 1000; //width size of canvas
	public final int Hsize = 500; //height of canvas
    private GraphicsContext GC;
    Image image; //image for canvas background
   
    
    
	public Canvas()
	{
		super();
		setCanvas();// calling instantiations
		
	}
	
	private void setCanvas()
	{
	//instantiating the size of canvas and and graphic context
		setWidth(Wsize); 
		setHeight(Hsize);
		GC = getGraphicsContext2D();
		GC.setFill(Color.WHITE);
		GC.fillRect(0, 0, Wsize, Hsize);
		//setting image as back ground
		image = new Image("C:\\Users\\27837\\eclipse-workspace\\219090500_2023_miniproject\\images\\images_hospital.PNG");
		GC.drawImage(image, 0,0,Wsize,Hsize); //drawing the image as background
		 		 	 
	}
	/**
	 * draw function which draws the edges and vertics of graph
	 * @param graph
	 */
	
	   public void Draw(Graph<Institution> graph) 
	   {
	
		   for(Edge<Institution>edge : graph.getEdges())
		   {
			   //variables to calc distance
			   double fromX1;
		       double fromY1;
		       double toX2;
		       double toY2 ;
		       int distance = 0;
		       
				fromX1 =  edge.getFromVertex().getValue().getX(); 
			    fromY1 =  edge.getFromVertex().getValue().getY();
				toX2   =  edge.getToVertex().getValue().getX();
				toY2   =  edge.getToVertex().getValue().getY();
				
			  double diffX =  fromX1 -toX2;
			  double diffy = fromY1-toY2;			   
			  double finalAns= Math.sqrt( Math.pow(diffX,2) + Math.pow(diffy,2));//distance in double variable
			  
			  distance = (int)finalAns;//upcasting the distance to be integer value
			  Drawedges(edge, distance); // call the draw function 
			 
		   }
		   //looping through all the vertices that exist in the graph 
			
		   for (Vertex<Institution>vert : graph.getVertices())
		   {
			//drawing the vertices
			NodeAnatomy(vert.getValue());
		           
	       }
		   
	   }
	   /**
	    * function that draws the shape and of the edge and displays the distance in the middle
	    * @param edge
	    * @param distance
	    */
	   
	   private void Drawedges(Edge<Institution> edge, int distance)
	   {
		   
	    	double fromX1;
	    	double fromY1;
	    	double toX2;
	    	double toY2;
	    	
		    fromX1 =  edge.getFromVertex().getValue().getX()+15; 
		    fromY1 =  edge.getFromVertex().getValue().getY()+15;
			toX2   =  edge.getToVertex().getValue().getX()+6;
			toY2   =  edge.getToVertex().getValue().getY()+6;
			
			//drawing the line edge	
		    GC.setFill(Color.BLACK);  
			GC.strokeLine(fromX1, fromY1, toX2, toY2);
			
			//getting the midpoint of line				
			double midx =(fromX1 +toX2)/2;
			double midy = (fromY1 + toY2)/2;
			
			//writing the distance in the middle
			String d = Integer.toString(distance);
			GC.setFill(Color.DARKGREEN);
			GC.strokeText("distance: "+d +"km", midx, midy);
			
	   }
	   
	   
     /**
      * method that creates the shape of vertex
      * @param institution
      */
	private void NodeAnatomy(Institution institution)
	{
		String Name = institution.getName();
		String Type = institution.getType();
		//setting the display of name and type and shape of the vertex(institution)
			GC.setFill(Color.DARKGREEN);
			GC.strokeText("Name: " +Name, institution.getX(), institution.getY()-15);
			GC.setFill(Color.DARKGREEN);
			GC.strokeText("type: "+ Type, institution.getX(),institution.getY()-5);
			GC.setFill(Color.DARKGREEN);
			GC.fillOval(institution.getX()+1, institution.getY()+1, 25, 25);
			
			
	  }
	
}