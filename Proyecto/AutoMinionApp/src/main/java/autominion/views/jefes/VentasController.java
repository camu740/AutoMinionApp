package autominion.views.jefes;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

import autominion.database.persistence.entities.Salesemployees;
import autominion.database.persistence.entities.Salesproposal;
import autominion.database.services.implementations.SalesproposalManagementServiceImpl;
import autominion.database.services.interfaces.SalesproposalManagementServiceI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class VentasController {

	@FXML
	private Button btnNewVenta;
	@FXML
	private GridPane gridPane;
    @FXML
    private Label lblVentas;
    @FXML
    private Label lblTotalVentas;
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
		this.primaryStage = primaryStage;

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

	// TODO cambiar la fecha
	public void verTarjetas(List<Salesproposal> ventas) {
		int contador = 0;
		// Obtenemos el resto para saber cuantas tarjetas vamos a necesitar
		double resto = ventas.size() % 5.0;
		// Numero de vehiculos
		double numeroVehiculos = ventas.size();
		// El numero de filas que vamos a necesitar
		double filasVehiculos = numeroVehiculos / 5.0;
		// Numero de filas restantes
		double filasRestantes = ventas.size() / 5;
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
					controller.getLblInfo().setText(ventas.get(contador).getVehicles().getRegistration() + " - "
							+ ventas.get(contador).getCustomers().getName());
					controller.getLblFecha().setText(ventas.get(contador).getId().getProposalDate() + "");

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
	
	public void setFields() {
		SalesproposalManagementServiceI salesService = new SalesproposalManagementServiceImpl(session);
		
		lblTotalVentas.setText(salesService.getSales() + "");
		
		lblTotalDinero.setText(salesService.getCollected() + "");
		
	}

	public List<Salesproposal> obtenerVentas() {
		SalesproposalManagementServiceI s = new SalesproposalManagementServiceImpl(session);
		try {
			return s.searchAll();
		} catch (Exception e) {
			System.err.println("Error al obtener las ventas");
		}
		return null;
	}

}
