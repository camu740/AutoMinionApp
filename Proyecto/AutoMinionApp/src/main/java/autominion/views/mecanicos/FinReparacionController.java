package autominion.views.mecanicos;

import java.io.IOException;

import org.hibernate.Session;

import autominion.database.persistence.entities.Repairs;
import autominion.database.persistence.entities.Vehicles;
import autominion.database.services.implementations.RepairsManagementServiceImpl;
import autominion.database.services.interfaces.RepairsManagementServiceI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class FinReparacionController {
	@FXML // fx:id="lblCombustibleInfo"
	private Label lblCombustibleInfo; // Value injected by FXMLLoader

	@FXML // fx:id="lblColorInfo"
	private Label lblColorInfo; // Value injected by FXMLLoader

	@FXML // fx:id="lblMarcaInfo"
	private Label lblMarcaInfo; // Value injected by FXMLLoader

	@FXML // fx:id="lblPuertasInfo"
	private Label lblPuertasInfo; // Value injected by FXMLLoader

	@FXML // fx:id="imagen"
	private AnchorPane imagen; // Value injected by FXMLLoader

	@FXML // fx:id="lblPresupuesto"
	private Label lblPresupuesto; // Value injected by FXMLLoader

	@FXML // fx:id="lblAnyoInfo"
	private Label lblAnyoInfo; // Value injected by FXMLLoader

	@FXML // fx:id="txtPiezas"
	private TextArea txtPiezas; // Value injected by FXMLLoader

	@FXML // fx:id="lblAsientos"
	private Label lblAsientos; // Value injected by FXMLLoader

	@FXML // fx:id="lblCombustible"
	private Label lblCombustible; // Value injected by FXMLLoader

	@FXML // fx:id="lblMarca"
	private Label lblMarca; // Value injected by FXMLLoader

	@FXML // fx:id="lblModeloInfo"
	private Label lblModeloInfo; // Value injected by FXMLLoader

	@FXML // fx:id="lblAnyo"
	private Label lblAnyo; // Value injected by FXMLLoader

	@FXML // fx:id="lblPuertas"
	private Label lblPuertas; // Value injected by FXMLLoader

	@FXML // fx:id="lblAsientosInfo"
	private Label lblAsientosInfo; // Value injected by FXMLLoader

	@FXML // fx:id="lblKmInfo"
	private Label lblKmInfo; // Value injected by FXMLLoader

	@FXML // fx:id="lblKm"
	private Label lblKm; // Value injected by FXMLLoader

	@FXML // fx:id="lblPiezas"
	private Label lblPiezas; // Value injected by FXMLLoader

	@FXML // fx:id="lblPresupuestoInfo"
	private Label lblPresupuestoInfo; // Value injected by FXMLLoader

	@FXML // fx:id="lblMatricula"
	private Label lblMatricula; // Value injected by FXMLLoader

	@FXML // fx:id="lblColor"
	private Label lblColor; // Value injected by FXMLLoader

	@FXML // fx:id="lblMatriculaInfo"
	private Label lblMatriculaInfo; // Value injected by FXMLLoader

	@FXML // fx:id="lblModelo"
	private Label lblModelo; // Value injected by FXMLLoader

	@FXML // fx:id="btnFin"
	private Button btnFin; // Value injected by FXMLLoader

	private Repairs repair;
	private BorderPane rootLayout;
	private Session session;

	public Repairs getRepair() {
		return repair;
	}

	public void setRepairs(Repairs repair) {
		this.repair = repair;
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
				
				java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
				
				repair.setFinalizeDate(date);
				repair.setFinalize(true);

				RepairsManagementServiceI service = new RepairsManagementServiceImpl(session);
				service.updateRepair(repair);

				goMecanicosButtonsController();

			}
		};

		btnFin.setOnAction(eventNew);
	}

	public void seeInformacion() {

		Vehicles vehicle = this.repair.getVehicles();

		lblAnyoInfo.setText(vehicle.getEntryDate().toString());
		lblAsientosInfo.setText(vehicle.getNumberSeat().toString());
		lblColorInfo.setText(vehicle.getColor());
		lblCombustibleInfo.setText(vehicle.getCombustion());
		lblKmInfo.setText(vehicle.getKm().toString());
		lblMarcaInfo.setText(vehicle.getBrand());
		lblMatriculaInfo.setText(vehicle.getRegistration());
		lblModeloInfo.setText(vehicle.getModel());
		lblPresupuestoInfo.setText(this.repair.getEstimatedBudget().toString());
		lblPuertasInfo.setText(vehicle.getNumberDoor().toString());
		txtPiezas.setText(this.repair.getPartsList());

		// TODO insertar imagen del vehiculo
	}

	protected void goMecanicosButtonsController() {
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
		// controller.setStage(stage);
		controller.setRootLayout(rootLayout);

		rootLayout.setCenter(root);
	}

}
