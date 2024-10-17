
package termproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
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
    private Label ParamText;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
