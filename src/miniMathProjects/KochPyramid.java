
package miniMathProjects;

import javafx.scene.paint.Color;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Line;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Rotate;

public class KochPyramid {
    
    private Scene scene;
    private Group root;
    
    private float b = 300;
    
    public KochPyramid(Group root, Scene scene){
        
        this.scene = scene;
        this.root = root;
        
        PhongMaterial blueStuff = new PhongMaterial(Color.BLUE);
        
        
        Point3D p1 = new Point3D(scene.getWidth()/2, b, scene.getWidth()/2);
        Point3D p2 = new Point3D(b, scene.getHeight() - b, 0);
        Point3D p3 = new Point3D(scene.getWidth()/2, scene.getHeight() - b, scene.getHeight());
        Point3D p4 = new Point3D(scene.getWidth() - b, scene.getHeight() - b, 0);
        
        TriangleMesh pyramid = new TriangleMesh();
        
        pyramid.getTexCoords().addAll(0,0);
        
        pyramid.getPoints().addAll(
                0, 0, 0,
                75, 150, -75,
                150, 0, 0,
                75, 0, 150
        );
        
        pyramid.getFaces().addAll(
                0,0,    2,0,    1,0,
                0,0,    2,0,    3,0,
                0,0,    1,0,    3,0
        );
        
        
        
        MeshView pyramidView = new MeshView(pyramid);
        pyramidView.setDrawMode(DrawMode.FILL);
        pyramidView.setMaterial(blueStuff);
        pyramidView.setTranslateX(200);
        pyramidView.setTranslateY(100);
        pyramidView.setTranslateZ(200);
        pyramidView.setRotationAxis(new Point3D(1,1,0));
        //pyramidView.setRotationAxis(Rotate.X_AXIS);
        
        scene.setOnKeyPressed(e->{
            pyramidView.setRotate(pyramidView.getRotate()+5);
        });
        
        root.getChildren().add(pyramidView);
    }
    
}
