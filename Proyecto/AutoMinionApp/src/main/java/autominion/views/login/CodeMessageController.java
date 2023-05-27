package autominion.views.login;

import java.io.IOException;

import autominion.main.AutominionMainApp;
import autominion.utils.SendEmail;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class CodeMessageController {

	@FXML // fx:id="btnBack"
	private Button btnBack; // Value injected by FXMLLoader

	@FXML // fx:id="btnNext"
	private Button btnNext; // Value injected by FXMLLoader

	@FXML // fx:id="btnSend"
	private Button btnSend; // Value injected by FXMLLoader

	@FXML // fx:id="txtEmail"
	private TextField txtEmail; // Value injected by FXMLLoader

	@FXML
	private void mostrarAlertError() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Error");
		alert.setContentText("Usuario o correo electrónico no válidos, vuelve a introducirlos");
		alert.showAndWait();
	}

	@FXML
	private void initialize() throws IOException {
		actionsButtons();
	}

	private void actionsButtons() {
		EventHandler<ActionEvent> eventBack = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				closeView();
			}
		};
		btnBack.setOnAction(eventBack);

		EventHandler<ActionEvent> eventForgot = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				sendEmail();
			}
		};
		btnSend.setOnAction(eventForgot);

		EventHandler<ActionEvent> eventPopup = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				comprobar();
			}
		};
		btnNext.setOnAction(eventPopup);

	}

	private void sendEmail() {
		SendEmail.email(txtEmail.getText(), AutominionMainApp.pin);
	}

	/**
	 * Comprueba si el usuario es correcto y el pin que hemos enviado al correo
	 * electronico, si es correcto te lleva a la pestaña para cambiar la contraseña
	 */
	private void comprobar() {
		if (txtEmail.getText().equals(AutominionMainApp.pin + "")) {
			closeView();
		} else {
			mostrarAlertError();
		}
	}

	private void closeView() {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();

	}
}