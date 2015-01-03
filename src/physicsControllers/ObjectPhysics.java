
package physicsControllers;

import javafx.event.ActionEvent;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.scene.Scene;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public abstract class ObjectPhysics {
    private Shape shape;
    private double x, y, dx, dy, t;
     
    protected Scene scene;
    protected final double gravity = (9.8/30), dt = 33;  //30 frames per second
   
    
    public ObjectPhysics(Shape shape, Scene scene){
        this.shape = shape;
        this.scene = scene;
        startTimeline();
    }
    
    public void startTimeline() {
        TimelineBuilder.create().cycleCount(Animation.INDEFINITE)
                .keyFrames(new KeyFrame(Duration.millis(dt), (ActionEvent event) -> {
                    update();
                    setT(getT()+dt);
        })).build().play();
    }
    
    abstract void update();
    
    public void setX(double x){
        this.x=x;
    }
    
    public void setY(double y){
        this.y=y;
    }
    
    public void setDx(double dx){
        this.dx=dx;
    }
    
    public void setDy(double dy){
        this.dy=dy;
    }
    
    public void setT(double t){
        this.t=t;
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public double getDx(){
        return dx;
    }
    
    public double getDy(){
        return dy;
    }
    
    public double getT(){
        return t;
    }
}
