package autominion.views.jefes;

import java.io.IOException;

import org.hibernate.Session;

import autominion.database.persistence.entities.Salesemployees;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuJefesController {
	@FXML
    private Button btnEmpleados;
    @FXML
    private Button btnReparaciones;
    @FXML
    private Button btnVehiculos;
    @FXML
    private Button btnVentas;
    @FXML
    private ImageView image;

	private Stage primaryStage;
	private BorderPane rootLayout;
	private Session session;
	private Salesemployees sales;

	public void setSalesEmployee(Salesemployees sales) {
		this.sales = sales;

	}

	public void setStage(Stage primaryStage) {
		this.primaryStage = primaryStage;

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
		actionsButtons();
	}

	private void actionsButtons() {
		EventHandler<ActionEvent> eventEmpleados = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
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
		};
		btnEmpleados.setOnAction(eventEmpleados);

		EventHandler<ActionEvent> eventVehiculos = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				FXMLLoader loader = new FXMLLoader(
						getClass().getResource("/autominion/views/jefes/VehiculosJefeView.fxml"));
				AnchorPane root;
				try {
					root = loader.load();
					VehiculosJefeController controller = loader.getController();
					controller.setSession(session);
					controller.setPrimaryStage(primaryStage);
					controller.setRootLayout(rootLayout);

					controller.verTarjetas(controller.ordenarVehiculosFechas());

					rootLayout.setCenter(root);
					primaryStage.show();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};

		btnVehiculos.setOnAction(eventVehiculos);

		EventHandler<ActionEvent> eventVentas = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/autominion/views/jefes/VentasView.fxml"));
					AnchorPane root = loader.load();
					VentasController controller = loader.getController();
					controller.setSalesEmployee(sales);

					controller.setSession(session);
					controller.setRootLayout(rootLayout);
					controller.setStage(primaryStage);
					
					controller.setFields();
					controller.verTarjetas(controller.obtenerVentas());

					rootLayout.setCenter(root);
					primaryStage.show();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};
		btnVentas.setOnAction(eventVentas);

		EventHandler<ActionEvent> eventReparaciones = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/jefes/ReparacionesView.fxml"));
					AnchorPane root = loader.load();
					ReparacionesController controller = loader.getController();
					controller.setSession(session);
					controller.setSalesEmployee(sales);
					controller.setRootLayout(rootLayout);
					controller.setStage(primaryStage);
		
					controller.setFields();
					controller.verTarjetas(controller.obtenerRepairs());
		
					rootLayout.setCenter(root);
					primaryStage.show();
		
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};
		btnReparaciones.setOnAction(eventReparaciones);
	}
}