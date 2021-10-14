/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xenmax;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author mukes
 */
public class MainUIController implements Initializable {
    
    @FXML
    private Button start; 
    @FXML
    private ImageView minimize;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private ImageView info;
    @FXML
    private AnchorPane infoWindow;
    @FXML
    private ImageView closeInfo;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(MouseEvent event) throws IOException {
        Parent fxml=FXMLLoader.load(getClass().getResource("LoginUI.fxml"));
        Scene registration_scene = new Scene(fxml);
        Stage registration_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        registration_stage.setScene(registration_scene);
        registration_stage.show();
        
    }

    @FXML
    private void close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimizeHandle(MouseEvent event) {
        Stage stage = (Stage)anchorpane.getScene().getWindow();
        stage  = (Stage)((ImageView)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void infoHandle(MouseEvent event) {
        if(event.getTarget()==info)
        {
            infoWindow.setVisible(true);
            anchorpane.setVisible(false);
        }
       
            
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        Parent fxml=FXMLLoader.load(getClass().getResource("MainUI.fxml"));
        Scene registration_scene = new Scene(fxml);
        Stage registration_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        registration_stage.setScene(registration_scene);
        registration_stage.show();
    }
    
}
