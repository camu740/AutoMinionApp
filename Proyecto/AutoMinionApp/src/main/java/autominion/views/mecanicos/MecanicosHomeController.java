package autominion.views.mecanicos;

import java.io.IOException;
import java.time.LocalDate;

import org.hibernate.Session;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MecanicosHomeController {
	@FXML // fx:id="lblFechaDia"
	private Label lblFechaDia; // Value injected by FXMLLoader
	@FXML // fx:id="btnBack"
	public Button btnBack; // Value injected by FXMLLoader
	@FXML // fx:id="lblJefe"
	private Label lblJefe; // Value injected by FXMLLoader
	@FXML // fx:id="lblName"
	private Label lblName; // Value injected by FXMLLoader
	@FXML // fx:id="imagen"
	private AnchorPane imagen; // Value injected by FXMLLoader
	@FXML // fx:id="lblDate"
	private Label lblDate; // Value injected by FXMLLoader
	@FXML // fx:id="lblNombreMecanico"
	private Label lblNombreMecanico; // Value injected by FXMLLoader
	private BorderPane rootLayout;
	private Session session;
	private Stage stage;
	// Para obtener la fecha de hoy
	LocalDate date_of_today = LocalDate.now();

	public void setNameMecanico(String name) {
		lblNombreMecanico.setText(name);
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@FXML
	public void initialize() {
		actionButtons();
		lblFechaDia.setText(date_of_today+"");
	}

	public MecanicosHomeController() {
		super();
	}

	private void actionButtons() {
		EventHandler<ActionEvent> eventBack = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					openButtonsView();
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
			}
		};
		btnBack.setOnAction(eventBack);
	}

	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}

	public void openButtonsView() {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/autominion/views/mecanicos/MecanicosButtonsView.fxml"));
		AnchorPane root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Give the controller access to the main app.
		MecanicosButtonsController controller = loader.getController();
		controller.setSession(session);
		controller.setStage(stage);
		controller.setRootLayout(rootLayout);

		rootLayout.setCenter(root);
	}
}
