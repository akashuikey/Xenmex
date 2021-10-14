/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xenmax;

import com.sun.javaws.progress.Progress;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static javafx.scene.paint.Color.TRANSPARENT;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author mukes
 */
public class LoginUIController extends Application implements Initializable {
    public String filePath="./faces";

    @FXML
    private AnchorPane camera;
    @FXML
    private AnchorPane crowd;
    @FXML
    private AnchorPane face;
    @FXML
    private AnchorPane cmd;
    @FXML
    private AnchorPane home;
    @FXML
    private AnchorPane camera_window;
    @FXML
    private AnchorPane crowd_window;
    @FXML
    private AnchorPane face_window;
    @FXML
    private AnchorPane cmd_window;
    @FXML
    private AnchorPane login;
    @FXML
    private AnchorPane main_container;
    private double xOffset = 0; 
    private double yOffset = 0;
    @FXML
    private ImageView camera_logo;
    @FXML
    private ImageView crowd_logo;
    @FXML
    private ImageView face_detect;
    @FXML
    private ImageView cmd_logo;
    @FXML
    private ImageView home_logo;
    @FXML
    private ImageView minimize;
    @FXML
    private Button startCam;
    @FXML
    private Button saveBtn;
    @FXML
    private Button recogniseBtn;
    @FXML
    private Button stopBtn;
    	FaceDetector faceDetect = new FaceDetector();	//Creating Face detector object									
			
	Database database = new Database();		//Creating Database object

	
	ArrayList<String> user = new ArrayList<String>();
	ImageView imageView1;
	
	public static ObservableList<String> event = FXCollections.observableArrayList();
	public static ObservableList<String> outEvent = FXCollections.observableArrayList();

