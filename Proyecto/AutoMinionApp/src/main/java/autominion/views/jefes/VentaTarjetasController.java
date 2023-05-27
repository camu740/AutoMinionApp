package autominion.views.jefes;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class VentaTarjetasController {
    @FXML // fx:id="gridPane"
    private GridPane gridPane; // Value injected by FXMLLoader
    @FXML // fx:id="image"
    private ImageView image; // Value injected by FXMLLoader
    @FXML // fx:id="lblInfo"
    private Text lblInfo; // Value injected by FXMLLoader
    @FXML // fx:id="lblFecha"
    private Text lblFecha; // Value injected by FXMLLoader
    
	public GridPane getGridPane() {
		return gridPane;
	}
	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
	}
	public ImageView getImage() {
		return image;
	}
	public void setImage(ImageView image) {
		this.image = image;
	}
	public Text getLblInfo() {
		return lblInfo;
	}
	public void setLblInfo(Text lblInfo) {
		this.lblInfo = lblInfo;
	}
	public Text getLblFecha() {
		return lblFecha;
	}
	public void setLblFecha(Text lblFecha) {
		this.lblFecha = lblFecha;
	}
}