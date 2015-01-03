

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import physicsControllers.CirclePhysics;

public class BouncingBall{
    
    private Group root;
    private Scene ballScene;
    private Animation currentAnimation;
    
    public BouncingBall(Group root, Scene ballScene){
       
        this.root = root;
        this.ballScene = ballScene;
        
        Circle ball = new Circle(30);
        root.getChildren().add(ball);
        ball.setCenterX(ballScene.getWidth()/2);
        ball.setCenterY(150);
        
        CirclePhysics bouncing = new CirclePhysics(ball, ballScene);
        currentAnimation = bouncing.getAnimation();
    }
    
    public Animation getCurrentAnimation(){
        return currentAnimation;
    }
    
}
