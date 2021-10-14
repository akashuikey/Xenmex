/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xenmax;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import static javafx.scene.paint.Color.TRANSPARENT;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author mukes
 */
public class XenMax extends Application {
    private double xOffset = 0; 
    private double yOffset = 0;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root1 = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
         root1.setOnMousePressed((MouseEvent event) -> {
             xOffset = event.getSceneX();
             yOffset = event.getSceneY();
        });
        
        //move around here
        root1.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
       
        Scene scene = new Scene(root1);
        Image icon = new Image(getClass().getResourceAsStream("/images/xenmaxc.png"));
        stage.getIcons().add(icon);
        scene.setFill(TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
