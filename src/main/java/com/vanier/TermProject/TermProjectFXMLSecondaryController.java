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
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class TermProjectFXMLSecondaryController implements Initializable {

    @FXML
    private AnchorPane AnchorSecond;
    @FXML
    private MenuBar MenuBarSecond;
    @FXML
    private Line OrthogonalToLeftLine3;
    @FXML
    private Button GraphBtnSecond;
    @FXML
    private Rectangle GraphShapeSecond;
    @FXML
    private Rectangle GraphShapeSecond1;
    @FXML
    private Rectangle GraphShapeSecond2;
    @FXML
    private Rectangle GraphShapeSecond3;
    @FXML
    private Line VerticalLineSecond1;
    @FXML
    private Rectangle BotRightRecSecond;
    @FXML
    private Rectangle BotLeftRecSecond;
    @FXML
    private Line VerticalLineSecond;
    @FXML
    private Line CrossLine1;
    @FXML
    private Line CrossLine;
    @FXML
    private LineChart<?, ?> Graph;
    @FXML
    private LineChart<?, ?> Graph1;
    @FXML
    private LineChart<?, ?> Graph2;
    @FXML
    private LineChart<?, ?> Graph3;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void handGraphBtnSecond(ActionEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TermProjectFXMLMain.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("Exception caught.");
        }
    }
}
