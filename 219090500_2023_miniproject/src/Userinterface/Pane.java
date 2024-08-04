package Userinterface;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;
import institution.Institution;
import javafx.scene.text.Font;


public class Pane extends javafx.scene.layout.StackPane{
	
	private Button btnCreateUser; // button to create each institution node
	private Button btnConnectUsers; //button to connect vertices together
	TextArea txt;                    // text area that gives information about an established connection
	private Label  lblNameofuser;    // label next to institution textfield
	private TextField txtNameofuser; // institution name text field
	
	private Label  lblDepttype;      // label next to type of institution textfield
	private TextField txtDepttype;   // textfield to enter the type of institution
	
	private Label  lblenterXPosition; //label next the x position of textfield 
	private TextField txtXPosition;   // textfield to enter the x position of a vertex
	
	private Label  lblenterYPosition; //label next to the y position of textfield
	private TextField txtYPosition;   // textfield to enter the y position of a node 
	
	private VBox Vbox;                // Vbox that contains all the labels, textfields buttons and textarea
	private GridPane Gpane;           // gridpane to position the Vbox and the canvas 
	private BorderPane bpane;         // boarder pane containing gridpane and displaying set up of User interface
	

	private Canvas Canvas;            // has the canvas class that draws the canvas and graph
	
	
	private Graph<Institution> graph; 
	private Edge<Institution>_edges;
	
	/**
	 * constructor that collects and instantiate UI, UI nodes and algorithms
	 */
	public Pane()
	{
		GUIinstantiation(); //button instantiation
		ButtonAction();     // button code algorithm
         
	}
	
	/**
	 * this is a method to calculate the traveling cost between two vertices
	 * it first calculates the distance amongst the two vertices, then multiples the distance by 
	 * the cost per hour rate
	 * @param institution1
	 * @param institution2
	 * @param cost
	 * @return
	 */
	public int Cost(Vertex<Institution> institution1, Vertex<Institution> institution2, double cost)
	{
		double fromX1; //x position of the first vertex
	    double fromY1; // y position of the second vertex
	    double toX2;   // x position of the second vertex
	    double toY2 ;   // y position of the second position vertex
	    int distance = 0; // distance between the vertices
	    double kmperhour = 5; // rate of travel
	   
	       
			fromX1 =  institution1.getValue().getX(); 
		    fromY1 =  institution1.getValue().getY();
		    
			toX2   =  institution2.getValue().getX();
			toY2   =  institution2.getValue().getX();
			
		  double diffX =  fromX1 -toX2; //
		  double diffy = fromY1-toY2;			   
		  double finalAns= Math.sqrt( Math.pow(diffX,2) + Math.pow(diffy,2)); // calculate the distance
		  
		  distance = (int)finalAns; //upcast the distance to being an integer
		  
		  cost = distance * kmperhour; // calculates the traveling cost
		  
	    return (int)cost;
	}
	/**
	 * method to create a connection amongst two vertices
	 * @param name1
	 * @param name2
	 */
	private void CreateEdges(String name1, String name2)
	{
		Vertex<Institution> institution1 = null; //represent first vertex entered by the user
		Vertex<Institution> institution2 = null; //represent second vertex entered by the user
		
		int cost = 0;
         // looping through all the existing vertices to match the first and second institution vertices
    	for(Vertex<Institution> v : graph.getVertices())
    	{
    		
    		if(v .getValue().getName().equals(name1)) // takes in the name and searches for matching vertices
    		{
    			institution1 =  v; // if names match the vertices are equal
    		}
    		
    		if(v.getValue().getName().equals(name2))
    		{
    			institution2 = v; // if names match the vertices are equal
    		}
    		
    	    }
    	
    	//conditional statement for empty name vertices enetered
    	if(institution1.getValue().getName().isEmpty() ||institution1.getValue().getName().isEmpty())
		{
			institution1= null;
			institution2 = null;
			//error if there is no name enetered
			JOptionPane.showMessageDialog(null, "Invalid Institution name(s)");
		}
		 // conditional statement if the institutions are not empty
		else if(institution1!= null && institution1!= null)
		{
		
			_edges = new Edge<>(cost,institution1,institution2);// edge is created if the institutions are not empty
			institution1.addEdge(_edges); // first vertex is notified about the connection edge
			institution2.addEdge(_edges); //second vertex is notified about the connection edge
    		graph.getEdges().add(_edges); // graph adds the edge being created
    		Canvas.Draw(graph); //the edge is being drawn on the canvas
    		
    		
    		String msg = "\nHas connected successfully to:"; //message that details the connection of two vertices
    		cost = 0 + Cost(institution1,institution2,cost); //cost of each connection
    		 String status=""; // cost status displayed for each connection
    		 //conditional statement  which categorizes the types of cost statuses
    		 
    		 if(cost>=0 && cost <=1500) // Category for cheap travelling cost
    		 {
    			status = "cheap" ;
    		 } else if (cost > 1500 && cost<=3500 ) //category for affordable cost
    		 {
    			 status = "affordable";
    			 
    		 }else if(cost > 3500) // category for a expensive cost
    		 {
    			 status = "expensive";
    		 }
    		 
    		// displays connection information of the two nodes being connected
			txt.appendText(" " 
					+"\n******************************************"
					+"\nname: "+institution1.getValue().getName()
					+"\ntype: "+institution1.getValue().getType()
					+"\n                                        "
					+ msg
					+"\n                                        "
					+"\nName: "+institution2.getValue().getName()
					+"\ntype: "+institution2.getValue().getType()
					+"\n                                        "
					+"\nThe travelling distribution\n"
					+ "cost between the two "
					+ "health institution is: R"+ cost
					+"\nStatus: "+status
					+"\n******************************************"
					+"                               ");
			
		}
    		
	}
	/**
	 * method for all the lambda actions 
	 */
	private void ButtonAction()
	{
		// button to create and draw each institution
        btnCreateUser.setOnAction(e->{
        	// boolean variable to check condition of each textfield
			boolean Continue = true;
			//conditonal statement to check if the textfields are empty or not if they are the boolean variable is turned fals
			// and the vertices are not created 
			if( txtNameofuser.getText().isEmpty())
			{
				Continue= false;
			   
			}
			
			if(txtDepttype.getText().isEmpty() )
			{
				Continue= false;
			}
			
			if( txtXPosition.getText().isEmpty())
			{
				Continue = false;
			}
			
			if( txtYPosition.getText().isEmpty())
			{
				Continue= false;
			}
			//if textfields are not empty then execute	
		if(Continue)
		{
			
			double xposition = Double.parseDouble(txtXPosition.getText()); //reads in the x position of entered
			double yposition = Double.parseDouble(txtYPosition.getText());//reads in y position enetered
		    String typeDept  =  txtDepttype.getText(); // reads in the type of institution
			String InstitutionName = txtNameofuser.getText(); // reads in the health institution name
			
			Institution _Institution= new Institution(InstitutionName,typeDept,xposition,yposition); // creates the institution model
			Vertex<Institution> _vertex = new Vertex<>(_Institution); //creates the vertex 
			graph.getVertices().add(_vertex); //adds the vertex on the graph
			 Canvas.Draw(graph); //draws the vertex on the canvas
			
			//clears all the textfields after entering the details of canvas
			txtNameofuser.clear();
			txtDepttype.clear();
			txtYPosition.clear();
			txtXPosition.clear();
			
			
		} 
	
		});
        
        
        btnConnectUsers.setOnAction(e->{
        	//prompts the user to enter the names of the vertices he wants to connect together
        	String name1 = JOptionPane.showInputDialog("Please enter the name of the institution you want to connect ");
        	String name2 = JOptionPane.showInputDialog("Please enter the name of the second institution  you want to connect with: "+ name1);
        	CreateEdges(name1,name2); // calls the create edge method to connect the vertices
        		
        });
		
           
	}
	
