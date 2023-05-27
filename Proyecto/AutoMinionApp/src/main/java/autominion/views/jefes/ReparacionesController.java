package autominion.views.jefes;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

import autominion.database.persistence.entities.Repairs;
import autominion.database.persistence.entities.Salesemployees;
import autominion.database.services.implementations.RepairsManagementServiceImpl;
import autominion.database.services.interfaces.RepairsManagementServiceI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ReparacionesController {
	@FXML
	private Button btnNewVenta;
	@FXML
	private GridPane gridPane;
	@FXML
	private Label lblReparaciones;
	@FXML
	private Label lblTotalReparaciones;
	@FXML
	private Label lblDinero;
	@FXML
	private Label lblTotalDinero;

	private Salesemployees sales;

	private BorderPane rootLayout;
	private Stage primaryStage;
	private Session session;

	public void setSalesEmployee(Salesemployees sales) {
		this.sales = sales;

	}

	public void setStage(Stage primaryStage) {
		this.setPrimaryStage(primaryStage);

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

	public void verTarjetas(List<Repairs> repairs) {
		int contador = 0;
		// Obtenemos el resto para saber cuantas tarjetas vamos a necesitar
		double resto = repairs.size() % 5.0;
		// Numero de vehiculos
		double numeroVehiculos = repairs.size();
		// El numero de filas que vamos a necesitar
		double filasVehiculos = numeroVehiculos / 5.0;
		// Numero de filas restantes
		double filasRestantes = repairs.size() / 5;
		double aux = 5;

		try {
			for (int j = 0; j < numeroVehiculos / 5.0; j++) {
				// Si es la ultima fila
				if (filasVehiculos < 1)
					aux = numeroVehiculos;

				for (int i = 0; i < aux; i++) {
					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/autominion/views/jefes/VentaTarjetasView.fxml"));
					AnchorPane root = loader.load();

					VentaTarjetasController controller = loader.getController();

					controller.getLblInfo().setText(repairs.get(contador).getMechanics().getEmployees().getName()
							+ " - " + repairs.get(contador).getVehicles().getRegistration());

					controller.getLblFecha().setText(repairs.get(contador).getId().getRequestDate() + "");

					gridPane.add(root, i, j + 1);
					gridPane.setHgap(40);

					contador++;
				}

				// Si hay menos de 5 vehiculos en una fila
				if (filasRestantes == 1)
					aux = resto;

				gridPane.setVgap(30);

				// Cada vez que rellenamos una fila restamos uno
				filasRestantes--;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void initialize() {
	}

	public List<Repairs> obtenerRepairs() {
		RepairsManagementServiceI s = new RepairsManagementServiceImpl(session);
		try {
			return s.searchAll();
		} catch (Exception e) {
			System.err.println("Error al obtener las reparaciones");
		}
		return null;
	}
	
	public void setFields() {
		RepairsManagementServiceI repairService = new RepairsManagementServiceImpl(session);
		
		lblTotalReparaciones.setText(repairService.getRepairs() + "");
		
		lblTotalDinero.setText(repairService.getCollected() + "");
		
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
