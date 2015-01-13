
import javafx.animation.Animation;
import javafx.animation.Timeline;
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
import miniMathProjects.SierpinskiTriangle;


public class Main extends Application{
    
    private Group root = new Group();
    private Scene startingScene;
    private Button quit = new Button("QUIT");
    private Button info = new Button("INFO");
    private Object runningObject;
    private Stage primaryStage;
    private GridPane options;
            
    @Override
    public void start(Stage primaryStage){
        
        this.primaryStage = primaryStage;
        
        GridPane grid = new GridPane();
        Text title = new Text("Physics Simulations\n Joshua Downes");
        Button bouncingBallStart = new Button("Elastic Ball");
        Button sierpinskiTriangleButton = new Button("Sierpinski Triangle");
        grid.add(title, 0, 0);
        grid.add(bouncingBallStart,0,1);
        grid.add(sierpinskiTriangleButton,0,2);
        
        startingScene = new Scene(grid, 500, 350);
        
        primaryStage.setScene(startingScene);
        primaryStage.setTitle("Physics Simulation Projects");
        primaryStage.show();
        
        bouncingBallStart.setOnAction((ActionEvent startElBall) ->{
            root = new Group();
            Scene ballScene = new Scene(root, 700, 500);
            BouncingBall eBall = new BouncingBall(root, ballScene);
            sceneOptions(root, eBall.getCurrentAnimation(), new Text("Click on the ball to drag it around\n"
                    + "Collisions are elastic\nIf ball(s) moving too fast, press R to reset.\n\nSimulation"
                    + " of conservation of momentum for elastic collisions"));
            eBall.setOptions(options);
            primaryStage.setTitle("Elastic Ball(s)");
            primaryStage.setScene(ballScene);
            primaryStage.show();
            
        });
        
        sierpinskiTriangleButton.setOnAction((ActionEvent startSerpinski) ->{
            root = new Group();
            Scene sTriangleScene = new Scene(root, 700, 500);
            SierpinskiTriangle sierpinskiTriangle = new SierpinskiTriangle(root, sTriangleScene);
            sceneOptions(root,new Timeline(), new Text("Click for next generation\nRuns a max of 10 generations"));
            primaryStage.setTitle("Sierpinski Triangle");
            primaryStage.setScene(sTriangleScene);
            primaryStage.show();
        });
        
    }
    
    public void sceneOptions(Group root, Animation animation, Text infoText){
        options = new GridPane();
        options.add(quit, 0, 0);
        quit.setOnAction((ActionEvent q)->{
            animation.stop();
            primaryStage.setScene(startingScene);
            primaryStage.setTitle("Physics Simulation Projects");
            primaryStage.show();
        });
        
        options.add(info, 1, 0);
        info.setOnMouseEntered((MouseEvent i)->{
            options.add(infoText, 10, 10);
        });
        info.setOnMouseExited((MouseEvent iExit)->{
            options.getChildren().remove(infoText);
        });
                
        
        root.getChildren().add(options);
        options.setLayoutX(0);
        options.setLayoutY(0);
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