		public void GUIinstantiation()
	{
			
       txt    =   new TextArea("******NOTIFICATIONS BOOTH******");
	   Vbox   =   new VBox();
	   Gpane  =   new GridPane();
	   Canvas =   new Canvas();
	   bpane  =   new BorderPane();
	   graph  =   new Graph<>();
	  
	   
		Nodeinstantiation();//GUI node instantiated 
	}
		/**
		 * method to instantiate all the Userinteface nodes and creates the UI
		 */
	
	public void Nodeinstantiation()
	{
		//instantiation of all the labels, textfields and buttons
		lblNameofuser = new Label ("Name of health department");
	    txtNameofuser = new TextField();
	    
	    lblDepttype = new Label("type of health department");
	    txtDepttype = new TextField();
		
	    lblenterXPosition = new Label ("x position of department" );
	    txtXPosition = new TextField();
	    
	    lblenterYPosition = new Label ("y position of department" );
	    txtYPosition = new TextField();
	   
		btnCreateUser = new Button("Add institution");
		btnConnectUsers = new Button("Connect institutions");
		
		//setting preffered size of the nodes for proper display
	    Vbox.setSpacing(10);
	    Vbox.setPadding(new Insets(10));
	    
		lblNameofuser.setFont(new Font(10));
		txtNameofuser.setPrefWidth(5);
		txtNameofuser.setPrefHeight(10);
		
		lblDepttype.setFont(new Font(10));
		txtDepttype.setPrefWidth(5);
		txtDepttype.setPrefHeight(10);
		
		lblenterXPosition.setFont(new Font(10));
		txtXPosition.setPrefWidth(5);
		txtXPosition.setPrefHeight(10);
		
		lblenterYPosition.setFont(new Font(10));
		txtYPosition.setPrefWidth(5);
		txtYPosition.setPrefHeight(10);
		
		btnCreateUser.setPrefWidth(200);
		btnCreateUser.setPrefHeight(250);
		
		btnConnectUsers.setPrefWidth(200);
		btnConnectUsers.setPrefHeight(250);
		
		
		 txt.setPrefHeight(500);
		
		//adding all the UI nodes to a vbox to properly collect them
		Vbox.getChildren().addAll(lblNameofuser,txtNameofuser,
				                  lblDepttype,txtDepttype,
				                  lblenterXPosition,txtXPosition,
				                  lblenterYPosition,txtYPosition,
				                  btnCreateUser,btnConnectUsers,
				                  txt);
		
		
		Gpane.add(Vbox, 0, 0); //positioning the Vbox in the UI using gridpane
		Gpane.add(Canvas, 1, 0);// positioning the Canvas in the UI using gridpane
		bpane.setCenter(Gpane); // positoning the gridpane on the boarder pane for display
		getChildren().add(Gpane);
		
		
		
	}
	
	

	
}