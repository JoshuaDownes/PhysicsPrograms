
package physicsControllers;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Circle;


public class CirclePhysics extends ObjectPhysics{
    
    private Circle circle;
    private boolean beingDragged = false;
    private double[][] dragValues = new double[4][2];
    
    public CirclePhysics(Circle circle, Scene scene){
        super(circle, scene);
        this.circle = circle;
        setY(circle.getCenterY());
        setX(circle.getCenterX());
        circleController(scene);
    }
    
    public void update(){
        
        if(!beingDragged){
            setDy(getDy()+gravity);
            setY(getY()+getDy());
            setX(getX()+getDx());
        }
        
        else{ //if(beingDragged)
            for(int i = 0; i<dragValues.length-1; i++){ //Track X and Y values
                dragValues[i][0]=dragValues[i+1][0];
                dragValues[i][1]=dragValues[i+1][1];
            } 
            dragValues[dragValues.length-1][0] = getX(); 
            dragValues[dragValues.length-1][1] = getY();
        }
        
        circle.setCenterX(getX());
        circle.setCenterY(getY());
        checkCollisions();
        
    }
    
    public void checkCollisions(){
        checkFloorCollisions();
        checkWallCollisions();
        checkObjectCollisions();
    }
    
    public void checkFloorCollisions(){
        if(circle.getCenterY() + circle.getRadius() >= scene.getHeight()){
            setY(scene.getHeight()-circle.getRadius());
            circle.setCenterY(getY());
            setDy(-getDy() - gravity);
        }
        if(circle.getCenterY() - circle.getRadius() <= 0){
            setY(circle.getRadius());
            circle.setCenterY(getY());
            setDy(-getDy());
        }
    }
    
    public void checkWallCollisions(){
        if(circle.getCenterX() + circle.getRadius() >= scene.getWidth()){
            setX(scene.getWidth()-circle.getRadius());
            circle.setCenterX(getX());
            setDx(-getDx());
        }
        if(circle.getCenterX() - circle.getRadius() <= 0){
            setX(circle.getRadius());
            circle.setCenterX(getX());
            setDx(-getDx());
        }
    }
    
    public void checkObjectCollisions(){
        //Not done yet
    }
    
    public void circleController(Scene scene){
        scene.setOnMousePressed(c ->{
            beingDragged = true;
            setDy(0);
            setDx(0);
            setX(c.getX());
            setY(c.getY());
        });
        scene.setOnMouseDragged(e ->{
            beingDragged = true;
            setDy(0);
            setDx(0);
            setX(e.getX());
            setY(e.getY());
        });
        scene.setOnMouseReleased(r ->{
            beingDragged = false;
            int l = dragValues.length;
            setDx((dragValues[l-1][0]-dragValues[0][0])/(dt/4));
            setDy((dragValues[l-1][1]-dragValues[0][1])/(dt/4));

        });
    }
}
