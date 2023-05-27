package autominion.views.jefes;

import org.hibernate.Session;

import autominion.database.persistence.entities.Vehicles;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TarjetasRecientesController {
	@FXML // fx:id="gridPane"
	private GridPane gridPane; // Value injected by FXMLLoader
	@FXML // fx:id="image"
	private ImageView image; // Value injected by FXMLLoader
	@FXML // fx:id="lblInfo"
	private Text lblInfo; // Value injected by FXMLLoader
	@FXML // fx:id="lblFecha"
	private Text lblFecha; // Value injected by FXMLLoader
	@FXML // fx:id="btnInfo"
	private Button btnInfo; // Value injected by FXMLLoader
	private Vehicles vehiculo;
	private Session session;
	private BorderPane rootLayout;
	private Stage primaryStage;

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

	public Vehicles getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehicles vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public BorderPane getRootLayout() {
		return rootLayout;
	}

	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@FXML
	private void initialize() {
		actionButtons();
	}

	private void actionButtons() {
		EventHandler<ActionEvent> eventInfo = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					info();
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
			}
		};
		btnInfo.setOnAction(eventInfo);
	}

	private void info() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/jefes/NewVehiculoView.fxml"));
			AnchorPane root = loader.load();
			NewVehiculoController controller = loader.getController();
			controller.setSession(session);
			controller.setRootLayout(rootLayout);
			controller.setPrimaryStage(primaryStage);
			controller.setVehiculo(vehiculo);
			controller.setBoxes();
			controller.setMecanicos(false);

			enviarCampos(controller);

			rootLayout.setCenter(root);

//			primaryStage.show();
		} catch (Throwable e1) {
			e1.printStackTrace();
		}
	}

	private void enviarCampos(NewVehiculoController controller) {
		controller.getTxtRegistration().setText(vehiculo.getRegistration());
		controller.getTxtColor().setText(vehiculo.getColor());
		controller.getTxtMarca().setText(vehiculo.getBrand());
		controller.getTxtKm().setText(vehiculo.getKm() + "");
		controller.getTxtNdoor().setText(vehiculo.getNumberDoor() + "");
		controller.getTxtNseat().setText(vehiculo.getNumberSeat() + "");
		controller.getTxtModelo().setText(vehiculo.getModel());
		controller.getTxtFabricationYear().setText(vehiculo.getFabricationYear() + "");

		controller.getCbCombustion().setVisible(false);
		controller.getCbDrivingType().setVisible(false);
		controller.getCbCustomer().setVisible(false);
		controller.getCbVehicleType().setVisible(false);

		// Si el vehiculo no tiene un cliente asociado
		if (vehiculo.getCustomers() != null) {
			controller.getTxtCliente().setText(vehiculo.getCustomers().getName());
		} else {
			controller.getTxtCliente().setText("Sin cliente asociado");
		}

		controller.getTxtCombustion().setText(vehiculo.getCombustion());
		controller.getTxtTipoConduccion().setText(vehiculo.getDrivingType());
		controller.getTxtTipoVehiculo().setText(vehiculo.getVehicleType());

		controller.getTxtRegistration().setDisable(true);
		controller.getTxtColor().setDisable(true);
		controller.getTxtMarca().setDisable(true);
		controller.getTxtKm().setDisable(true);
		controller.getTxtNdoor().setDisable(true);
		controller.getTxtNseat().setDisable(true);
		controller.getTxtModelo().setDisable(true);
		controller.getTxtFabricationYear().setDisable(true);
	}
}
