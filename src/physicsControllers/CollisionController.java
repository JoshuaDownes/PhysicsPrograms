
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
            obj.setY(currentScene.getHeight()-obj.getHeight()/2);
            obj.setDy(-obj.getDy()-obj.gravity/2);
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
        
        //Adjusts velocities with formulae derived from conservation of energy and momentum equations
        double m1 = obj1.getMass(), m2 = obj2.getMass();
        double v1y = obj1.getDy(), v2y = obj2.getDy();
        double v1x = obj1.getDx(), v2x = obj2.getDx();
        
        obj1.setDy(((m1-m2)*v1y + 2*m2*v2y)/(m1+m2));
        obj1.setDx(((m1-m2)*v1x + 2*m2*v2x)/(m1+m2));
        
        obj2.setDy((2*m1*v1y - (m1-m2)*v2y)/(m1+m2));
        obj2.setDx((2*m1*v1x - (m1-m2)*v2y)/(m1+m2));
    }
    
}
