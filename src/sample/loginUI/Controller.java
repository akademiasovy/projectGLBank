package sample.loginUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Employee;
import sample.database.Database;
import sample.mainUI.MainController;


import java.io.IOException;
import java.sql.Connection;

public class Controller {

    public TextField txtLogin;
    public Button btnLogIn;
    public PasswordField txtPassword;
    public Label lblError;

    public Controller() {

    }

    public void btnClick(ActionEvent actionEvent) {
        lblError.setVisible(false);
        Database db=Database.getInstance();
        Employee emp = db.getEmployee(txtLogin.getText(),txtPassword.getText());
        if(emp==null){
            lblError.setVisible(true);
        }
        else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../mainUI/main.fxml"));
            Parent root1;
            try {
                root1 = (Parent) fxmlLoader.load();

            Stage stage2 = new Stage();
            stage2.setTitle("BANK APPLICATION");
            stage2.setScene(new Scene(root1));
            stage2.show();

                MainController mc;
                mc = fxmlLoader.getController();
                mc.setEmplyee(emp);

            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            stage.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
