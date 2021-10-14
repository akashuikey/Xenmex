/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xenmax;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/**
 * FXML Controller class
 *
 * @author mukes
 */
public class RegistrationUIController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField cpassword;
    @FXML
    private DatePicker dob;
    @FXML
    private Button image;
    @FXML
    private ImageView image_content;
    Database db = new Database();
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private ImageView minimize;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleSubmit(MouseEvent event) throws ClassNotFoundException, SQLException, IOException {
        
        
        db.insert(name.getText(), username.getText(), password.getText(), dob.getEditor().toString());
         Parent fxml=FXMLLoader.load(getClass().getResource("MainUI.fxml"));
        Scene registration_scene = new Scene(fxml);
        Stage registration_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        registration_stage.setScene(registration_scene);
        registration_stage.show();
    }

    @FXML
    private void chooseImage(MouseEvent event) throws MalformedURLException {
     
           JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = chooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file = new File(chooser.getSelectedFile().getAbsolutePath());
			String localURL = file.toURI().toURL().toString();
			image_content.setImage(new Image(localURL));
                        
    }
    
}

    @FXML
    private void minimizeHandle(MouseEvent event) {
         Stage stage = (Stage)anchorpane.getScene().getWindow();
        stage  = (Stage)((ImageView)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        Parent fxml=FXMLLoader.load(getClass().getResource("LoginUI.fxml"));
        Scene registration_scene = new Scene(fxml);
        Stage registration_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        registration_stage.setScene(registration_scene);
        registration_stage.show();
    }
}