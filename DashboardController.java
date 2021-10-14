/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xenmax;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane profile_container;
    @FXML
    private AnchorPane retrive_container;
    @FXML
    private AnchorPane upload_container;
    @FXML
    private AnchorPane transfer_container;
    @FXML
    private AnchorPane transfer_window;
    @FXML
    private AnchorPane profile_window;
    @FXML
    private AnchorPane upload_window;
    @FXML
    private AnchorPane download_window;
    @FXML
    private ImageView profile_logo;
    @FXML
    private ImageView download_logo;
    @FXML
    private ImageView upload_logo;
    @FXML
    private ImageView transfer_logo;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private ImageView minimize;
    @FXML
    private Button tselect_file;
    @FXML
    private Label transferLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleTab(MouseEvent event) {
         if(event.getTarget()==profile_logo)
        {
            profile_window.setVisible(true);
            upload_window.setVisible(false);
            download_window.setVisible(false);
            transfer_window.setVisible(false);
        }
         else
             if(event.getTarget()==upload_logo)
        {
            profile_window.setVisible(false);
            upload_window.setVisible(true);
            download_window.setVisible(false);
            transfer_window.setVisible(false);
        }
          else
             if(event.getTarget()==download_logo)
        {
            profile_window.setVisible(false);
            upload_window.setVisible(false);
            download_window.setVisible(true);
            transfer_window.setVisible(false);
        }
          else
             if(event.getTarget()==transfer_logo)
        {
            profile_window.setVisible(false);
            upload_window.setVisible(false);
            download_window.setVisible(false);
            transfer_window.setVisible(true);
        }
    }

    @FXML
    private void minimizeHandle(MouseEvent event) {
         Stage stage = (Stage)anchorpane.getScene().getWindow();
        stage  = (Stage)((ImageView)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
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
    private void choose_file(MouseEvent event) throws MalformedURLException {
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = chooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file = new File(chooser.getSelectedFile().getAbsolutePath());
			String localURL = file.toURI().toURL().toString();
			transferLabel.setText(localURL);
    }
    
}

    @FXML
    private void transferHandle(MouseEvent event) {
        String fp;
        fp=transferLabel.getText();
        
    }
}
