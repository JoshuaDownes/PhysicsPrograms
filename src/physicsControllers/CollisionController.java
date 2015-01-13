
//Checks collisions between objects with parent class ObjectPhysics, sets dy and dx accordingly
package physicsControllers;

import java.util.ArrayList;
import javafx.scene.Scene;

public class CollisionController {
    
    private ArrayList<ObjectPhysics> collidableObjects;
    private Scene currentScene;
    
    public CollisionController(ArrayList<ObjectPhysics> collidableObjects, Scene currentScene){
        this.collidableObjects = collidableObjects;
        this.currentScene = currentScene;
    }
    
    public void add(ObjectPhysics newObject){
        collidableObjects.add(newObject);
    }
    
    public void checkCollisions(){ //
        
        for(int i = 0; i < collidableObjects.size(); i++){
            checkBoundaryCollisions(collidableObjects.get(i));
            for(int j = i+1; j < collidableObjects.size(); j++){
                checkCollisions(collidableObjects.get(i), collidableObjects.get(j));
            }
        }
    }
    
    public void checkCollisions(ObjectPhysics obj1, ObjectPhysics obj2){
        if(obj1.getY()+obj1.getHeight() > obj2.getY() && obj2.getY()+obj2.getHeight() > obj1.getY()){
            if(checkCollisionXAxis(obj1, obj2)){
                adjustVelocities(obj1, obj2);
            }
        }
    }
    
    public boolean checkCollisionXAxis(ObjectPhysics obj1, ObjectPhysics obj2){
        //if(objects == circles){
            //Step 1: Find lowest possible value for collision between 2 circles, i.e. radius1 + radius2
            double limitBetween = obj1.getHeight()/2 + obj2.getHeight()/2; //radii
        
            //Step 2: check distance between arcs. Are they closer?
            double distanceBetween = Math.sqrt(Math.pow((obj1.getX()-obj2.getX()),2)+Math.pow((obj1.getY()-obj2.getY()),2));
            
            return distanceBetween < limitBetween;
        //}    
    }
    
    public void checkBoundaryCollisions(ObjectPhysics obj){
        if(obj.getY() + obj.getHeight()/2 >= currentScene.getHeight()){
            obj.setDy(-obj.getDy()*(currentScene.getHeight()/(obj.getY() + obj.getHeight()/2)) - obj.gravity);
            obj.setY(currentScene.getHeight() - obj.getHeight()/2);
        }
        if(obj.getY() - obj.getHeight()/2 <= 0){
            obj.setY(obj.getHeight()/2);
            obj.setDy(-obj.getDy());
        }
        if(obj.getX() + obj.getHeight()/2 >= currentScene.getWidth()){
            obj.setX(currentScene.getWidth()-obj.getHeight()/2);
            obj.setDx(-obj.getDx());
        }
        if(obj.getX() - obj.getHeight()/2 <= 0){
            obj.setX(obj.getHeight()/2);
            obj.setDx(-obj.getDx());
        }
    }
    
    
    public void adjustVelocities(ObjectPhysics obj1, ObjectPhysics obj2){
        //if objects == circles
        
        //First we place the circles outside of each other
        double dist = Math.sqrt(Math.pow((obj1.getY() - obj2.getY()), 2) + Math.pow((obj1.getX() - obj2.getX()), 2));
        double midpointx = (obj1.getX() + obj2.getX())/2;
        double midpointy = (obj1.getY() + obj2.getY())/2;
        
        obj1.setX(midpointx + obj1.getHeight()/2 * (obj1.getX()-obj2.getX())/dist);
        obj1.setY(midpointy + obj1.getHeight()/2 * (obj1.getY()-obj2.getY())/dist);
        obj2.setX(midpointx + obj2.getHeight()/2 * (obj2.getX()-obj1.getX())/dist);
        obj2.setY(midpointy + obj2.getHeight()/2 * (obj2.getY()-obj1.getY())/dist);
        
        //Next, adjusts velocities with formulae derived from conservation of energy and momentum equations
        dist = Math.sqrt(Math.pow((obj1.getY() - obj2.getY()), 2) + Math.pow((obj1.getX() - obj2.getX()), 2)); //Recalculate distance 
        
        double normalX = (obj2.getX() - obj1.getX())/dist;
        double normalY = (obj2.getY() - obj1.getY())/dist;
        
        double p = 2 * (obj1.getDx()*normalX + obj1.getDy()*normalY - obj2.getDx()*normalX - obj2.getDy()*normalY) 
                / (obj1.getMass() + obj2.getMass());
        
        obj1.setDx(obj1.getDx() - p*obj2.getMass() * normalX);
        obj1.setDy(obj1.getDy() - p*obj2.getMass() * normalY);
        
        obj2.setDx(obj2.getDx() + p*obj1.getMass() * normalX);
        obj2.setDy(obj2.getDy() + p*obj1.getMass() * normalY);
       
    }
    
}
