

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application{
    
    private Group root = new Group();
    private Scene startingScene;
    private Button quit = new Button("QUIT");
    private Button info = new Button("INFO");
    private Object runningObject;
    private Stage primaryStage;
            
    @Override
    public void start(Stage primaryStage){
        
        this.primaryStage = primaryStage;
        
        GridPane grid = new GridPane();
        Text title = new Text("Physics Simulations\n Joshua Downes");
        Button bouncingBallStart = new Button("Elastic Ball");
        grid.add(title, 0, 0);
        grid.add(bouncingBallStart,0,1);
        
        startingScene = new Scene(grid, 500, 350);
        
        primaryStage.setScene(startingScene);
        primaryStage.setTitle("Physics Simulation Projects");
        primaryStage.show();
        
        bouncingBallStart.setOnAction((ActionEvent startElBall) ->{
            root = new Group();
            Scene ballScene = new Scene(root, 500, 350);
            BouncingBall eBall = new BouncingBall(root, ballScene);
            sceneOptions(root, eBall.getCurrentAnimation(), new Text("Click on the ball to drag it around\n"
                    + "Collisions are elastic\nIf it is moving too fast, press R to reset."));
            primaryStage.setTitle("Elastic Ball");
            primaryStage.setScene(ballScene);
            primaryStage.show();
            
        });
        
    }
    
    public void sceneOptions(Group root, Animation animation, Text infoText){
        GridPane buttonPane = new GridPane();
        buttonPane.add(quit, 0, 0);
        quit.setOnAction((ActionEvent q)->{
            animation.stop();
            primaryStage.setScene(startingScene);
            primaryStage.setTitle("Physics Simulation Projects");
            primaryStage.show();
        });
        
        buttonPane.add(info, 1, 0);
        info.setOnMouseEntered((MouseEvent i)->{
            buttonPane.add(infoText, 1, 1);
        });
        info.setOnMouseExited((MouseEvent iExit)->{
            buttonPane.getChildren().remove(infoText);
        });
                
        
        root.getChildren().add(buttonPane);
        buttonPane.setLayoutX(40);
        buttonPane.setLayoutY(0);
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
