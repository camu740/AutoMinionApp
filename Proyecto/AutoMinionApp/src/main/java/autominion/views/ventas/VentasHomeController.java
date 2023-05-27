package autominion.views.ventas;

import java.io.IOException;

import org.hibernate.Session;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class VentasHomeController {
	@FXML // fx:id="gridPane"
	private GridPane gridPane; // Value injected by FXMLLoader
	private Stage stage;
	private BorderPane rootLayout;
	private Session session;

	@FXML
	void initialize() {
		// TODO activate
//		initPropuestasFinalizan();
//		initUltimasVentas();
		initVehiculosAntiguos();
	}

	public void setStage(Stage primaryStage) {
		stage = primaryStage;
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

	public BorderPane getRootLayout() {
		return rootLayout;
	}

	public void verTarjetas() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/ventas/VehiculosView.fxml"));
			AnchorPane root = loader.load();
			VehiculosController controller = loader.getController();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initVehiculosAntiguos() {
//		List<Vehicles> ListaVehiculos = new ArrayList<>();
//
//		// TODO vehiculos ordenados de mas antiguedad a menos (tiempo en el
//		// concesionario)
//
//		int i = 0;
//
//		for (Vehicles v : ListaVehiculos) {
//			i++;
//			switch (i) {
//			case 1:
//				info31.setText(v.getRegistration());
//				fecha31.setText(v.getEntryDate().toString());
//				img31.setImage(null);
//				break;
//			case 2:
//				info32.setText(v.getRegistration());
//				fecha32.setText(v.getEntryDate().toString());
//				img32.setImage(null);
//				break;
//			case 3:
//				info33.setText(v.getRegistration());
//				fecha33.setText(v.getEntryDate().toString());
//				img33.setImage(null);
//				break;
//			case 4:
//				info34.setText(v.getRegistration());
//				fecha34.setText(v.getEntryDate().toString());
//				img34.setImage(null);
//				break;
//			default:
//				break;
//			}
//		}

	}

	private void initUltimasVentas() {
		// TODO obtener ultimas ventas realizadas

	}

	private void initPropuestasFinalizan() {
		// TODO obtener propuestas de venta en orden de mas antiguas a mas recientes

	}
}
