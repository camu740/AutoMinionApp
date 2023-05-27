package autominion.views.ventas;

import java.io.IOException;
import java.math.BigDecimal;
import org.hibernate.Session;

import autominion.database.persistence.entities.Customers;
import autominion.database.persistence.entities.Salesemployees;
import autominion.database.persistence.entities.Salesproposal;
import autominion.database.persistence.entities.Vehicles;
import autominion.database.persistence.entities.composed_id.SalesproposalId;
import autominion.database.services.implementations.CustomerManagementServiceImpl;
import autominion.database.services.implementations.SalesproposalManagementServiceImpl;
import autominion.database.services.implementations.VehicleManagementServiceImpl;
import autominion.database.services.interfaces.CustomerManagementServiceI;
import autominion.database.services.interfaces.SalesproposalManagementServiceI;
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
import javafx.stage.Stage;

public class NewProposalController {
	@FXML
	private Label lblPrice1;
	@FXML
	private Label lblCustomer;
	@FXML
	private ChoiceBox<String> cbVehicles;
	@FXML
	private TextArea txtDetails;
	@FXML
	private ChoiceBox<String> cbCustomer;
	@FXML
	private Button btnNewProposal;
	@FXML
	private Label lblPrice;
	@FXML
	private TextField txtPrice;
	@FXML
	private Label lblVehiculo;
	@FXML
	private Label lblPrice11;
	@FXML
	private Button btnNewSale;
	@FXML
	private TextArea txtDetails1;

	private BorderPane rootLayout;
	private Stage primaryStage;
	private Session session;

	private ObservableList<String> boxVehicles = FXCollections.observableArrayList("Seleccionar un vehículo...");
	private ObservableList<String> boxCustomers = FXCollections.observableArrayList("Seleccionar un cliente...");
	
	private Salesemployees salesEmployee;
	
	public void setSalesEmployee(Salesemployees sales) {
		this.salesEmployee = sales;
		
	}

	public void setStage(Stage primaryStage) {
		this.primaryStage = primaryStage;

	}
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
		cbCustomer.setItems(boxCustomers);
		cbCustomer.setValue(boxCustomers.get(0));

		actionsButtons();

	}

	private void actionsButtons() {
		EventHandler<ActionEvent> proposal = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				createProposal(false);
			}
		};
		btnNewProposal.setOnAction(proposal);

		EventHandler<ActionEvent> sale = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				createProposal(true);
			}
		};
		btnNewSale.setOnAction(sale);
	}

	/**
	 * 
	 * @param sold verdadero si es una venta, false si es una propuesta
	 */
	private void createProposal(boolean sold) {

		try {

			String[] vehicleSplit = cbVehicles.getValue().split("-");
			VehicleManagementServiceI vehicleService = new VehicleManagementServiceImpl(session);
			String vehicleId = vehicleSplit[0].substring(0, vehicleSplit[0].length() - 3);
			Vehicles vehicle = vehicleService.searchByRegistration(vehicleId);
			
			String[] customerSplit = cbCustomer.getValue().split("-");
			CustomerManagementServiceI customerService = new CustomerManagementServiceImpl(session);
			Long customerId = Long.parseLong(customerSplit[0].substring(0, customerSplit[0].length() - 1));
			Customers customer = customerService.searchById(customerId);

			java.sql.Date proposalDate = new java.sql.Date(new java.util.Date().getTime());
			
			
			System.out.println(vehicleId);
			System.out.println(customerId);
			System.out.println(salesEmployee.getId());

			SalesproposalId proposalId = new SalesproposalId(salesEmployee.getId(), customer.getId(), vehicle.getId(), proposalDate);

			BigDecimal price = new BigDecimal(txtPrice.getText());
			
			SalesproposalManagementServiceI proposalService = new SalesproposalManagementServiceImpl(session);


			if (sold) {
				proposalService.insertNewSalesproposal(
						new Salesproposal(proposalId, customer, salesEmployee, vehicle, price, sold, proposalDate));
				
				mostrarAlertCorrect();
				
				goSalesView();

				
			}else {
				proposalService.insertNewSalesproposal(
						new Salesproposal(proposalId, customer, salesEmployee, vehicle, price));
				
				mostrarAlertCorrect();
				
				goProposalView();

			}

		} catch (Exception e) {
			mostrarAlertError();
		}
	}

	private void goProposalView() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/ventas/VentasView.fxml"));
			AnchorPane root = loader.load();
			VentasController controller = loader.getController();
			controller.setSession(session);
			controller.setSalesEmployee(salesEmployee);
			controller.setRootLayout(rootLayout);
			controller.setStage(primaryStage);

			controller.verTarjetas(controller.obtenerPropuestas());

			rootLayout.setCenter(root);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void goSalesView() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/ventas/VentasView.fxml"));
			AnchorPane root = loader.load();
			VentasController controller = loader.getController();
			controller.setSession(session);
			controller.setSalesEmployee(salesEmployee);
			controller.setRootLayout(rootLayout);
			controller.setStage(primaryStage);

			
			controller.verTarjetas(controller.obtenerVentas());
			
			rootLayout.setCenter(root);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setBoxes() {
		VehicleManagementServiceI vehicleService = new VehicleManagementServiceImpl(this.session);

		for (Vehicles vehiculo : vehicleService.searchAll()) {
			boxVehicles.add(
					vehiculo.getRegistration() + " \t - \t " + vehiculo.getBrand() + " \t - \t " + vehiculo.getModel());
		}

		CustomerManagementServiceI customerService = new CustomerManagementServiceImpl(session);

		for (Customers customer : customerService.searchAll()) {
			boxCustomers.add(customer.getId() + " - " + customer.getName() + " " + customer.getSurname1() + " "
					+ customer.getSurname2());
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
		alert.setTitle("Propuesta creada");
		alert.setContentText("Se ha creado correctamente.");
		alert.showAndWait();
	}
}
