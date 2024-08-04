package institution;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Institution implements Comparable<Institution>{
//declaring variables of the institution (attributes)
	GraphicsContext gc;
	private String name;
	private String type;
	private double xPosition;
	private double yPosition;
	
	
	/**
	 * constructer to create the instance of the institution
	 * @param name
	 * @param type
	 * @param x
	 * @param y
	 */
	
	public Institution(String name,String type,double x,double y) {
		
		this.name = name;
		this.xPosition = x;
		this.yPosition = y;
		this.setType(type);
		
		
	}
	public void drawActor(GraphicsContext gc)
	{
		gc.setFill(Color.BLACK);
		gc.fillOval(50, 50, 70, 70);
		
		
	}

	/**
	 * getter and setter methods to access the variables of the institution (vertex) 
	 */
	@Override
	public int compareTo(Institution arg0) {
		return 0;
	}
   
	public String getName() {
		return name;
	}
	
	
	public double getX() {
		return xPosition;
	}
	
	public double getY() {
		return yPosition;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setX(double xPosition) {
		this.xPosition = xPosition;
	}
	public void setY(double yPosition) {
		this.yPosition = yPosition;
		
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
    
	
	
}