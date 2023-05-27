package autominion.views.ventas;

import java.io.IOException;

import org.hibernate.Session;

import autominion.database.persistence.entities.Customers;
import autominion.database.services.implementations.CustomerManagementServiceImpl;
import autominion.database.services.interfaces.CustomerManagementServiceI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class NewCustomerController {
    @FXML
    private TextField txtPhone;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblAddress;
    @FXML
    private TextField txtSurname2;
    @FXML
    private TextField txtSurname1;
    @FXML
    private TextField txtEmail;
    @FXML
    private Label lblEmail;
    @FXML
    private Button btnNewCustomer;
    @FXML
    private TextField txtNombre;
    @FXML
    private Label lblSurname;
    @FXML
    private TextField txtAddress;
    @FXML
    private Label lblSurname2;
    @FXML
    private Label lblPhone;
	private BorderPane rootLayout;
	private Stage primaryStage;
	private Session session;
	private CustomerManagementServiceI customerService;
	private Customers cliente;
    
	public Customers getCliente() {
		return cliente;
	}
	public void setCliente(Customers cliente) {
		this.cliente = cliente;
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
	
	@FXML
	private void initialize() {
		actionsButtons();
	}

	private void actionsButtons() {
		EventHandler<ActionEvent> eventNew = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				createNewCustomer();
			}
		};
		btnNewCustomer.setOnAction(eventNew);
	}

	public Button getBtnNewCustomer() {
		return btnNewCustomer;
	}
	public void setBtnNewCustomer(Button btnNewCustomer) {
		this.btnNewCustomer = btnNewCustomer;
	}
	protected void createNewCustomer() {
		try {
			String name = txtNombre.getText();
			String surname1 = txtSurname1.getText();
			String surname2 = txtSurname2.getText();
			String phone = txtPhone.getText();
			String email = txtEmail.getText();
			String address = txtAddress.getText();
			
			customerService = new CustomerManagementServiceImpl(session);
			customerService.insertNewCustomer(new Customers(name, surname1, surname2, phone, email, address));
			
			mostrarAlertCorrect();
			
			goCustomersView();
			
		} catch (Exception e) {
			mostrarAlertError();
		}
	}

	private void goCustomersView() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/ventas/ClientesView.fxml"));
		AnchorPane root;
		try {
			root = loader.load();
			ClientesController controller = loader.getController();
			controller.setSession(session);
			controller.setRootLayout(rootLayout);
			controller.setStage(primaryStage);
			controller.verClientes();

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
		alert.setTitle("Cliente creado");
		alert.setContentText("Se ha creado el nuevo cliente correctamente.");
		alert.showAndWait();
	}
}
