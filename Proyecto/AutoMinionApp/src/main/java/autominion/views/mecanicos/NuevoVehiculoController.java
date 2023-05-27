package autominion.views.mecanicos;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;

import autominion.database.persistence.entities.Concessionaires;
import autominion.database.persistence.entities.Customers;
import autominion.database.persistence.entities.Vehicles;
import autominion.database.services.implementations.ConcessionaireManagementServiceImpl;
import autominion.database.services.implementations.CustomerManagementServiceImpl;
import autominion.database.services.implementations.VehicleManagementServiceImpl;
import autominion.database.services.interfaces.ConcessionaireManagementServiceI;
import autominion.database.services.interfaces.CustomerManagementServiceI;
import autominion.database.services.interfaces.VehicleManagementServiceI;
import autominion.views.ventas.VehiculosController;
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

public class NuevoVehiculoController {
	public Button getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(Button btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

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
		EventHandler<ActionEvent> eventNew = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				createNewVehicle();
			}
		};
		btnGuardar.setOnAction(eventNew);
		
		EventHandler<ActionEvent> eventDelete = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				deleteVehicle();
			}
		};
		btnEliminar.setOnAction(eventDelete);
	}
	
	private void deleteVehicle() {
		VehicleManagementServiceI vehicleService = new VehicleManagementServiceImpl(this.session);

		vehicleService.deleteVehicle(vehiculo);
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Vehículo eliminado");
		alert.setContentText("Se ha eliminado el vehículo correctamente.");
		alert.showAndWait();
		
		goVehiculosVentas();
	}

	private void createNewVehicle() {
		try {
			String[] customerSplit = cbCustomer.getValue().split("-");
			Long idCustomer = Long.parseLong(customerSplit[0].substring(0, customerSplit[0].length() - 1));
			CustomerManagementServiceI customerService = new CustomerManagementServiceImpl(session);
			Customers customer = customerService.searchById(idCustomer);

			BigDecimal km = new BigDecimal(txtKm.getText());
			Integer fabricationYear = Integer.parseInt(txtFabricationYear.getText());
			Integer numberDoor = Integer.parseInt(txtNdoor.getText());
			Integer numberSeats = Integer.parseInt(txtNseat.getText());

			String vehicleType = cbVehicleType.getValue();
			String brand = txtMarca.getText();
			String model = txtModelo.getText();
			String registration = txtRegistration.getText();
			String color = txtColor.getText();
			String combustion = cbCombustion.getValue();
			String drivingType = cbDrivingType.getValue();

			java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());

			Long idConcessionaire = 2L;
			ConcessionaireManagementServiceI concessionaireService = new ConcessionaireManagementServiceImpl(session);
			Concessionaires concessionaire = concessionaireService.searchById(idConcessionaire);

			VehicleManagementServiceI vehicleService = new VehicleManagementServiceImpl(this.session);

			vehicleService.insertNewVehicle(new Vehicles(concessionaire, customer, vehicleType, brand, model,
					registration, color, fabricationYear, km, numberDoor, numberSeats, combustion, drivingType, date));

			mostrarAlertCorrect();

			if (mecanicos) {
				goMecanicosButtonsController();
			} else {
				goVehiculosVentas();
			}

		} catch (Exception e) {
			mostrarAlertError();
		}
	}

	protected void goMecanicosButtonsController() {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/autominion/views/mecanicos/MecanicosButtonsView.fxml"));
		AnchorPane root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Give the controller access to the main app.
		MecanicosButtonsController controller = loader.getController();
		controller.setSession(session);
		// controller.setStage(stage);
		controller.setRootLayout(rootLayout);

		rootLayout.setCenter(root);
	}

	protected void goVehiculosVentas() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/ventas/VehiculosView.fxml"));
		AnchorPane root;
		try {
			root = loader.load();
			VehiculosController controller = loader.getController();
			controller.setSession(session);
			controller.setRootLayout(rootLayout);
			controller.setPrimaryStage(primaryStage);
			controller.verTarjetas(controller.obtenerVehiculos());
			EventHandler<ActionEvent> eventNew = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					newVehiculo(session, rootLayout);
				}
			};
			controller.btnNewVehiculo.setOnAction(eventNew);

			rootLayout.setCenter(root);

			// PrimaryStage quitado dado que da fallo

//			primaryStage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void newVehiculo(Session session, BorderPane rootLayout) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/ventas/NewVehiculoView.fxml"));
			AnchorPane root = loader.load();
			NuevoVehiculoController controller = loader.getController();
			controller.setSession(session);
			controller.setRootLayout(rootLayout);
			controller.setPrimaryStage(primaryStage);
			controller.setBoxes();
			controller.setMecanicos(false);

			rootLayout.setCenter(root);

//			primaryStage.show();
		} catch (Throwable e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	private void mostrarAlertError() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Campos inválidos");
		alert.setContentText("Compruebe que los campos introducidos sean válidos.");
		alert.showAndWait();
	}

	@FXML
	private void mostrarAlertCorrect() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Vehículo creado");
		alert.setContentText("Se ha creado el nuevo vehículo correctamente.");
		alert.showAndWait();
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