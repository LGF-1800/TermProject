
package termproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class TermProject extends Application {

    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("TermProjectFXMLMain.fxml"));
        
        Parent root2 = FXMLLoader.load(getClass().getResource("TermProjectFXMLSecondary.fxml"));
        
        Scene scene = new Scene(root);
        
        Scene scene2 = new Scene(root2);
        
        stage.setScene(scene);
        
        stage.show();
    }
    
}
