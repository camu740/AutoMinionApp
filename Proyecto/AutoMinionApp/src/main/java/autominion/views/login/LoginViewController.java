package autominion.views.login;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;

import autominion.database.persistence.entities.Director;
import autominion.database.persistence.entities.Employees;
import autominion.database.persistence.entities.Mechanics;
import autominion.database.persistence.entities.Salesemployees;
import autominion.database.services.implementations.DirectorManagementServiceImpl;
import autominion.database.services.implementations.EmployeesManagementServiceImpl;
import autominion.database.services.implementations.MechanicsManagementServiceImpl;
import autominion.database.services.implementations.SalesproposalManagementServiceImpl;
import autominion.database.services.interfaces.DirectorManagementServiceI;
import autominion.database.services.interfaces.EmployeesManagementServiceI;
import autominion.database.services.interfaces.MechanicsManagementServiceI;
import autominion.database.services.interfaces.SalesproposalManagementServiceI;
import autominion.main.AutominionMainApp;
import autominion.utils.SendEmail;
import autominion.views.jefes.EmployeeController;
import autominion.views.jefes.MenuJefesController;
import autominion.views.mecanicos.MecanicosButtonsController;
import autominion.views.mecanicos.MecanicosHomeController;
import autominion.views.mecanicos.NuevoVehiculoController;
import autominion.views.ventas.MenuVentasController;
import autominion.views.ventas.VehiculosController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginViewController {
	private Stage stage;
	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;
	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;
	@FXML // fx:id="image"
	private ImageView imageLogin; // Value injected by FXMLLoader
	@FXML // fx:id="lblUser"
	private Label lblUser; // Value injected by FXMLLoader
	@FXML // fx:id="lblEmail"
	private Label lblEmail; // Value injected by FXMLLoader
	@FXML // fx:id="lblPassword"
	private Label lblPassword; // Value injected by FXMLLoader
	@FXML // fx:id="lblLogin"
	private Label lblTittle; // Value injected by FXMLLoader
	@FXML // fx:id="lblForgot"
	private Label lblDescription; // Value injected by FXMLLoader
	@FXML // fx:id="txtUser"
	private TextField txtUser; // Value injected by FXMLLoader
	@FXML // fx:id="txtPasswd"
	private PasswordField txtPasswd; // Value injected by FXMLLoader
	@FXML // fx:id="txtEmail"
	private TextField txtEmail; // Value injected by FXMLLoader
	@FXML // fx:id="btnNext"
	private Button btnNext; // Value injected by FXMLLoader
	@FXML // fx:id="btnBack"
	private Button btnBack; // Value injected by FXMLLoader
	@FXML // fx:id="btnSign"
	private Button btnSign; // Value injected by FXMLLoader
	@FXML // fx:id="lblForgot"
	private Button btnForgot; // Value injected by FXMLLoader
	@FXML
	private Image imageForgot;
	private Session session;
	private BorderPane rootLayout;
	TilePane tilepane = new TilePane();
	@FXML
	Popup popup = new Popup();

	public void setSession(Session session) {
		this.session = session;
	}

	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}

	public void setStage(Stage primaryStage) {
		stage = primaryStage;
	}

	@FXML
	private void initialize() throws IOException {
		loginView();
		actionsButtons();
	}

	@FXML
	private void mostrarAlertError() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Error");
		alert.setContentText("Error credenciales incorrectas");
		alert.showAndWait();
	}

	private void login() {
		EmployeesManagementServiceI employeeService = new EmployeesManagementServiceImpl(session);

		String user = txtUser.getText();
		String passwd = txtPasswd.getText();

		Employees employee = employeeService.searchByEmail(txtUser.getText());

		// usuario correcto
		if (user.equals(employee.getEmail()) && passwd.equals(employee.getPassword())) {
			checkPermisos(employee);
		} else {
			mostrarAlertError();
		}

	}

	private void checkPermisos(Employees employee) {
		MechanicsManagementServiceI mechanicService = new MechanicsManagementServiceImpl(session);
		SalesproposalManagementServiceI salesService = new SalesproposalManagementServiceImpl(session);
		DirectorManagementServiceI directorService = new DirectorManagementServiceImpl(session);

		Mechanics mechanicBoss = mechanicService.searchBoss();
		Salesemployees sales = salesService.searchById(employee.getId());
		Director director = directorService.searchAll().get(0);

		// comprobamos si es jefe mecánico
		if (employee.getEmail().equals(mechanicBoss.getEmployees().getEmail())
				&& employee.getPassword().equals(mechanicBoss.getEmployees().getPassword())) {
			stage.close();
			initMecanicosHome(session, mechanicBoss.getEmployees().getName());
			// comprobamos si es director
		} else if (employee.getEmail().equals(director.getEmployees().getEmail())
				&& employee.getPassword().equals(director.getEmployees().getPassword())) {
			stage.close();
			initMenuJefes(session);
			// comprobamos si es de ventas
		} else if (sales != null) {
			if (employee.getEmail().equals(sales.getEmployees().getEmail())
					&& employee.getPassword().equals(sales.getEmployees().getPassword())) {
				stage.close();
				initMenuVentas(session, sales);
			} else {
				System.out.println("Esta pero no tiene asignado rol");
			}
		}
	}

	private void initMenuJefes(Session session) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/jefes/MenuJefesView.fxml"));
			BorderPane rootLayout = (BorderPane) loader.load();

			MenuJefesController controller = loader.getController();
			controller.setRootLayout(rootLayout);
			controller.setStage(stage);
			controller.setSession(session);

			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);

			initEmpleadosView(rootLayout, session);

			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initEmpleadosView(BorderPane rootLayout, Session session) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/jefes/EmployeeView.fxml"));
		AnchorPane root;
		try {
			root = loader.load();
			EmployeeController controller = loader.getController();
			controller.setSession(session);
			controller.setRootLayout(rootLayout);
			controller.setStage(stage);
			controller.verEmpleados();

			rootLayout.setCenter(root);
			stage.show();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void initMecanicosHome(Session session, String name) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(
					AutominionMainApp.class.getResource("/autominion/views/mecanicos/MecanicosHomeView.fxml"));
			rootLayout = (BorderPane) loader.load();

			MecanicosHomeController controller = loader.getController();
			controller.setSession(session);
			controller.setStage(stage);
			controller.setRootLayout(rootLayout);
			controller.setNameMecanico(name);

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);

			cargarScena(session);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void cargarScena(Session session) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/autominion/views/mecanicos/MecanicosButtonsView.fxml"));
		AnchorPane root = loader.load();

		MecanicosButtonsController controller = loader.getController();
		controller.setSession(session);
		controller.setStage(stage);
		controller.setRootLayout(rootLayout);

		rootLayout.setCenter(root);
	}

	private void initMenuVentas(Session session, Salesemployees sales) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/ventas/MenuVentasView.fxml"));
			BorderPane rootLayout = (BorderPane) loader.load();

			MenuVentasController controller = loader.getController();
			controller.setRootLayout(rootLayout);
			controller.setStage(stage);
			controller.setSession(session);
			controller.setSalesEmployee(sales);

			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);

			initVentasHome(rootLayout, session);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initVentasHome(BorderPane rootLayout, Session session) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/ventas/VehiculosView.fxml"));
			AnchorPane root = loader.load();
			VehiculosController controller = loader.getController();
			controller.setSession(session);
			controller.setPrimaryStage(stage);
			controller.setRootLayout(rootLayout);
			controller.verTarjetas(controller.obtenerVehiculos());

			EventHandler<ActionEvent> eventNew = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					newVehiculo(session, rootLayout);
				}
			};
			controller.btnNewVehiculo.setOnAction(eventNew);

			rootLayout.setCenter(root);
			stage.show();

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
			controller.setBoxes();
			controller.setMecanicos(false);

			rootLayout.setCenter(root);

			stage.show();
		} catch (Throwable e1) {
			e1.printStackTrace();
		}
	}

	private void actionsButtons() {
		EventHandler<ActionEvent> eventSign = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				login();
			}
		};
		btnSign.setOnAction(eventSign);

		EventHandler<ActionEvent> eventBack = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					loginView();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		};
		btnBack.setOnAction(eventBack);

		EventHandler<ActionEvent> eventForgot = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				forgotPasswordView();
			}
		};
		btnForgot.setOnAction(eventForgot);

		EventHandler<ActionEvent> eventPopup = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				FXMLLoader fxmlLoader = new FXMLLoader(
						getClass().getResource("/autominion/views/login/CodeMessageView.fxml"));

				Parent root1 = null;
				try {
					root1 = (Parent) fxmlLoader.load();
					root1.getStylesheets().add("file:./target/resources/styles/CodeMessage.css");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setScene(new Scene(root1));
				stage.show();

				SendEmail.email(txtEmail.getText(), AutominionMainApp.pin);
			}
		};
		btnNext.setOnAction(eventPopup);
	}

	/**
	 * Metodo que muestra los elementos para el login
	 * 
	 * @throws FileNotFoundException
	 */
	private void loginView() throws FileNotFoundException {
		lblEmail.setVisible(false);
		txtEmail.setVisible(false);
		btnBack.setVisible(false);
		btnNext.setVisible(false);
		lblDescription.setVisible(false);

		lblTittle.setText("Login");

		// File file = new File("../resources/images/rent.jpg");
		// Image image = new
		// Image("C:\\Users\\alumnado\\Desktop\\Workspace\\AutoMinion\\AutoMinion\\rent.jpg");
//        Image image = new Image(file.getAbsolutePath());
//		imageLogin.setImage(image);

		btnSign.setVisible(true);
		btnForgot.setVisible(true);
		txtPasswd.setVisible(true);
		txtUser.setVisible(true);
		lblUser.setVisible(true);
		lblPassword.setVisible(true);
	}

	/**
	 * Metodo que muestra los elementos para cambiar la contraseña
	 */
	private void forgotPasswordView() {
		lblEmail.setVisible(true);
		txtEmail.setVisible(true);
		btnBack.setVisible(true);
		btnNext.setVisible(true);
		lblDescription.setVisible(true);

		lblTittle.setText("Forgot password");

		btnSign.setVisible(false);
		btnForgot.setVisible(false);
		txtPasswd.setVisible(false);
		txtUser.setVisible(false);
		lblUser.setVisible(false);
		lblPassword.setVisible(false);
	}

}
