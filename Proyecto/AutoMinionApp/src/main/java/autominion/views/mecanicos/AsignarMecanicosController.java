package autominion.views.mecanicos;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;

import autominion.database.persistence.entities.Customers;
import autominion.database.persistence.entities.Mechanics;
import autominion.database.persistence.entities.Repairs;
import autominion.database.persistence.entities.Vehicles;
import autominion.database.persistence.entities.composed_id.RepairsId;
import autominion.database.services.implementations.MechanicsManagementServiceImpl;
import autominion.database.services.implementations.RepairsManagementServiceImpl;
import autominion.database.services.implementations.VehicleManagementServiceImpl;
import autominion.database.services.interfaces.MechanicsManagementServiceI;
import autominion.database.services.interfaces.RepairsManagementServiceI;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class AsignarMecanicosController {

	@FXML
	private Label lblVehiculo;

	@FXML
	private Label lblMecanico;

	@FXML
	private Button btnAsignar;

	@FXML
	private ChoiceBox<String> cbVehicles;

	@FXML
	private ChoiceBox<String> cbMechanics;

	@FXML
	private TextArea txtPiezas;

	@FXML
	private Label lblPartsList;

	@FXML
	private Label lblEstimatedTime;

	@FXML
	private Label lblEstimatedBudget;

	@FXML
	private Label lblPriority;

	@FXML
	private Label lblDetails;

	@FXML
	private TextArea txtDetails;

	@FXML
	private TextField txtEstimatedTime;

	@FXML
	private TextField txtEstimatedBudget;

	@FXML
	private ChoiceBox<String> cbPriority;

	private Session session;
	private BorderPane rootLayout;

	private ObservableList<String> boxVehicles = FXCollections.observableArrayList("Seleccionar un vehículo...");
	private ObservableList<String> boxMechanics = FXCollections.observableArrayList("Seleccionar un mecánico...");
	private ObservableList<String> boxPriority = FXCollections.observableArrayList("Seleccionar prioridad...");
	List<String> priority = Arrays.asList("Alta", "Media", "Baja");

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
	@FXML
	private void initialize() {
		cbVehicles.setItems(boxVehicles);
		cbVehicles.setValue(boxVehicles.get(0));
		cbMechanics.setItems(boxMechanics);
		cbMechanics.setValue(boxMechanics.get(0));
		cbPriority.setItems(boxPriority);
		cbPriority.setValue(boxPriority.get(0));

		actionsButtons();

	}

	private void actionsButtons() {
		EventHandler<ActionEvent> eventNew = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				createNewRepair();
			}
		};
		btnAsignar.setOnAction(eventNew);
	}

	public void createNewRepair() {
		try {
			//java.sql.Date requestDate = new java.sql.Date(new java.util.Date().getTime());

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    String requestDate = sdf.format(timestamp);
			
			String[] vehicleSplit = cbVehicles.getValue().split("-");
			VehicleManagementServiceI vehicleService = new VehicleManagementServiceImpl(session);
			String vehicleId = vehicleSplit[0].substring(0, vehicleSplit[0].length() - 3);
			Vehicles vehicle = vehicleService.searchByRegistration(vehicleId);

			String[] mechanicSplit = cbMechanics.getValue().split("-");
			MechanicsManagementServiceI mechanicService = new MechanicsManagementServiceImpl(session);
			Long mechanicId = Long.parseLong(mechanicSplit[0].substring(0, mechanicSplit[0].length() - 1));
			Mechanics mechanic = mechanicService.searchById(mechanicId);

			Customers customer = vehicle.getCustomers();

			BigDecimal budget = new BigDecimal(txtEstimatedBudget.getText());

			BigDecimal time = new BigDecimal(txtEstimatedTime.getText());

			String details = txtDetails.getText();

			String partsList = txtPiezas.getText();

			RepairsId repairsId = new RepairsId(mechanic.getId(), vehicle.getId(), requestDate);

			String priority = cbPriority.getValue();

			RepairsManagementServiceI repairService = new RepairsManagementServiceImpl(session);
			repairService.insertNewRepair(new Repairs(repairsId, customer, budget, time, details, priority, partsList));

			mostrarAlertCorrect();

			goMecanicosButtonsController();

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

	public void setBoxes() {
		VehicleManagementServiceI v = new VehicleManagementServiceImpl(this.session);

		for (Vehicles vehiculo : v.searchAll()) {
			boxVehicles.add(
					vehiculo.getRegistration() + " \t - \t " + vehiculo.getBrand() + " \t - \t " + vehiculo.getModel());
		}

		MechanicsManagementServiceI m = new MechanicsManagementServiceImpl(this.session);

		for (Mechanics mecanico : m.searchAll()) {
			boxMechanics.add(mecanico.getId() + " - " + mecanico.getEmployees().getName() + " "
					+ mecanico.getEmployees().getSurname1());
		}

		for (String type : priority) {
			boxPriority.add(type);
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
		alert.setTitle("Reparación asignada");
		alert.setContentText("Se ha creado la nueva reparación correctamente.");
		alert.showAndWait();
	}
}
