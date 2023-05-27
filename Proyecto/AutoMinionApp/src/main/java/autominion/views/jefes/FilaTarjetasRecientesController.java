package autominion.views.jefes;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

import autominion.database.persistence.entities.Salesproposal;
import autominion.database.services.implementations.SalesproposalManagementServiceImpl;
import autominion.database.services.interfaces.SalesproposalManagementServiceI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class FilaTarjetasRecientesController {
	@FXML // fx:id="btnBack"
	private Button btnBack; // Value injected by FXMLLoader
	@FXML // fx:id="btnNext"
	private Button btnNext; // Value injected by FXMLLoader
	@FXML // fx:id="GridPane"
	private GridPane gridPane; // Value injected by FXMLLoader
	private Session session;

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@FXML
	void initialize() {
	}

	public void verTarjetas() {
		SalesproposalManagementServiceI s = new SalesproposalManagementServiceImpl(session);
		List<Salesproposal> a = s.searchAll();
		
		try {
			for (int i = 0; i < a.size(); i++) {
				FXMLLoader loader = new FXMLLoader(
						getClass().getResource("/autominion/views/ventas/TarjetasRecientesView.fxml"));
				AnchorPane root = loader.load();
				if (i == 1) {
					TarjetasRecientesController controller = loader.getController();
					controller.getLblInfo().setText(a.get(i).getVehicles().getModel() + "\t" + a.get(i).getVehicles().getRegistration());
				}
				gridPane.add(root, i, 0);
				gridPane.setHgap(60);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
