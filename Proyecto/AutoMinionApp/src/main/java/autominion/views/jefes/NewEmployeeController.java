package autominion.views.jefes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;

import autominion.database.persistence.entities.Employees;
import autominion.database.persistence.entities.Mechanics;
import autominion.database.persistence.entities.Salesemployees;
import autominion.database.services.implementations.EmployeesManagementServiceImpl;
import autominion.database.services.interfaces.EmployeesManagementServiceI;
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

public class NewEmployeeController {

	@FXML
	private Button btnNewEmployee;
	@FXML
	private Label lblNombre;
	@FXML
	private Label lblSurname;
	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtSurname1;
	@FXML
	private Label lblSurname2;
	@FXML
	private TextField txtSurname2;
	@FXML
	private Label lblPhone;
	@FXML
	private Label lblEmail;
	@FXML
	private TextField txtPhone;
	@FXML
	private TextField txtEmail;
	@FXML
	private Label lblAddress;
	@FXML
	private TextField txtAddress;
	@FXML
	private Label lblType;
	@FXML
	private ChoiceBox<String> cbType;
	@FXML
	private Button btnVolver;
	@FXML
    private TextField txtTipo;

	private BorderPane rootLayout;
	private Stage primaryStage;
	private Session session;
	private EmployeesManagementServiceI employeeService;
	private Employees employee;

	private ObservableList<String> boxTypes = FXCollections.observableArrayList("Tipo de empleado...");
	List<String> type = Arrays.asList("Ventas", "Mecánico");

	public ObservableList<String> getBoxTypes() {
		return boxTypes;
	}

	public void setBoxTypes(ObservableList<String> boxTypes) {
		this.boxTypes = boxTypes;
	}

	public Employees getEmployee() {
		return employee;
	}

	public void setEmployee(Employees employee) {
		this.employee = employee;
	}

	public ChoiceBox<String> getCbType() {
		return cbType;
	}

	public void setCbType(ChoiceBox<String> cbType) {
		this.cbType = cbType;
	}

	public TextField getTxtPhone() {
		return txtPhone;
	}

	public void setTxtPhone(TextField txtPhone) {
		this.txtPhone = txtPhone;
	}

	public TextField getTxtSurname2() {
		return txtSurname2;
	}

	public void setTxtSurname2(TextField txtSurname2) {
		this.txtSurname2 = txtSurname2;
	}

	public TextField getTxtSurname1() {
		return txtSurname1;
	}

	public void setTxtSurname1(TextField txtSurname1) {
		this.txtSurname1 = txtSurname1;
	}

	public TextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(TextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public TextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(TextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public TextField getTxtAddress() {
		return txtAddress;
	}

	public void setTxtAddress(TextField txtAddress) {
		this.txtAddress = txtAddress;
	}

	public void setStage(Stage primaryStage) {
		this.primaryStage = primaryStage;

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

	public Button getBtnNewEmployee() {
		return btnNewEmployee;
	}

	public void setBtnNewEmployee(Button btnNewEmployee) {
		this.btnNewEmployee = btnNewEmployee;
	}

	@FXML
	private void initialize() {
		cbType.setItems(boxTypes);
		cbType.setValue(boxTypes.get(0));
		actionsButtons();
	}

	public void setBoxes() {
		for (String t : type) {
			boxTypes.add(t);
		}
	}

	private void actionsButtons() {
		EventHandler<ActionEvent> eventNew = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				createNewEmployee();
			}
		};
		btnNewEmployee.setOnAction(eventNew);

		EventHandler<ActionEvent> eventReturn = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				goEmployeeView();
			}
		};
		btnVolver.setOnAction(eventReturn);
	}

	protected void createNewEmployee() {
		try {
			String name = txtNombre.getText();
			String surname1 = txtSurname1.getText();
			String surname2 = txtSurname2.getText();
			String phone = txtPhone.getText();
			String email = txtEmail.getText();
			String address = txtAddress.getText();
			String type = cbType.getValue();

			employeeService = new EmployeesManagementServiceImpl(session);
			Employees employee = new Employees(name, surname1, surname2, phone, email, address);

			switch (type) {
			case "Ventas":
				employee.setSalesemployees(new Salesemployees(employee));
				break;
			case "Mecánico":
				employee.setMechanics(new Mechanics(employee));
				break;
			default:
				throw new Exception();
			}

			employeeService.insertNewEmployee(employee); 

			mostrarAlertCorrect();

			goEmployeeView();

		} catch (Exception e) {
			mostrarAlertError();
		}
	}

	private void goEmployeeView() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/jefes/EmployeeView.fxml"));
		AnchorPane root;
		try {
			root = loader.load();
			EmployeeController controller = loader.getController();
			controller.setSession(session);
			controller.setRootLayout(rootLayout);
			controller.setStage(primaryStage);
			controller.verEmpleados();

			rootLayout.setCenter(root);
			primaryStage.show();

		} catch (IOException e1) {
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
		alert.setTitle("Empleado creado");
		alert.setContentText("Se ha creado el nuevo empleado correctamente.");
		alert.showAndWait();
	}

	public TextField getTxtTipo() {
		return txtTipo;
	}

	public void setTxtTipo(TextField txtTipo) {
		this.txtTipo = txtTipo;
	}

}