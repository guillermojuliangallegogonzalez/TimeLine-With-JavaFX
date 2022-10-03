package timeline;

import com.sun.javafx.perf.PerformanceTracker;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
 * @author guillermogallegogonzalez
 */
public class TimelineDemo extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Group pane = new Group();
        // Bola que se usará para la animación
        Circle ball = new Circle(10);
        ball.setTranslateX(300 * 0.5);
        ball.setTranslateY(250 * 0.5);
        pane.getChildren().addAll(ball);
        // Etiqueta que mostrará el valor de frames por segundo (FPS)
        Label label = new Label();
        label.setTranslateX(10);
        label.setTranslateY(10);
        pane.getChildren().addAll(label);
        Scene scene = new Scene(pane, 300, 250);
        //Escuchador a incluir en el bucle de Timeline
        EventHandler<ActionEvent> eH = e->{
        // Mostrar la frecuencia de refresco FPS
        PerformanceTracker perfTracker=
        PerformanceTracker.getSceneTracker(scene);
        label.setText("FPS (Timeline) = "+perfTracker.getInstantFPS());
        // Cambiar la dirección de la bola si llega a los extremos
        if(ball.getTranslateX()< 0 || ball.getTranslateX()> 300){
        ballSpeedX*=-1;
        }
        if(ball.getTranslateY()< 0 || ball.getTranslateY()> 250){
        ballSpeedY*=-1;
        }
        ball.setTranslateX(ball.getTranslateX()+ballSpeedX);
        ball.setTranslateY(ball.getTranslateY()+ballSpeedY);
        };
        
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(5),eH));
        animation.setCycleCount(Timeline.INDEFINITE);
        
        animation.play();
        
        primaryStage.setTitle("TimeLine Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static double ballSpeedX= 1;
    public static double ballSpeedY= 1;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
