package com.vanier.TermProject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.vanier.TermProject.model.Physics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
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
    private LineChart<String, Number> Graph;
    @FXML
    private LineChart<String, Number> Graph1;
    @FXML
    private LineChart<String, Number> Graph2;
    @FXML
    private LineChart<String, Number> Graph3;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	Series<String, Number> verticalVelocitySeries = new Series<String, Number>();
    	Series<String, Number> horizontalVelocitySeries = new Series<String, Number>();
        Series<String, Number> verticalDisplacementSeries = new Series<String, Number>();
        Series<String, Number> horizontalDisplacementSeries = new Series<String, Number>();

        Physics instance = Physics.getInstance();
		double timeOfFlight = instance.getTimeOfFlight();

        for (double time = 0; time <= timeOfFlight; time += 0.1) {
        	double verticalVelocity = instance.calculateVerticalVelocity(time);
        	verticalVelocitySeries.getData().add(new Data<String, Number>(String.format("%.2f", time), verticalVelocity));
        	
        	double horizontalVelocity = instance.getStartHorizontalVelocity();
        	horizontalVelocitySeries.getData().add(new Data<String, Number>(String.format("%.2f", time), horizontalVelocity));
            
            double verticalDisplacement = instance.calculateY(time);
            verticalDisplacementSeries.getData().add(new Data<String, Number>(String.format("%.2f", time), verticalDisplacement));
        
            double horizontalDisplacement = instance.calculateX(time);
            horizontalDisplacementSeries.getData().add(new Data<String, Number>(String.format("%.2f", time), horizontalDisplacement));
        }
        
        Graph.getData().add(verticalVelocitySeries);
        Graph2.getData().add(horizontalVelocitySeries);
        Graph1.getData().add(verticalDisplacementSeries);
        Graph3.getData().add(horizontalDisplacementSeries);
    }


   

    @FXML
    private void handGraphBtnSecond(ActionEvent event) throws IOException {
    	TermProject.setRoot("TermProjectFXMLMain");
    }
    
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
