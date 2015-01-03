
package physicsControllers;

import javafx.event.ActionEvent;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.scene.Scene;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public abstract class ObjectPhysics {
    private Shape shape;
    private double x, y, dx, dy, t;
    private double height, width;
    
    protected Timeline timeline;
     
    protected Scene scene;
    protected final double gravity = (9.8/30), dt = 33;  //30 frames per second
   
    
    public ObjectPhysics(Shape shape, Scene scene, double height, double width){
        this.shape = shape;
        this.scene = scene;
        startTimeline();
    }
    
    public ObjectPhysics(Shape shape, Scene scene, double height, double width, Timeline mainTimeline){
        this.shape = shape;
        this.scene = scene;
        startTimeline(mainTimeline);
    }
    
    public void startTimeline() {
        timeline = TimelineBuilder.create().cycleCount(Animation.INDEFINITE)
                .keyFrames(new KeyFrame(Duration.millis(dt), (ActionEvent event) -> {
                    update();
                    setT(getT()+dt);
        })).build();
        
        timeline.play();
    }
    
    
    //Overloads startTimeLine method, attaches timeline for additional balls
    //to original ball so that all timelines stop together
    public void startTimeline(Timeline mainTimeLine){
        timeline = TimelineBuilder.create().cycleCount(Animation.INDEFINITE)
                .keyFrames(new KeyFrame(Duration.millis(dt), (ActionEvent event) -> {
                    update();
                    setT(getT()+dt);
                    if(mainTimeLine.getStatus()==Animation.Status.STOPPED){
                        timeline.stop();
                    }
                    if(mainTimeLine.getStatus()==Animation.Status.PAUSED){
                        timeline.pause();
                    }
                    else timeline.play();
        })).build();
        
        timeline.play();
    }
    
    public Timeline getTimeline(){
        return timeline;
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
