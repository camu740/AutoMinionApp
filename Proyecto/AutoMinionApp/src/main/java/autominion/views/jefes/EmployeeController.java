package autominion.views.jefes;

import java.io.IOException;

import org.hibernate.Session;

import autominion.database.services.implementations.EmployeesManagementServiceImpl;
import autominion.database.services.interfaces.EmployeesManagementServiceI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EmployeeController {

	@FXML
	private Button btnNewEmployee;

	@FXML
	private GridPane gridPane;

	private BorderPane rootLayout;
	private Stage primaryStage;
	private Session session;

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
				goNewEmployee();
			}
		};
		btnNewEmployee.setOnAction(eventNew);
	}

	protected void goNewEmployee() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/jefes/NewEmployeeView.fxml"));
			AnchorPane root = loader.load();
			NewEmployeeController controller = loader.getController();
			controller.setSession(session);
			controller.setRootLayout(rootLayout);
			controller.setStage(primaryStage);

			controller.setBoxes();

			rootLayout.setCenter(root);

			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void verEmpleados() {
		EmployeesManagementServiceI empleados = new EmployeesManagementServiceImpl(session);

		for (int i = 0; i < empleados.searchAll().size(); i++) {
			try {
				FXMLLoader loader = new FXMLLoader(
						getClass().getResource("/autominion/views/jefes/FilaEmployeeView.fxml"));
				AnchorPane root = loader.load();
				FilaEmployeeController controller = loader.getController();
				controller.setRootLayout(rootLayout);
				controller.setSession(session);
				controller.setStage(primaryStage);
				controller.setEmpleado(empleados.searchAll().get(i));

				for (int j = 0; j < empleados.getMechanics().size(); j++) {
					if (empleados.searchAll().get(i).getId() == empleados.getMechanics().get(j).getId()) {
						controller.setMechanics(true);
						controller.setSalesemployees(false);
					} else if (empleados.searchAll().get(i).getId() == empleados.getSalesemployees().get(j).getId()) {
						controller.setMechanics(false);
						controller.setSalesemployees(true);
					}
				}

				controller.verEmpleado();

				gridPane.add(root, 0, i);
				gridPane.setVgap(35);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}