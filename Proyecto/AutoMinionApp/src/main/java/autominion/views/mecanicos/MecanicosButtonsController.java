package autominion.views.mecanicos;

import java.io.IOException;

import org.hibernate.Session;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MecanicosButtonsController {
	@FXML // fx:id="btnReparar"
	private Button btnReparar; // Value injected by FXMLLoader
	@FXML // fx:id="btnNuevo"
	private Button btnNuevo; // Value injected by FXMLLoader
	@FXML // fx:id="btnAsignar"
	private Button btnAsignar; // Value injected by FXMLLoader
	private BorderPane rootLayout;
	private Session session;
	private Stage stage;

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
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
	private void initialize() throws IOException {
		actionsButtons();
	}

	private void actionsButtons() {
		EventHandler<ActionEvent> eventNew = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					openNuevoVehiculo();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};
		btnNuevo.setOnAction(eventNew);

		EventHandler<ActionEvent> eventAsignar = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					openAsignar();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};
		btnAsignar.setOnAction(eventAsignar);

		EventHandler<ActionEvent> eventReparar = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					openReparar();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};
		btnReparar.setOnAction(eventReparar);
	}

	private void openNuevoVehiculo() throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/autominion/views/mecanicos/NuevoVehiculoView.fxml"));
		AnchorPane root = loader.load();
		
		NuevoVehiculoController nuevo = loader.getController();
		nuevo.setSession(session);
		nuevo.setRootLayout(rootLayout);
		//inicializa todos los Box de la vista
		nuevo.setBoxes();
		nuevo.setMecanicos(true);

		rootLayout.setCenter(root);
	}

	private void openAsignar() throws IOException {
		

		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/autominion/views/mecanicos/AsignarMecanicosView.fxml"));
		AnchorPane root = loader.load();
		
		AsignarMecanicosController asignar = loader.getController();
		asignar.setSession(this.session);
		asignar.setRootLayout(rootLayout);
		asignar.setBoxes();
		
		rootLayout.setCenter(root);
	}

	private void openReparar() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/mecanicos/GridCardView.fxml"));
		AnchorPane root = loader.load();
		GridCardController controller = loader.getController();
		controller.setSession(session);
		controller.setRootLayout(rootLayout);
		controller.cargarVehiculos();
		
		rootLayout.setCenter(root);
	}
}
