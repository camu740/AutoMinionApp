package autominion.views.jefes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;

import autominion.database.persistence.entities.Customers;
import autominion.database.persistence.entities.Vehicles;
import autominion.database.services.implementations.CustomerManagementServiceImpl;
import autominion.database.services.implementations.VehicleManagementServiceImpl;
import autominion.database.services.interfaces.CustomerManagementServiceI;
import autominion.database.services.interfaces.VehicleManagementServiceI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class NewVehiculoController {

	@FXML
	private Label lblCliente;
	@FXML
	private Label lblVehicleType;
	@FXML
	private Label lblRegistration;
	@FXML
	private Label lblMarca;
	@FXML
	private Label lblModelo;
	@FXML
	private Label lblKm;
	@FXML
	private TextField txtMarca;
	@FXML
	private TextField txtModelo;
	@FXML
	private TextField txtRegistration;
	@FXML
	private TextField txtKm;
	@FXML
	private ChoiceBox<String> cbVehicleType;
	@FXML
	private Label lblCombustion;
	@FXML
	private ChoiceBox<String> cbCombustion;
	@FXML
	private Label lblDrivingType;
	@FXML
	private ChoiceBox<String> cbDrivingType;
	@FXML
	private Label lblColor;
	@FXML
	private TextField txtColor;
	@FXML
	private ChoiceBox<String> cbCustomer;
	@FXML
	private Label lblNdoor;
	@FXML
	private Label lblNseat;
	@FXML
	private TextField txtNdoor;
	@FXML
	private TextField txtNseat;
	@FXML
	private Label lblFabricationYear;
	@FXML
	private TextField txtFabricationYear;
	@FXML
	private Button btnFoto;
	@FXML
	private Button btnGuardar;
	@FXML
	private Button btnNewCustomer;
	@FXML
	private Button btnEliminar;
	@FXML
	private TextField txtTipoVehiculo;
	@FXML
	private TextField txtTipoConduccion;
	@FXML
	private TextField txtCombustion;
	@FXML
	private TextField txtCliente;
    @FXML
    private Button btnVolver;
    
	private BorderPane rootLayout;
	private Stage primaryStage;
	private Session session;
	private boolean mecanicos = true;
	private Vehicles vehiculo;

	private ObservableList<String> boxCustomers = FXCollections.observableArrayList("Seleccionar un cliente...");
	private ObservableList<String> boxVehicleType = FXCollections.observableArrayList("Tipo de vehiculo...");
	private ObservableList<String> boxCombustion = FXCollections.observableArrayList("Tipo de combustión...");
	private ObservableList<String> boxDrivingType = FXCollections.observableArrayList("Tipo de conducción...");
	List<String> vehicleType = Arrays.asList("Coche", "Motocicleta", "Ciclomotor");
	List<String> combustionType = Arrays.asList("Gasolina", "Diesel", "Electric", "Hybrid");
	List<String> drivingType = Arrays.asList("Manual", "Automatico");
	List<Customers> customers = new ArrayList<>();

	public BorderPane getRootLayout() {
		return rootLayout;
	}

	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public boolean isMecanicos() {
		return mecanicos;
	}

	public void setMecanicos(boolean mecanicos) {
		this.mecanicos = mecanicos;
	}

	@FXML
	private void initialize() {
		cbCustomer.setItems(boxCustomers);
		cbCustomer.setValue(boxCustomers.get(0));
		cbVehicleType.setItems(boxVehicleType);
		cbVehicleType.setValue(boxVehicleType.get(0));
		cbCombustion.setItems(boxCombustion);
		cbCombustion.setValue(boxCombustion.get(0));
		cbDrivingType.setItems(boxDrivingType);
		cbDrivingType.setValue(boxDrivingType.get(0));

		actionsButtons();
	}
	
	public void setBoxes() {
		CustomerManagementServiceI service = new CustomerManagementServiceImpl(session);
		this.customers = service.searchAll();

		for (Customers customer : customers) {
			boxCustomers.add(customer.getId() + " - " + customer.getName() + " " + customer.getSurname1() + " "
					+ customer.getSurname2());
		}

		for (String type : vehicleType) {
			boxVehicleType.add(type);
		}

		for (String type : combustionType) {
			boxCombustion.add(type);
		}

		for (String type : drivingType) {
			boxDrivingType.add(type);
		}
	}

	
	private void actionsButtons() {

		EventHandler<ActionEvent> eventDelete = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				deleteVehicle();
			}
		};
		btnEliminar.setOnAction(eventDelete);
		
		EventHandler<ActionEvent> eventReturn = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				goVehiculosJefes();
			}
		};
		btnVolver.setOnAction(eventReturn);
	}

	private void deleteVehicle() {
		VehicleManagementServiceI vehicleService = new VehicleManagementServiceImpl(this.session);

		vehicleService.deleteVehicle(vehiculo);

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Vehículo eliminado");
		alert.setContentText("Se ha eliminado el vehículo correctamente.");
		alert.showAndWait();

		goVehiculosJefes();
	}

	protected void goVehiculosJefes() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/jefes/VehiculosJefeView.fxml"));
		AnchorPane root;
		try {
			root = loader.load();
			VehiculosJefeController controller = loader.getController();
			controller.setSession(session);
			controller.setRootLayout(rootLayout);
			controller.setPrimaryStage(primaryStage);
			controller.verTarjetas(controller.ordenarVehiculosFechas());

			rootLayout.setCenter(root);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public TextField getTxtMarca() {
		return txtMarca;
	}

	public void setTxtMarca(TextField txtMarca) {
		this.txtMarca = txtMarca;
	}

	public TextField getTxtModelo() {
		return txtModelo;
	}

	public void setTxtModelo(TextField txtModelo) {
		this.txtModelo = txtModelo;
	}

	public TextField getTxtRegistration() {
		return txtRegistration;
	}

	public void setTxtRegistration(TextField txtRegistration) {
		this.txtRegistration = txtRegistration;
	}

	public TextField getTxtKm() {
		return txtKm;
	}

	public void setTxtKm(TextField txtKm) {
		this.txtKm = txtKm;
	}

	public TextField getTxtColor() {
		return txtColor;
	}

	public void setTxtColor(TextField txtColor) {
		this.txtColor = txtColor;
	}

	public TextField getTxtNdoor() {
		return txtNdoor;
	}

	public void setTxtNdoor(TextField txtNdoor) {
		this.txtNdoor = txtNdoor;
	}

	public TextField getTxtNseat() {
		return txtNseat;
	}

	public void setTxtNseat(TextField txtNseat) {
		this.txtNseat = txtNseat;
	}

	public TextField getTxtFabricationYear() {
		return txtFabricationYear;
	}

	public void setTxtFabricationYear(TextField txtFabricationYear) {
		this.txtFabricationYear = txtFabricationYear;
	}

	public ChoiceBox<String> getCbVehicleType() {
		return cbVehicleType;
	}

	public void setCbVehicleType(ChoiceBox<String> cbVehicleType) {
		this.cbVehicleType = cbVehicleType;
	}

	public ChoiceBox<String> getCbCombustion() {
		return cbCombustion;
	}

	public void setCbCombustion(ChoiceBox<String> cbCombustion) {
		this.cbCombustion = cbCombustion;
	}

	public ChoiceBox<String> getCbDrivingType() {
		return cbDrivingType;
	}

	public void setCbDrivingType(ChoiceBox<String> cbDrivingType) {
		this.cbDrivingType = cbDrivingType;
	}

	public ChoiceBox<String> getCbCustomer() {
		return cbCustomer;
	}

	public void setCbCustomer(ChoiceBox<String> cbCustomer) {
		this.cbCustomer = cbCustomer;
	}

	public TextField getTxtTipoVehiculo() {
		return txtTipoVehiculo;
	}

	public void setTxtTipoVehiculo(TextField txtTipoVehiculo) {
		this.txtTipoVehiculo = txtTipoVehiculo;
	}

	public TextField getTxtTipoConduccion() {
		return txtTipoConduccion;
	}

	public void setTxtTipoConduccion(TextField txtTipoConduccion) {
		this.txtTipoConduccion = txtTipoConduccion;
	}

	public TextField getTxtCombustion() {
		return txtCombustion;
	}

	public void setTxtCombustion(TextField txtCombustion) {
		this.txtCombustion = txtCombustion;
	}

	public TextField getTxtCliente() {
		return txtCliente;
	}

	public void setTxtCliente(TextField txtCliente) {
		this.txtCliente = txtCliente;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Vehicles getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehicles vehiculo) {
		this.vehiculo = vehiculo;
	}
}