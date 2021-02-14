import javafx.scene.paint.Color;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.shape.*;

import static javafx.scene.paint.Color.rgb;

public class Lab1 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Yaschook Lab1");

        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);

        Rectangle stem = new Rectangle();
        stem.setX(125);
        stem.setY(120);
        stem.setWidth(7);
        stem.setHeight(100);
        root.getChildren().add(stem);
        scene.setFill(rgb(0, 255, 128));
        stem.setFill(rgb(0, 128, 0));

        Circle circle = new Circle(128, 110, 25, Color.YELLOW);
        root.getChildren().add(circle);
        
        Polygon petal1 = new Polygon();
        petal1.getPoints().addAll(new Double[]{
        	    105.0, 100.0,
        	    105.0, 125.0,
        	    75.0, 130.0,
           	    75.0, 100.0,        	    
        	     });
        root.getChildren().add(petal1);
        petal1.setFill(Color.RED);
        
        Polygon petal2 = new Polygon();
        petal2.getPoints().addAll(new Double[]{        	     
           	     147.0,125.0,
           	     148.0,100.0,
          	    185.0, 100.0,
        	    185.0, 130.0,
        	     });
        root.getChildren().add(petal2);
        petal2.setFill(Color.RED);
        
        Polygon petal3 = new Polygon();
        petal3.getPoints().addAll(new Double[]{        	     
           	     158.0,165.0,
           	     168.0,140.0,
          	    145.0, 125.0,
        	    130.0, 135.0,
        	     });
        root.getChildren().add(petal3);
        petal3.setFill(Color.RED);
        
        Polygon petal4 = new Polygon();
        petal4.getPoints().addAll(new Double[]{  
        		115.0,165.0,
           	    95.0,145.0,           	     
          	    110.0, 125.0,
        	    122.0, 135.0,
        	     });
        root.getChildren().add(petal4);
        petal4.setFill(Color.RED);
        
        Polygon petal5 = new Polygon();
        petal5.getPoints().addAll(new Double[]{  
        		130.0, 85.0,
           	    145.0, 90.0, 
           	    170.0, 80.0,
          	    150.0, 65.0,        	    
        	     });
        root.getChildren().add(petal5);
        petal5.setFill(Color.RED);
        
        Polygon petal6 = new Polygon();
        petal6.getPoints().addAll(new Double[]{  
        		105.0, 95.0,
           	    120.0, 90.0, 
           	    120.0, 65.0,
           	    90.0, 80.0,     
        	     });
        root.getChildren().add(petal6);
        petal6.setFill(Color.RED);
        

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
