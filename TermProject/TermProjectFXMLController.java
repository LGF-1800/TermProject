package termproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import termproject.Physics;
import termproject.TimelineWrapper;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
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
    private void handleEnterTimeBtn(ActionEvent event) {
        try {
            double input = Double.valueOf(EnterTimeField.getText());
            physics.jumpTo(input);
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid number for Rewind.");
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

}