	public boolean enabled = false;
	public boolean isDBready = false;
    @FXML
    private ImageView frame;
    private TitledPane dataPane;
    @FXML
    private AnchorPane pdPane;
    @FXML
    private Label title;
    @FXML
    private TextField fname;
    @FXML
    private TextField code;
    @FXML
    private TextField reg;
    @FXML
    private ListView<?> output;
    @FXML
    private Label savedLable;

	
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void start(Stage primaryStage) throws Exception {
         Parent root1 = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
         root1.setOnMousePressed((MouseEvent event) -> {
             xOffset = event.getSceneX();
             yOffset = event.getSceneY();
        });
        
        //move around here
        root1.setOnMouseDragged((MouseEvent event) -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
       
        Scene scene = new Scene(root1);
        Image icon = new Image(getClass().getResourceAsStream("/images/xenmaxc.png"));
        primaryStage.getIcons().add(icon);
        scene.setFill(TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void handleTab(MouseEvent event) {
        
         if(event.getTarget()==home_logo)
        {
            login.setVisible(true);
            cmd_window.setVisible(false);
            face_window.setVisible(false);
            crowd_window.setVisible(false);
            camera_window.setVisible(false);
        }
         else
              if(event.getTarget()==camera_logo)
        {
            login.setVisible(false);
            cmd_window.setVisible(false);
            face_window.setVisible(false);
            crowd_window.setVisible(false);
            camera_window.setVisible(true);
        }
         else
              if(event.getTarget()==crowd_logo)
        {
            login.setVisible(false);
            cmd_window.setVisible(false);
            face_window.setVisible(false);
            crowd_window.setVisible(true);
            camera_window.setVisible(false);
        }
         else
              if(event.getTarget()==face_detect)
        {
            login.setVisible(false);
            cmd_window.setVisible(false);
            face_window.setVisible(true);
            crowd_window.setVisible(false);
            camera_window.setVisible(false);
        }
         else
              if(event.getTarget()==cmd_logo)
        {
            login.setVisible(false);
            cmd_window.setVisible(true);
            face_window.setVisible(false);
            crowd_window.setVisible(false);
            camera_window.setVisible(false);
        }
    }

    @FXML
    private void loginHandle(MouseEvent event) throws IOException {
        Parent fxml=FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Scene registration_scene = new Scene(fxml);
        Stage registration_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        registration_stage.setScene(registration_scene);
        registration_stage.show();
    }

    @FXML
    private void handelRegister(MouseEvent event) throws IOException {
        Parent fxml=FXMLLoader.load(getClass().getResource("RegistrationUI.fxml"));
        Scene registration_scene = new Scene(fxml);
        Stage registration_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        registration_stage.setScene(registration_scene);
        registration_stage.show();
    }

    @FXML
    private void minimizeHandle(MouseEvent event) {
         Stage stage = (Stage)main_container.getScene().getWindow();
        stage  = (Stage)((ImageView)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void startCamera(ActionEvent event) throws SQLException {
        //*******************************************************************************************
		//initializing objects from start camera button event
		faceDetect.init();
                

		faceDetect.setFrame(frame);

		faceDetect.start();

		if (!database.init()) {

			System.out.println("Database Connection failed");

		} else {
			isDBready = true;
			System.out.println("Success: Database Connection Succesful ! ");
		}

		//*******************************************************************************************
		//Activating other buttons
		startCam.setVisible(false);
		
		stopBtn.setVisible(true);
		//ocrBtn.setDisable(false);
		
		if (isDBready) {
			recogniseBtn.setDisable(false);
		}

		
		// shapeBtn.setDisable(false);
		

		
		//*******************************************************************************************
		
		
		
		
		//**********************************************************************************************
		//Picture Gallary
		
		String path = filePath;

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		//Image reader from the mentioned folder
		
		//**********************************************************************************************
	
    }

    @FXML
    private void saveFace(ActionEvent event) {
        //Input Validation
		if (fname.getText().trim().isEmpty() || reg.getText().trim().isEmpty() || code.getText().trim().isEmpty()) {

			new Thread(() -> {

				try {
					System.out.print("empty value not allow");

					Thread.sleep(2000);

					

				} catch (InterruptedException ex) {
				}

			}).start();

		} else {
			//Progressbar
			

			new Thread(() -> {

				try {

					faceDetect.setFname(fname.getText());

					faceDetect.setFname(fname.getText());
					
					
					faceDetect.setCode(Integer.parseInt(code.getText()));
					
					faceDetect.setReg(Integer.parseInt(reg.getText()));

					database.setFname(fname.getText());
					
					
					database.setCode(Integer.parseInt(code.getText()));
					
					database.setReg(Integer.parseInt(reg.getText()));

					database.insert1();

					

					savedLable.setVisible(true);
					Thread.sleep(2000);

					

					savedLable.setVisible(false);

				} catch (InterruptedException ex) {
				}

			}).start();

			faceDetect.setSaveFace(true);

		}

    }
    int count = 0;

    @FXML
    private void faceRecognise(ActionEvent event) {
        faceDetect.setIsRecFace(true);
		// printOutput(faceDetect.getOutput());

		recogniseBtn.setText("Get Face Data");

		//Getting detected faces
		user = faceDetect.getOutput();

		if (count > 0) {

			//Retrieved data will be shown in Fetched Data pane
			String t = " Face Data: " + user.get(1);

			outEvent.add(t);

			String n1 = "First Name:" + user.get(1);

			outEvent.add(n1);

			//output.setItems(outEvent);

			

			String fc = "Face Code:" + user.get(0);

			outEvent.add(fc);

			//output.setItems(outEvent);

			String r = "Reg no:" + user.get(3);

			outEvent.add(r);

			//output.setItems(outEvent);
                        


		}

		count++;
    }

    @FXML
    private void stopCam(ActionEvent event) throws SQLException  {
       faceDetect.stop();

		startCam.setVisible(true);
		stopBtn.setVisible(false);

		/* this.saveFace=true; */

		

		recogniseBtn.setDisable(true);
		saveBtn.setDisable(true);
		dataPane.setDisable(true);
		
		
		database.db_close();
		
		isDBready=false;
         }

    @FXML
    private void startHotspot(MouseEvent event) throws IOException {
         Process p=Runtime.getRuntime().exec("WifiHotspot.jar");
  
    }
    

}
