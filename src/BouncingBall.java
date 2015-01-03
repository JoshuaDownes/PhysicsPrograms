

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import physicsControllers.CirclePhysics;

public class BouncingBall extends Application {
    
    private Group root = new Group();
    
    @Override
    public void start(Stage primaryStage){
        
        Circle ball = new Circle(30);
        root.getChildren().add(ball);
        ball.setCenterX(250);
        ball.setCenterY(150);
        
        Scene ballScene = new Scene(root, 500, 350);
        
        new CirclePhysics(ball, ballScene);
        
        primaryStage.setTitle("Bouncing Ball");
        primaryStage.setScene(ballScene);
        primaryStage.show();
        
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
    
}
