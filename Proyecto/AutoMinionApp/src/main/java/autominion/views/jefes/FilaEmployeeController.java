package autominion.views.jefes;

import java.io.IOException;

import org.hibernate.Session;

import autominion.database.persistence.entities.Employees;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FilaEmployeeController {
	@FXML // fx:id="btnInfo"
	private Button btnInfo; // Value injected by FXMLLoader
	@FXML // fx:id="lblCorreo"
	private Label lblCorreo; // Value injected by FXMLLoader
	@FXML // fx:id="lblNombre"
	private Label lblNombre; // Value injected by FXMLLoader
	@FXML // fx:id="lblNumero"
	private Label lblNumero; // Value injected by FXMLLoader
	private BorderPane rootLayout;
	private Stage primaryStage;
	private Session session;
	private Employees employee;
	private boolean mechanics;
	private boolean salesemployees;

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

	public boolean isMechanics() {
		return mechanics;
	}

	public void setMechanics(boolean mechanics) {
		this.mechanics = mechanics;
	}

	public boolean isSalesemployees() {
		return salesemployees;
	}

	public void setSalesemployees(boolean salesemployees) {
		this.salesemployees = salesemployees;
	}

	@FXML
	private void initialize() {
		actionsButtons();
	}

	private void actionsButtons() {
		EventHandler<ActionEvent> eventInfo = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				infoEmployee();
			}
		};
		btnInfo.setOnAction(eventInfo);
	}

	public void verEmpleado() {
		lblNombre.setText(employee.getName() + " " + employee.getSurname1() + " " + employee.getSurname2());
		lblCorreo.setText(employee.getEmail());
		lblNumero.setText(employee.getPhone());
	}

	public Employees getEmpleado() {
		return employee;
	}

	public void setEmpleado(Employees employee) {
		this.employee = employee;
	}

	private void infoEmployee() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/jefes/NewEmployeeView.fxml"));
			AnchorPane root = loader.load();
			NewEmployeeController controller = loader.getController();
			controller.setEmployee(employee);
			controller.setSession(session);
			controller.setRootLayout(rootLayout);
			controller.setStage(primaryStage);
			
			rellenarCampos(controller);

			rootLayout.setCenter(root);

			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void rellenarCampos(NewEmployeeController controller) {
		controller.getTxtAddress().setText(employee.getAddress());
		controller.getTxtEmail().setText(employee.getEmail());
		controller.getTxtNombre().setText(employee.getName());
		controller.getTxtPhone().setText(employee.getPhone());
		controller.getTxtSurname1().setText(employee.getSurname1());
		controller.getTxtSurname2().setText(employee.getSurname2());

		if(mechanics) {
			controller.getTxtTipo().setText("Mecánico");
		} else if (salesemployees) {
			controller.getTxtTipo().setText("Ventas");
		}
		
		controller.getTxtAddress().setDisable(true);
		controller.getTxtEmail().setDisable(true);
		controller.getTxtNombre().setDisable(true);
		controller.getTxtPhone().setDisable(true);
		controller.getTxtSurname1().setDisable(true);
		controller.getTxtSurname2().setDisable(true);
		controller.getTxtTipo().setDisable(true);
		controller.getCbType().setVisible(false);
		
		controller.getBtnNewEmployee().setVisible(false);
	}
}