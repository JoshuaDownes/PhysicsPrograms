
package physicsControllers;

import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Circle;


public class CirclePhysics extends ObjectPhysics{
    
    private Circle circle;
    private boolean beingDragged = false;
    private double[][] dragValues = new double[4][2];
    
    public CirclePhysics(Circle circle, Scene scene, double radius, double mass){
        super(circle, scene, radius*2, radius*2, mass);
        this.circle = circle;
        setY(circle.getCenterY());
        setX(circle.getCenterX());
        circleController(scene);
    }
    
    public CirclePhysics(Circle circle, Scene scene, double radius, double mass, Timeline mainTimeline){
        super(circle, scene, radius*2, radius*2, mass, mainTimeline);
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
    
    public boolean collisionXRange(ObjectPhysics object){
        //if object == circle
        return collisionXRange((CirclePhysics)object);
    }
    
    public boolean collisionXRange(CirclePhysics circle){ // Return the width from center which results in collision if an object is within Y range to collide
        //Collision detection between two circles
        //Step 1: Find lowest possible value for collision between two circles, i.e. radius 1 + radius 2
        double limitBetween = getHeight()/2 + circle.getHeight()/2; //radii
        
        //Step 2: check distance between arcs. Are they closer?
        double distanceBetween = Math.sqrt(Math.pow((getX()-circle.getX()),2)+Math.pow((getY()-circle.getY()),2));
        
        return distanceBetween < limitBetween;
    }
    
    
    
    public void checkCollisions(){
        checkFloorCollisions();
        checkWallCollisions();
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
    
    public void circleController(Scene scene){
        circle.setOnMousePressed(pressed ->{
            beingDragged = true;
            setDy(0);
            setDx(0);
            setX(pressed.getX());
            setY(pressed.getY());
        });
        circle.setOnMouseDragged(draggingMouse ->{
            beingDragged = true;
            setDy(0);
            setDx(0);
            setX(draggingMouse.getX());
            setY(draggingMouse.getY());
        });
        circle.setOnMouseReleased(released ->{
            beingDragged = false;
            int l = dragValues.length;
            setDx((dragValues[l-1][0]-dragValues[0][0])/(dt/l));
            setDy((dragValues[l-1][1]-dragValues[0][1])/(dt/l));
        });
        scene.setOnKeyPressed(keyPressed ->{
            if(keyPressed.getCode()==KeyCode.R){
                setDx(0);
                setDy(0);
                setX(scene.getWidth()/2);
                setY(scene.getHeight()/2);
            }
        });
        
    }
}
