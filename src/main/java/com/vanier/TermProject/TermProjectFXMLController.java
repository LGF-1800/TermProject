package com.vanier.TermProject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.vanier.TermProject.model.Physics;
import com.vanier.TermProject.model.TimelineWrapper;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TermProjectFXMLController implements Initializable {

    @FXML
    private Rectangle AdvanceToRec;
    @FXML
    private Rectangle RewindRec;
    @FXML
    private Rectangle ControlsRec;
    @FXML
    private Rectangle ParamRec;
    @FXML
    private MenuBar MenuBar;
    @FXML
    private Line LeftLine;
    @FXML
    private Line OrthogonalToLeftLine;
    @FXML
    private Label ParamLabel;
    @FXML
    private Rectangle VVeclocityRec1;
    @FXML
    private TextField VVelocityField1;
    @FXML
    private Label VVelocityLabel1;
    @FXML
    private Rectangle HVeclocityRec;
    @FXML
    private TextField HVelocityField;
    @FXML
    private Label HVelocityLabel;
    @FXML
    private Rectangle AngleRec;
    @FXML
    private TextField AngleField;
    @FXML
    private Label AngleLabel;
    @FXML
    private Rectangle GravAccRec;
    @FXML
    private TextField GravAccField;
    @FXML
    private Label GravAccLabel;
    @FXML
    private Rectangle HeightRec;
    @FXML
    private TextField HeightField;
    @FXML
    private Label HeightLabel;
    @FXML
    private Line OrthogonalToLeftLine1;
    @FXML
    private Label ControlLabel;
    @FXML
    private Line OrthogonalToLeftLine2;
    @FXML
    private Polyline StartShape;
    @FXML
    private Button StartBtn;
    @FXML
    private Rectangle StopShape;
    @FXML
    private Button StopBtn;
    @FXML
    private Label RewindLabel;
    @FXML
    private TextField RewindToField;
    @FXML
    private Label AdvanceToLabel;
    @FXML
    private TextField AdvanceToField;
    @FXML
    private Button ResetBtn;
    @FXML
    private Polyline ResetShape1;
    @FXML
    private Polyline ResetShape;
    @FXML
    private Line OrthogonalToLeftLine3;
    @FXML
    private Rectangle BotLeftRec;
    @FXML
    private Slider VerticalDisSlider;
    @FXML
    private Rectangle VerticalDisRec;
    @FXML
    private Label VerticalDisLabel;
    @FXML
    private Label VerticalDisReading;
    @FXML
    private Label VerticalDisUnit;
    @FXML
    private Rectangle HorizontalDisRec;
    @FXML
    private Label HorizontalDisLabel;
    @FXML
    private Label HorizontalDisReading;
    @FXML
    private Label HorizontalDisUnit;
    @FXML
    private Slider HorizontalDisSlider;
    @FXML
    private Line VerticalLine;
    @FXML
    private Line VerticalLine1;
    @FXML
    private Rectangle TimeRec;
    @FXML
    private Label TimeLabel;
    @FXML
    private Label TimeReading;
    @FXML
    private Label TimeUnit;
    @FXML
    private Slider TimeSlider;
    @FXML
    private Line VerticalLine2;
    @FXML
    private Button GraphBtn;
    @FXML
    private Rectangle GraphShape;
    @FXML
    private Rectangle GraphShape1;
    @FXML
    private Rectangle GraphShape2;
    @FXML
    private Rectangle GraphShape3;
    @FXML
    private AnchorPane AnchorMain;
    @FXML
    private Rectangle BotRightRec;
    @FXML
    private Line VerticalLine3;
    @FXML
    private Button RewindBtn;
    @FXML
    private Button AdvanceBtn;
    
    //i added this pane because it's gonna be so annoying trying to make it so that
    //the circle stays away from the controls if the circle's parent is the anchorpane itself
    //so i made a separate pane for that and the circle is going to have that as its parent
    @FXML
    private Pane ScenePane;
    
    //i yanked these guys out since they will be super useful
    @Deprecated
	private TimelineWrapper timelineWrapper;
	@Deprecated
	private Circle circle;
	@Deprecated
	private double scale;
	@Deprecated
	private double vVelocity;
	@Deprecated
	private double hVelocity;
	@Deprecated
	private double gravity;
	@Deprecated
	private double timeOfFlight;
	@Deprecated
	private double initialHeight;
	@Deprecated
	private Timeline timeline;
	
	//stuff i added 2024-11-16
	private Physics physics;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	circle = new Circle(50, Color.RED);
		ScenePane.getChildren().add(circle);
    	//can we flip this so that the origin is on the bottom left???
    	ScenePane.setScaleY(-1);//it works this is so good
    	
    	physics = Physics.getInstance();
    	physics.setObject(circle);
    	physics.elapsedTimeProperty().addListener((observable, oldValue, newValue) -> {
    		VerticalDisReading.setText(String.format("%.3f", physics.getVerticalDisplacement()));
    		HorizontalDisReading.setText(String.format("%.3f", physics.getHorizontalDisplacement()));
    		TimeReading.setText(String.format("%.3f", newValue.doubleValue()));
    	});
    	
    	updateFields();
    }

	private void updateFields() {
		VVelocityField1.setText(Double.toString(physics.getStartVerticalVelocity()));
    	HVelocityField.setText(Double.toString(physics.getStartHorizontalVelocity()));
    	HeightField.setText(Double.toString(physics.getStartHeight()));
    	GravAccField.setText(Double.toString(physics.getGravitationAcceleration()));
	}    
    
    @Deprecated //no one should use this. i transported tiba's calculations to the physics class in model
    //except the horizontal and vertical displacements they kinda dont work
    @FXML
    private void handleCalculation(ActionEvent event) throws NumberFormatException {
        try {
            // Get input values from text fields
            vVelocity = Double.parseDouble(VVelocityField1.getText()); // Vy
            hVelocity = Double.parseDouble(HVelocityField.getText()); // Vx
            gravity = Double.parseDouble(GravAccField.getText()); // g
            initialHeight = Double.parseDouble(HeightField.getText()); // h
            circle.setLayoutY(initialHeight  * scale + 50);

            gravity = Math.abs(gravity); // Ensure gravity is positive

            // time of flight = [vy + sqrt(vy^2 + 2*g*h)] / 2 
            double timeOfFlight = (vVelocity + Math.sqrt(Math.pow(vVelocity, 2) + 2 * gravity * initialHeight)) / gravity;
            // Calculate horizontal displacement
            double horizontalDisplacement = hVelocity * timeOfFlight;
            // Calculate vertical displacement
            double verticalDisplacement = initialHeight + vVelocity * timeOfFlight - (0.5 * gravity * Math.pow(timeOfFlight, 2));
            
            // Display results
            HorizontalDisReading.setText(String.format("%.2f", horizontalDisplacement));
            VerticalDisReading.setText(String.format("%.2f", verticalDisplacement));
            TimeReading.setText(String.format("%.2f", timeOfFlight));
            
        	timelineWrapper = new TimelineWrapper(circle, vVelocity, hVelocity, gravity, timeOfFlight, scale);
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numerical values.");
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    private void handleGraphBtn(ActionEvent event) throws IOException {
    	physics.pause();
    	try {
			physics.getTimeOfFlight();
			TermProject.setRoot("TermProjectFXMLSecondary");
		} catch (IllegalArgumentException | IllegalStateException e) {
			showAlert("Invalid Input", e.getMessage());
		}
    }

    @FXML
    private void handleStartBtn(ActionEvent event) {
    	physics.play();
    }

    @FXML
    private void handleStopBtn(ActionEvent event) {
    	physics.pause();
    }

    @FXML
    private void handleResetBtn(ActionEvent event) {
    	physics.reset();
    }

    @FXML
    private void handleRewindBtn(ActionEvent event) {
        try {
            double input = Double.valueOf(RewindToField.getText());
            if (physics.getElapsedTime() >= input) {
                physics.jumpTo(input);
            } else {
                showAlert("Invalid Input", "Rewind value must be less than or equal to the current elapsed time.");
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid number for Rewind.");
        }
    }

    @FXML
    private void handleAdvanceBtn(ActionEvent event) {
        try {
            double input = Double.valueOf(AdvanceToField.getText());
            if (physics.getElapsedTime() <= input) {
                physics.jumpTo(input);
            } else {
                showAlert("Invalid Input", "Advance value must be greater than or equal to the current elapsed time.");
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid number for Advance.");
        }
    }

    
    @Deprecated //kinda useless when u already have hvelocity and vvelocity
    @FXML
	private void setAngle(ActionEvent event) {
//    	physics.setStartAngle(Double.valueOf(AngleField.getText()));
	}
    
    @FXML
    private void setHorizontalVelocity(ActionEvent event) {
        try {
            physics.setStartHorizontalVelocity(Double.valueOf(HVelocityField.getText()));
            updateFields();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid number for Horizontal Velocity.");
        }
    }

    @FXML
    private void setVerticalVelocity(ActionEvent event) {
        try {
            physics.setStartVerticalVelocity(Double.valueOf(VVelocityField1.getText()));
            updateFields();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid number for Vertical Velocity.");
        }
    }

    @FXML
    private void setHeight(ActionEvent event) {
        try {
            Double valueOf = Double.valueOf(HeightField.getText());
            if (valueOf >= 0) {
            	physics.setStartHeight(valueOf);
            	updateFields();
            }
            else
            	showAlert("Invalid Input", "Please enter a positive number for Height.");
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid number for Height.");
        }
    }

    @FXML
    private void setGrav(ActionEvent event) {
        try {
            physics.setGravitationAcceleration(Double.valueOf(GravAccField.getText()));
            updateFields();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid number for Gravitational Acceleration.");
        }
    }
    
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
}
