package autominion.views.ventas;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

import autominion.database.persistence.entities.Vehicles;
import autominion.database.services.implementations.VehicleManagementServiceImpl;
import autominion.database.services.interfaces.VehicleManagementServiceI;
import autominion.views.mecanicos.NuevoVehiculoController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class VehiculosController {
	@FXML // fx:id="gridPane"
	private GridPane gridPane; // Value injected by FXMLLoader
	@FXML // fx:id="btnOrdenar"
	private Button btnOrdenar; // Value injected by FXMLLoader
	@FXML
    public Button btnNewVehiculo;
	private Session session;
    private BorderPane rootLayout;
    private Stage primaryStage;
	private VehicleManagementServiceI v;
	private boolean ordenado = false;

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

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@FXML
	private void initialize() {
		actionButtons();
	}
	
	private void actionButtons() {
		EventHandler<ActionEvent> eventOrdenar = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					if(!ordenado) {
						verTarjetas(ordenarVehiculosFechas());
					} else {
						verTarjetas(obtenerVehiculos());
					}
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
			}
		};
		btnOrdenar.setOnAction(eventOrdenar);
		
		EventHandler<ActionEvent> eventNewVehicle = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				goNewVehicle();
			}
		};
		btnNewVehiculo.setOnAction(eventNewVehicle);
	}

	public void verTarjetas(List<Vehicles> vehiculos) {
		int contador = 0;
		// Obtenemos el resto para saber cuantas tarjetas vamos a necesitar
		double resto = vehiculos.size() % 5.0;
		// Numero de vehiculos
		double numeroVehiculos = vehiculos.size();
		// El numero de filas que vamos a necesitar
		double filasVehiculos = numeroVehiculos / 5.0;
		// Numero de filas restantes
		double filasRestantes = vehiculos.size() / 5;
		double aux = 5;

		try {
			for (int j = 0; j < numeroVehiculos / 5.0; j++) {
				// Si es la ultima fila
				if (filasVehiculos < 1)
					aux = numeroVehiculos;

				for (int i = 0; i < aux; i++) {
					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/autominion/views/ventas/TarjetasRecientesView.fxml"));
					AnchorPane root = loader.load();
					
					TarjetasRecientesController controller = loader.getController();
					controller.getLblInfo().setText(
							vehiculos.get(contador).getModel() + " - " + vehiculos.get(contador).getRegistration());
					controller.getLblFecha().setText(vehiculos.get(contador).getEntryDate() + "");
					controller.setVehiculo(vehiculos.get(contador));
					controller.setSession(session);
					controller.setRootLayout(rootLayout);
					controller.setPrimaryStage(primaryStage);

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

	public List<Vehicles> ordenarVehiculosFechas() {
		v = new VehicleManagementServiceImpl(session);
		try {
			ordenado = true;
			return v.getVehiclesOldDate();
		} catch (Exception e) {
			System.err.println("Error al obtener los vehiculos ordenados");
		}
		return null;
	}

	public List<Vehicles> obtenerVehiculos() {
		v = new VehicleManagementServiceImpl(session);
		try {
			ordenado = false;
			return v.searchAll();
		} catch (Exception e) {
			System.err.println("Error al obtener los vehiculos");
		}
		return null;
	}
	
	public void goNewVehicle() {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/autominion/views/jefes/NewVehiculoView.fxml"));
			AnchorPane root = loader.load();
			NuevoVehiculoController controller = loader.getController();
			controller.setSession(session);
			controller.setRootLayout(rootLayout);
			controller.setBoxes();
			controller.setMecanicos(false);

			rootLayout.setCenter(root);

			primaryStage.show();
		} catch (Throwable e1) {
			e1.printStackTrace();
		}
	}
}
