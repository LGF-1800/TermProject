package termproject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import termproject.Physics;
import termproject.TimelineWrapper;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TermProjectFXMLController implements Initializable {

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
    private TextField VVelocityField1;
    @FXML
    private Label VVelocityLabel1;
    @FXML
    private TextField HVelocityField;
    @FXML
    private Label HVelocityLabel;
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
    private Rectangle VerticalDisRec;
    @FXML
    private Label VerticalDisLabel;
    @FXML
    private Label VerticalDisReading;
    @FXML
    private Rectangle HorizontalDisRec;
    @FXML
    private Label HorizontalDisLabel;
    @FXML
    private Label HorizontalDisReading;
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
    private Pane ScenePane;
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
    private Physics physics;
    @FXML
    private Rectangle EnterTimeRec;
    @FXML
    private Label EnterTimeLabel;
    @FXML
    private TextField EnterTimeField;
    @FXML
    private Button EnterTimeBtn;
    @FXML
    private ComboBox<String> VerticalDisComboBox;
    @FXML
    private ComboBox<String> SimTimeComboBox;
    @FXML
    private ComboBox<String> HorizontalDisComboBox;
    @FXML
    private MenuItem MenuFileClose;
    @FXML
    private MenuItem MenuHelpManual;
    @FXML
    private MenuItem MenuEditTheme;
    @FXML
    private Rectangle VVelocityRec;
    @FXML
    private Rectangle HVelocityRec;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        VerticalDisComboBox.setItems(FXCollections.observableArrayList("Kilometer(km)", "Meter(m)", "Centimeter(cm)", "Millimeter(mm)"));
        HorizontalDisComboBox.setItems(FXCollections.observableArrayList("Kilometer(km)", "Meter(m)", "Centimeter(cm)", "Millimeter(mm)"));
        SimTimeComboBox.setItems(FXCollections.observableArrayList("Hour(h)", "Minute(min)", "Second(s)", "Millisecond(ms)"));

        circle = new Circle(50, Color.RED);
        ScenePane.getChildren().add(circle);

        ScenePane.setScaleY(-1);

        physics = Physics.getInstance();
        physics.setObject(circle);
        physics.elapsedTimeProperty().addListener((observable, oldValue, newValue) -> {
            VerticalDisReading.setText(String.format("%.3f", physics.getVerticalDisplacement()));
            HorizontalDisReading.setText(String.format("%.3f", physics.getHorizontalDisplacement()));
            TimeReading.setText(String.format("%.3f", newValue.doubleValue()));
        });

        Tooltip tooltip = new Tooltip();
        circle.setOnMouseEntered(event -> {
            tooltip.setText(String.format("x: %.3f, y: %.3f", physics.calculateX(physics.getElapsedTime()), physics.calculateY(physics.getElapsedTime())));
            tooltip.show(circle, event.getScreenX() + 10, event.getScreenY() + 10);
        });

        circle.setOnMouseMoved(event -> {
            tooltip.setText(String.format("x: %.3f, y: %.3f", physics.calculateX(physics.getElapsedTime()), physics.calculateY(physics.getElapsedTime())));
            tooltip.setX(event.getScreenX() + 10);
            tooltip.setY(event.getScreenY() + 10);
        });

        circle.setOnMouseExited(event -> {
            tooltip.hide();

        });

        updateFields();
    }

    private void updateFields() {
        VVelocityField1.setText(Double.toString(physics.getStartVerticalVelocity()));
        HVelocityField.setText(Double.toString(physics.getStartHorizontalVelocity()));
        HeightField.setText(Double.toString(physics.getStartHeight()));
        GravAccField.setText(Double.toString(physics.getGravitationAcceleration()));
    }

    @FXML
    private void handleGraphBtn(ActionEvent event) throws IOException {
        physics.pause();
        try {
            if ((physics.getGravitationAcceleration() <= 0) || ((physics.getStartHeight() <= 0) && (physics.getStartVerticalVelocity() <= 0))) {
                showAlert("Invalid Input", "Gravitational Acceleration and either the Vertical Velocity or Height fields must be greater than zero.");
            } else {
                physics.getTimeOfFlight();
                TermProject.setRoot("TermProjectFXMLSecondary");
            }
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
    private void handleEnterTimeBtn(ActionEvent event) {
        try {
            double input = Double.valueOf(EnterTimeField.getText());
            if (physics.getElapsedTime() == 0){
                 showAlert("Invalid Input", "Please start the simulation before using Enter Time.");
            }
            else if(input > physics.getTimeOfFlight()){
                 showAlert("Invalid Input", "The simulation will have have already completed at this time.\nPlease enter a valid number for Enter Time.");
            }
            else{
            physics.jumpTo(input);    
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid number for Enter Time.");
        }
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
            } else {
                showAlert("Invalid Input", "Please enter a positive number for Height.");
            }
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

    @FXML
    private void handleVerticalDisComboBox(ActionEvent event) {
        String selectedUnit = VerticalDisComboBox.getValue();
        double verticalDisplacement = physics.getVerticalDisplacement();

        switch (selectedUnit) {
            case "Kilometer(km)":
                verticalDisplacement /= 1000;
                break;
            case "Meter(m)":
                // No conversion needed; it's the base unit.
                break;
            case "Centimeter(cm)":
                verticalDisplacement *= 100;
                break;
            case "Millimeter(mm)":
                verticalDisplacement *= 1000;
                break;
            default:
                showAlert("Invalid Unit", "Please select a valid unit for Vertical Distance.");
                return;
        }

        VerticalDisReading.setText(String.format("%.3f %s", verticalDisplacement, selectedUnit));
    }

    @FXML
    private void handleHorizontalDisComboBox(ActionEvent event) {
        String selectedUnit = HorizontalDisComboBox.getValue();
        double horizontalDisplacement = physics.getHorizontalDisplacement();

        switch (selectedUnit) {
            case "Kilometer(km)":
                horizontalDisplacement /= 1000;
                break;
            case "Meter(m)":
                // No conversion needed; it's the base unit.
                break;
            case "Centimeter(cm)":
                horizontalDisplacement *= 100;
                break;
            case "Millimeter(mm)":
                horizontalDisplacement *= 1000;
                break;
            default:
                showAlert("Invalid Unit", "Please select a valid unit for Horizontal Distance.");
                return;
        }

        HorizontalDisReading.setText(String.format("%.3f %s", horizontalDisplacement, selectedUnit));
    }

    @FXML
    private void handleSimTimeComboBox(ActionEvent event) {
        String selectedUnit = SimTimeComboBox.getValue();
        double simulationTime = physics.getElapsedTime();

        switch (selectedUnit) {
            case "Hour(h)":
                simulationTime /= 3600;
                break;
            case "Minute(min)":
                simulationTime /= 60;
                break;
            case "Second(s)":
                // No conversion needed; it's the base unit.
                break;
            case "Millisecond(ms)":
                simulationTime *= 1000;
                break;
            default:
                showAlert("Invalid Unit", "Please select a valid unit for Simulation Time.");
                return;
        }

        TimeReading.setText(String.format("%.3f %s", simulationTime, selectedUnit));
    }

    @FXML
    private void handleMenuFileClose(ActionEvent event) {
        Platform.exit();
    }
    
    Alert alert = new Alert(AlertType.INFORMATION);
  
    @FXML
    private void handleMenuHelpManual(ActionEvent event) { 
        Alert manual = getAlert();
    }
    
    public Alert getAlert(){
        alert.setTitle("User Manual");
        alert.setHeaderText("2-D Projectile Simulator");
        alert.setHeight(900);
        alert.setWidth(1050);
         String contentText = """
            This Java program is a simulator designed to replicate 2-D projectile motion, allowing you to visualize and analyze the behavior of an object influenced by gravity and initial velocity.
            It serves as an interactive tool for exploring key physics concepts, offering both educational value and hands-on experimentation.
            -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            Parameter Adjustments

            The simulation begins by defining key parameters under the "Parameter Adjustments" section. You can input the following variables in the designated text fields:

            Initial Velocity (Vertical and Horizontal): Define the starting speed in both axes.
            Initial Height: Specify the object's launch height.
            Gravitational Acceleration: Set the acceleration due to gravity affecting the object's motion.

            Ensure all fields are completed accurately before proceeding to the controls.
            -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            Simulation Controls

            Under the "Controls" section, you can interact with the simulation using the following options:

            Start/Stop: Begin or pause the simulation.
            Reset: Restart the simulation to its initial state.
            Advance/Rewind: Move forward or backward to a specific point in the trajectory (if applicable).
            -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            Dynamic Readings

            As the simulation progresses, three real-time readings are displayed at the bottom of the screen:

            Horizontal Displacement: The distance traveled along the x-axis.
            Vertical Displacement: The height relative to the initial position.
            Elapsed Time: The total time since the simulation began.

            Below these readings, you can change the units for displaying these values to suit your preferences.
            -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            Graphical Analysis

            To the right of the readings, the "Graphical Analysis" button allows you to switch to a new scene featuring four line graphs. 
            These graphs plot the following functions over time, based on the most recent input:

            Velocity (Y-axis)
            Velocity (X-axis)
            Displacement (Y-axis)
            Displacement (X-axis)
                              
            You can return to the simulation by pressing the "Projectile Motion Simulation" button at the bottom of the scene.
        """;

        String[] keywords = { "Parameter Adjustments", "Simulation Controls", "Dynamic Readings", "Graphical Analysis", "Projectile Motion Simulation" };

        TextFlow textFlow = new TextFlow();
        for (String paragraph : contentText.split("\n\n")) {
            for (String keyword : keywords) {
                if (paragraph.contains(keyword)) {
                    paragraph = paragraph.replace(keyword, "\0" + keyword + "\0");
                }
            }
            for (String part : paragraph.split("\0")) {
                Text text = new Text(part);
                if (arrayContains(keywords, part)) {
                    text.setStyle("-fx-font-weight: bold;");
                }
                textFlow.getChildren().add(text);
            }
            textFlow.getChildren().add(new Text("\n\n")); 
        }

        alert.getDialogPane().setContent(textFlow);

        alert.showAndWait();
        return alert;
    }

    private boolean arrayContains(String[] array, String value) {
        for (String item : array) {
            if (item.equals(value)) {
                return true;
            }
        }
        return false;
    }
   
    @FXML
    private void handleMenuEditTheme(ActionEvent event) {
        List<String> themes = new ArrayList<>();
        themes.add("Default");
        themes.add("Gray");
        themes.add("Blue");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Default", themes);
        dialog.setTitle("Theme Editor");
        dialog.setHeaderText("Theme Selction");
        dialog.setContentText("Choose your preferred theme:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(selection -> {
            switch (selection) {
                case "Default" -> {
                    Color D = Color.web("d4d4d4");
                    Color B = Color.web("1c8cec");
                    ParamRec.setFill(D);
                    ControlsRec.setFill(D);
                    BotLeftRec.setFill(D);
                    VerticalDisRec.setFill(D);
                    HorizontalDisRec.setFill(D);
                    TimeRec.setFill(D);
                    BotRightRec.setFill(D);
                    VVelocityRec.setFill(B);
                    HVelocityRec.setFill(B);
                    GravAccRec.setFill(B);
                    HeightRec.setFill(B);
                    EnterTimeRec.setFill(B);
                }
                case "Gray" -> {
                    Color D = Color.web("d4d4d4");
                    ParamRec.setFill(D);
                    ControlsRec.setFill(D);
                    BotLeftRec.setFill(D);
                    VerticalDisRec.setFill(D);
                    HorizontalDisRec.setFill(D);
                    TimeRec.setFill(D);
                    BotRightRec.setFill(D);
                    VVelocityRec.setFill(Color.GRAY);
                    HVelocityRec.setFill(Color.GRAY);
                    GravAccRec.setFill(Color.GRAY);
                    HeightRec.setFill(Color.GRAY);
                    EnterTimeRec.setFill(Color.GRAY);

                }
                case "Blue" -> {
                    Color B = Color.web("1c8cec");
                    ParamRec.setFill(Color.SKYBLUE);
                    ControlsRec.setFill(Color.SKYBLUE);
                    BotLeftRec.setFill(Color.SKYBLUE);
                    VerticalDisRec.setFill(Color.SKYBLUE);
                    HorizontalDisRec.setFill(Color.SKYBLUE);
                    TimeRec.setFill(Color.SKYBLUE);
                    BotRightRec.setFill(Color.SKYBLUE);
                    VVelocityRec.setFill(B);
                    HVelocityRec.setFill(B);
                    GravAccRec.setFill(B);
                    HeightRec.setFill(B);
                    EnterTimeRec.setFill(B);
                }
                default -> {
                    Color D = Color.web("d4d4d4");
                    Color B = Color.web("1c8cec");
                    ParamRec.setFill(D);
                    ControlsRec.setFill(D);
                    BotLeftRec.setFill(D);
                    VerticalDisRec.setFill(D);
                    HorizontalDisRec.setFill(D);
                    TimeRec.setFill(D);
                    BotRightRec.setFill(D);
                    VVelocityRec.setFill(B);
                    HVelocityRec.setFill(B);
                    GravAccRec.setFill(B);
                    HeightRec.setFill(B);
                    EnterTimeRec.setFill(B);
                }
            }
        });
    }
}
