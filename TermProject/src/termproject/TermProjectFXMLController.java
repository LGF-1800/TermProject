
package termproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class TermProjectFXMLController implements Initializable {

    @FXML
    private MenuBar MenuBar;
    @FXML
    private AnchorPane PerpendicularToLeftLine;
    @FXML
    private Line LeftLine;
    @FXML
    private Line OrthogonalToLeftLine;
    @FXML
    private Rectangle VeclocityRec;
    @FXML
    private Rectangle ParamRec;
    @FXML
    private Label ParamLabel;
    @FXML
    private TextField VelocityField;
    @FXML
    private Label VelocityLabel;
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
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
