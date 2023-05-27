package autominion.views.ventas;

import java.io.IOException;

import org.hibernate.Session;

import autominion.database.persistence.entities.Customers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FilaClienteController {
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
	private Customers c;

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
		EventHandler<ActionEvent> eventInfo = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				infoCliente();
			}
		};
		btnInfo.setOnAction(eventInfo);
	}

	public void verCliente() {
		lblNombre.setText(c.getName() + " " + c.getSurname1() + " " + c.getSurname2());
		lblCorreo.setText(c.getEmail());
		lblNumero.setText(c.getPhone());
	}

	public Customers getC() {
		return c;
	}

	public void setC(Customers c) {
		this.c = c;
	}

	private void infoCliente() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/ventas/NewCustomerView.fxml"));
			AnchorPane root = loader.load();
			NewCustomerController controller = loader.getController();
			controller.setSession(session);
			controller.setCliente(c);
			
			rellenarCampos(controller);

			rootLayout.setCenter(root);

			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void rellenarCampos(NewCustomerController controller) {
		controller.getTxtAddress().setText(c.getAddress());
		controller.getTxtEmail().setText(c.getEmail());
		controller.getTxtNombre().setText(c.getName());
		controller.getTxtPhone().setText(c.getPhone());
		controller.getTxtSurname1().setText(c.getSurname1());
		controller.getTxtSurname2().setText(c.getSurname2());
		
		controller.getTxtAddress().setDisable(true);
		controller.getTxtEmail().setDisable(true);
		controller.getTxtNombre().setDisable(true);
		controller.getTxtPhone().setDisable(true);
		controller.getTxtSurname1().setDisable(true);
		controller.getTxtSurname2().setDisable(true);
		
		controller.getBtnNewCustomer().setVisible(false);
	}
}