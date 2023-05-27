package autominion.views.ventas;

import java.io.IOException;

import org.hibernate.Session;

import autominion.database.services.implementations.CustomerManagementServiceImpl;
import autominion.database.services.interfaces.CustomerManagementServiceI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ClientesController {
	@FXML // fx:id="btnOrdenar"
	private Button btnNewCustomer; // Value injected by FXMLLoader
	@FXML // fx:id="gridPane"
	private GridPane gridPane; // Value injected by FXMLLoader
	
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
				goNewCustomer();
			}
		};
		btnNewCustomer.setOnAction(eventNew);
	}

	protected void goNewCustomer() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/ventas/NewCustomerView.fxml"));
			AnchorPane root = loader.load();
			NewCustomerController controller = loader.getController();
			controller.setSession(session);
			
			rootLayout.setCenter(root);

			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void verClientes() {
		CustomerManagementServiceI clientes = new CustomerManagementServiceImpl(session);

		for (int i = 0; i < clientes.searchAll().size(); i++) {
			try {
				FXMLLoader loader = new FXMLLoader(
						getClass().getResource("/autominion/views/ventas/FilaClienteView.fxml"));
				AnchorPane root = loader.load();
				FilaClienteController controller = loader.getController();
				controller.setRootLayout(rootLayout);
				controller.setSession(session);
				controller.setStage(primaryStage);
				controller.setC(clientes.searchAll().get(i));
				controller.verCliente();
				
				gridPane.add(root, 0, i);
				gridPane.setVgap(35);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}