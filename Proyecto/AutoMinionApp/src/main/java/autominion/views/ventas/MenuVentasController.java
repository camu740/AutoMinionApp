package autominion.views.ventas;

import java.io.IOException;

import org.hibernate.Session;

import autominion.database.persistence.entities.Salesemployees;
import autominion.views.mecanicos.NuevoVehiculoController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuVentasController {
	@FXML
	private MenuItem itemClientes;
	@FXML
	private MenuItem itemNuevoCliente;
	@FXML
	private MenuItem itemNuevoVehiculo;
	@FXML
	private MenuItem itemVehiculos;
	@FXML
	private MenuItem itemVentas;
	@FXML
    private MenuItem itemVehiculosAntiguos;
    @FXML
    private MenuItem itemNewPropuesta;
    @FXML
    private MenuItem itemNewVenta;

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
	void handleClientes(ActionEvent event) {
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
	void handleNewCliente(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/ventas/NewCustomerView.fxml"));
			AnchorPane root = loader.load();
			NewCustomerController controller = loader.getController();
			controller.setSession(session);
			controller.setRootLayout(rootLayout);
			controller.setStage(primaryStage);

			rootLayout.setCenter(root);

			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void handleNewVehiculo(ActionEvent event) {
		newVehiculo();
	}

	@FXML
	void handlePropuestas(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/ventas/VentasView.fxml"));
			AnchorPane root = loader.load();
			VentasController controller = loader.getController();
			controller.setSession(session);
			controller.setSalesEmployee(sales);
			controller.setRootLayout(rootLayout);
			controller.setStage(primaryStage);

			controller.verTarjetas(controller.obtenerPropuestas());

			rootLayout.setCenter(root);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void handleVehiculos(ActionEvent event) {
		vehiculos(false);
	}

	@FXML
	void handleVentas(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/ventas/VentasView.fxml"));
			AnchorPane root = loader.load();
			VentasController controller = loader.getController();
			controller.setSession(session);
			controller.setSalesEmployee(sales);
			controller.setRootLayout(rootLayout);
			controller.setStage(primaryStage);

			
			controller.verTarjetas(controller.obtenerVentas());
			
			rootLayout.setCenter(root);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 @FXML
	    void handleNewVenta(ActionEvent event) {
	    	proposalView();
	    }
	    
	    @FXML
	    void handleNewPropuesta(ActionEvent event) {
	    	proposalView();
	    }
	    
	    private void proposalView() {
	    	try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/ventas/NewProposalView.fxml"));
				AnchorPane root = loader.load();
				NewProposalController controller = loader.getController();
				controller.setSession(session);
				controller.setSalesEmployee(sales);
				controller.setRootLayout(rootLayout);
				controller.setBoxes();
				
				rootLayout.setCenter(root);

				primaryStage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	@FXML
	void handleVehiculosAntiguos(ActionEvent event) {
		vehiculos(true);
	}

	private void vehiculos(boolean antiguos) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/ventas/VehiculosView.fxml"));
		AnchorPane root;
		try {
			root = loader.load();
			VehiculosController controller = loader.getController();
			controller.setSession(session);
			controller.setPrimaryStage(primaryStage);
			controller.setRootLayout(rootLayout);
			if (!antiguos) {
				controller.verTarjetas(controller.obtenerVehiculos());
			} else {
				controller.verTarjetas(controller.ordenarVehiculosFechas());
			}

			EventHandler<ActionEvent> eventNew = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					newVehiculo();
				}
			};
			controller.btnNewVehiculo.setOnAction(eventNew);

			rootLayout.setCenter(root);
			primaryStage.show();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void newVehiculo() {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/autominion/views/ventas/NewVehiculoView.fxml"));
			AnchorPane root = loader.load();
			NuevoVehiculoController controller = loader.getController();
			controller.setSession(session);
			controller.setRootLayout(rootLayout);
			controller.setBoxes();
			controller.setMecanicos(false);

			rootLayout.setCenter(root);

			primaryStage.show();
		} catch (Throwable e1) {
			e1.printStackTrace();
		}
	}
}