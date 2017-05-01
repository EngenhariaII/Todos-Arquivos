/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controle.FuncionarioControl;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class TelaLoginController implements Initializable {

    @FXML
    private TextField txtLogin;
    private PasswordField txtSenha;
    @FXML
    private Button btSair;
    @FXML
    private Button btLogar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void clkSair(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    private void clkLogin(ActionEvent event) throws IOException {
        String login;
        String senha;
        login = txtLogin.getText();
        senha = txtSenha.getText();
        try{
            if(new FuncionarioControl().verificausuario(login,senha)){
                ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("TelaMenuPrincipal.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                //stage.setTitle("Menu Principal");
                //stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
}
