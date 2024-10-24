package com.vanier.TermProject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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


   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleGraphBtn(ActionEvent event) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TermProjectFXMLSecondary.fxml"));
            Parent root2 = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene2 = new Scene(root2);
            stage.setScene(scene2);
            stage.show();

        } catch (IOException e) {
            System.out.println("Exception caught.");
        }
    }

    @FXML
    private void handleStartBtn(ActionEvent event) {
    }

    @FXML
    private void handleStopBtn(ActionEvent event) {
    }

    @FXML
    private void handleResetBtn(ActionEvent event) {
    }

    @FXML
    private void handleRewindBtn(ActionEvent event) {
    }

    @FXML
    private void handleAdvanceBtn(ActionEvent event) {
    }

}
