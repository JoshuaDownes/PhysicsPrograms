

import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import physicsControllers.CirclePhysics;
import javafx.scene.paint.Color;
import physicsControllers.CollisionController;
import physicsControllers.ObjectPhysics;

public class BouncingBall{
    
    private Group root;
    private Scene ballScene;
    private Animation currentAnimation;
        
    private ArrayList<ObjectPhysics> collisionObjects = new ArrayList<ObjectPhysics>();
    private CollisionController collisionController;
    private int ballCount = 1;
    private CirclePhysics bouncing;
    
    public BouncingBall(Group root, Scene ballScene){
       
        this.root = root;
        this.ballScene = ballScene;
        
        Circle ball = new Circle(30);
        root.getChildren().add(ball);
        ball.setCenterX(ballScene.getWidth()/2);
        ball.setCenterY(ballScene.getHeight()/2);
        
        bouncing = new CirclePhysics(ball, ballScene, ball.getRadius(), 1);
        collisionObjects.add(bouncing);
        collisionController= new CollisionController(collisionObjects, ballScene);
        bouncing.setCollisionController(collisionController);
        currentAnimation = bouncing.getTimeline();
    }
    
    public void setOptions(GridPane options){
        additionalOptions(options, bouncing);
    }
    
    public void additionalOptions(GridPane options, CirclePhysics originalBallPhysics){
        Button addBallButton = new Button("Add Ball");
        Button addBigger = new Button("Add Bigger");
        Button addSmaller = new Button("Add Smaller");
        addBallButton.setOnAction(e ->{
            addBall(root, ballScene, originalBallPhysics, 30);
        });
        addBigger.setOnAction(e->{addBall(root,ballScene,originalBallPhysics,45);});
        addSmaller.setOnAction(e->{addBall(root,ballScene,originalBallPhysics,15);});
       
        options.add(addBallButton, 0, 1);
        options.add(addBigger, 1, 1);
        options.add(addSmaller, 2, 1);
    }
    
    public void addBall(Group root, Scene ballScene, CirclePhysics mainBall, int size){
        
        if(ballCount<5){
            Circle newBall = new Circle(size);
            newBall.setFill(Color.rgb((int)(256*Math.random()),(int)(256* Math.random()),(int)(256*Math.random())));
            
            root.getChildren().add(newBall);
            newBall.setCenterX(ballScene.getWidth()/2+15*ballCount);
            newBall.setCenterY(ballScene.getHeight()/2+15*ballCount);
            
            CirclePhysics newBouncing = new CirclePhysics(newBall, ballScene, newBall.getRadius(), (double)size/30, mainBall.getTimeline());
            
            collisionObjects.add(newBouncing);
            
            ballCount++;

        }
    }

    
    public Animation getCurrentAnimation(){
        return currentAnimation;
    }
    
}
