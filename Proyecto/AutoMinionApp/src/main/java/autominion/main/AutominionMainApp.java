package autominion.main;

import java.io.IOException;

import org.hibernate.Session;

import autominion.utils.HibernateUtil;
import autominion.views.jefes.MenuJefesController;
import autominion.views.jefes.VehiculosJefeController;
import autominion.views.login.LoginViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AutominionMainApp extends Application {

	private Stage primaryStage;
	private static int randomNumber = (int) (Math.random() * 999999) + 100000;
	public static int pin = randomNumber;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Apertura de sesión.
		final Session session = HibernateUtil.getSessionFactory().openSession();

		this.primaryStage = primaryStage; 
		this.primaryStage.setTitle("Consesionarios AutoMinion");

		initLogin(session);
//		initMenuJefes(session);
		
		// Set the application icon.
		this.primaryStage.getIcons().add(new Image("file:./target/resources/images/icon.png"));
	}

	public void initLogin(Session session) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/login/LoginView.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			LoginViewController controlador = loader.getController();
			controlador.setSession(session);
			controlador.setStage(primaryStage);
			
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initMenuJefes(Session session) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/jefes/MenuJefesView.fxml"));
			BorderPane rootLayout = (BorderPane) loader.load();

			MenuJefesController controller = loader.getController();
			controller.setRootLayout(rootLayout);
			controller.setStage(primaryStage);
			controller.setSession(session);

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			initVehiculosView(rootLayout, session);
			
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initVehiculosView(BorderPane rootLayout, Session session) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/autominion/views/jefes/VehiculosJefeView.fxml"));
			AnchorPane root = loader.load();
			
			VehiculosJefeController controller = loader.getController();
			controller.setSession(session);
			controller.setPrimaryStage(primaryStage);
			controller.setRootLayout(rootLayout);
			controller.verTarjetas(controller.ordenarVehiculosFechas());
			
//			EventHandler<ActionEvent> eventNew = new EventHandler<ActionEvent>() {
//				public void handle(ActionEvent e) {
//					newVehiculo(session, rootLayout);
//				}
//			};
//			controller.btnNewVehiculo.setOnAction(eventNew);

			rootLayout.setCenter(root);
			primaryStage.show();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
//	private void newVehiculo(Session session, BorderPane rootLayout) {
//		try {
//			FXMLLoader loader = new FXMLLoader(
//					getClass().getResource("/autominion/views/jefes/NewVehiculoView.fxml"));
//			AnchorPane root = loader.load();
//			NuevoVehiculoController controller = loader.getController();
//			controller.setSession(session);
//			controller.setRootLayout(rootLayout);
//			controller.setBoxes();
//			controller.setMecanicos(false);
//
//			rootLayout.setCenter(root);
//
//			primaryStage.show();
//		} catch (Throwable e1) {
//			e1.printStackTrace();
//		}
//	}

	public static void main(String[] args) {
		launch(args);
	}
}
