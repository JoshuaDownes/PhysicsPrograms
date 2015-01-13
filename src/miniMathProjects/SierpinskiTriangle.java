
package miniMathProjects;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public class SierpinskiTriangle {

    //Scene and group for main screen
    private Scene scene; 
    private Group root;
    
    private ArrayList<Triangle> mainTriangles = new ArrayList();
    
    private double b = 50.0; //Buffer for screen height and width
    private Double[] points;
    private int genCount = 1;
    
    public SierpinskiTriangle(Group root, Scene scene){
        
        this.scene = scene;
        this.root = root;
        
        Point2D p1 = new Point2D(scene.getWidth()/2, b);
        Point2D p2 = new Point2D(b, scene.getHeight() - b);
        Point2D p3 = new Point2D(scene.getWidth() - b, scene.getHeight() - b);
        
        Triangle initial = new Triangle(p1, p2, p3);
        
        mainTriangles.add(initial);
        
        root.getChildren().add(initial);
        
        scene.setOnMouseClicked(e->{
            if(genCount<=11){
                nextGeneration();
                genCount++;
            }
        });
        
    }
    
    public void nextGeneration(){ //Creates new upside down triangle and places it inside each smaller triangle
        
       int initialSize = mainTriangles.size();
       ArrayList<Triangle> newMainTriangles = new ArrayList();
       
       for(int j = 0; j < initialSize; j++){
           Triangle t = mainTriangles.get(j);
           t.setOpacity(0);
           
           //Generates 3 inner triangles
           for(int i = 1; i <=3; i++){
               Point2D p1, p2, p3;
               
               p1 = t.getPoint(i);
               switch(i){
                   case 1: p2 = t.getPoint(i).midpoint(t.getPoint(2));
                           p3 = t.getPoint(i).midpoint(t.getPoint(3));
                           break;
                   case 2: p2 = t.getPoint(i).midpoint(t.getPoint(1));
                           p3 = t.getPoint(i).midpoint(t.getPoint(3));
                           break;
                   case 3: p2 = t.getPoint(i).midpoint(t.getPoint(1));
                           p3 = t.getPoint(i).midpoint(t.getPoint(2));
                           break;
                   default:p2 = new Point2D(0,0);
                           p3 = new Point2D(0,0);
               }
                newMainTriangles.add(new Triangle(p1,p2,p3));
                root.getChildren().add(newMainTriangles.get(i-1+(3*j)));
           } 
       }
       
       mainTriangles = newMainTriangles;
    }
    
}

class Triangle extends Polygon{
    
    Point2D p1, p2, p3;
    
    public Triangle(Point2D p1, Point2D p2, Point2D p3){
        super.getPoints().addAll(p1.getX(),p1.getY(),p2.getX(),p2.getY(),p3.getX(),p3.getY());
        super.setFill(Color.MIDNIGHTBLUE);
        
        this.p1 = p1; this.p2 = p2; this.p3 = p3;
    }
    
    public Point2D getPoint(int n){
        switch(n){
            case 1: return p1;
            case 2: return p2;
            case 3: return p3;
            default: return new Point2D(0,0);
        }
    }
}
