package autominion.views.mecanicos;

import java.io.IOException;

import org.hibernate.Session;

import autominion.database.persistence.entities.Repairs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class VehiculoCardController {
	@FXML // fx:id="lblPrioridad"
    private Label lblPrioridad; // Value injected by FXMLLoader

    @FXML // fx:id="lblModeloInfo"
    private Label lblModeloInfo; // Value injected by FXMLLoader

    @FXML // fx:id="lblMarcaInfo"
    private Label lblMarcaInfo; // Value injected by FXMLLoader

    @FXML // fx:id="btnNext"
    private Button btnNext; // Value injected by FXMLLoader

    @FXML // fx:id="imagen"
    private ImageView imagen; // Value injected by FXMLLoader

    @FXML // fx:id="lblMatriculaInfo"
    private Label lblMatriculaInfo; // Value injected by FXMLLoader

    @FXML // fx:id="lblAnyoInfo"
    private Label lblAnyoInfo; // Value injected by FXMLLoader
    
    private BorderPane rootLayout;
    private Repairs repair;
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
    
    public void seeRepair(Repairs r) {
    	lblModeloInfo.setText(r.getVehicles().getModel());
    	lblMarcaInfo.setText(r.getVehicles().getBrand());
    	lblMatriculaInfo.setText(r.getVehicles().getRegistration());
    	lblAnyoInfo.setText(r.getVehicles().getFabricationYear().toString());
    	lblPrioridad.setText(r.getPriority());
    	
    }
    
    
    private void actionsButtons() {
    	EventHandler<ActionEvent> eventNext = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					openFinReparacion();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};
		btnNext.setOnAction(eventNext);
    }
    
    private void openFinReparacion() throws IOException {
    	FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/autominion/views/mecanicos/FinReparacionView.fxml"));
		AnchorPane root = loader.load();
		
		FinReparacionController controller = loader.getController();
		controller.setSession(session);

		//pasamos la reparacion y la mostramos
		controller.setRepairs(repair);
		controller.setRootLayout(rootLayout);	
		controller.seeInformacion();
		
		rootLayout.setCenter(root);

    }
}
