package br.pucrs.views;

import br.pucrs.utils.Authenticator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

	private static Scene scene;
	private static Authenticator authenticator;

	@Override
	public void start(Stage stage) {
		authenticator = new Authenticator();
		String javaVersion = System.getProperty("java.version");
		String javafxVersion = System.getProperty("javafx.version");

		stage.setTitle("Automação de Build com Maven - Exemplo");
		stage.getIcons().add(new Image("mvn.png"));

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(15);
		grid.setPadding(new Insets(25, 20, 40, 20));

		Text title = new Text("Login");
		title.setFont(Font.font("Lucida Console", FontWeight.NORMAL, 18));
		HBox titleBox = new HBox(10);
		titleBox.setAlignment(Pos.CENTER);
		titleBox.getChildren().add(title);
		grid.add(titleBox, 0, 0, 5, 1);

		Label usernameLabel = new Label("Usuário:");
		grid.add(usernameLabel, 0, 1);

		TextField usernameInput = new TextField();
		grid.add(usernameInput, 1, 1);

		Label passwordLabel = new Label("Senha:");
		grid.add(passwordLabel, 0, 2);

		PasswordField passwordInput = new PasswordField();
		grid.add(passwordInput, 1, 2);

		Button loginBtn = new Button("Entrar");
		loginBtn.setStyle("-fx-background-color: #0081b8; -fx-text-fill: #fff;");
		loginBtn.setPrefSize(100, 25);
		loginBtn.setCursor(Cursor.HAND);
		loginBtn.setDefaultButton(true);
		HBox loginBtnBox = new HBox(10);
		loginBtnBox.setAlignment(Pos.BOTTOM_CENTER);
		loginBtnBox.getChildren().add(loginBtn);
		grid.add(loginBtnBox, 0, 3, 5, 1);

		loginBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String username = usernameInput.getText();
				String password = passwordInput.getText();
				boolean auth = authenticator.auth(username, password);

				Alert alert;
				if (auth) {
					alert = new Alert(AlertType.INFORMATION, "Você está autorizado a acessar o sistema, %s!".formatted(username));
					alert.setTitle(stage.getTitle());
					alert.setHeaderText("Autenticação concluída");
				} else {
					alert = new Alert(AlertType.ERROR, "Nome de usuário ou senha incorretos!");
					alert.setTitle(stage.getTitle());
					alert.setHeaderText("Erro na autenticação");
				}
				alert.initOwner(stage);
				alert.showAndWait();

				passwordInput.setText("");
			}
		});

		Label info = new Label("JavaFX %s, running on Java %s".formatted(javafxVersion, javaVersion));
		info.setFont(Font.font("Lucida Console", FontWeight.NORMAL, 10));
		HBox infoBox = new HBox(5);
		infoBox.setAlignment(Pos.BOTTOM_CENTER);
		infoBox.getChildren().add(info);
		grid.add(infoBox, 0, 7, 5, 1);

		scene = new Scene(grid, 400, 380);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
