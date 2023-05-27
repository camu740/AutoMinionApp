package autominion.views.mecanicos;

import java.io.IOException;

import org.hibernate.Session;

import autominion.database.persistence.entities.Repairs;
import autominion.database.services.implementations.RepairsManagementServiceImpl;
import autominion.database.services.interfaces.RepairsManagementServiceI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class GridCardController {
	@FXML
	private GridPane cardPanel;
	private Session session;
	private BorderPane rootLayout;

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
	private void initialize() {
	}

	public void cargarVehiculos() {
		RepairsManagementServiceI r = new RepairsManagementServiceImpl(this.session);
		for (int i = 0; i < r.searchAllNotFinalize().size(); i++) {
			try {
				FXMLLoader loader = new FXMLLoader(
						getClass().getResource("/autominion/views/mecanicos/VehiculoCardView.fxml"));
				AnchorPane root = loader.load();
				VehiculoCardController controller = loader.getController();
				controller.setSession(session);

				cardPanel.add(root, 0, i);
				controller.setRootLayout(rootLayout);
				
				//obtenemos la reparacion y la pasamos a la vista
				Repairs repair = r.searchAll().get(i);
				controller.setRepairs(repair);
				controller.seeRepair(repair);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
