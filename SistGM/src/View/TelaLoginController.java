/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controle.EmpresaControl;
import Controle.FuncionarioControl;
import Util.Validadores;
import com.jfoenix.controls.*;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class TelaLoginController implements Initializable {

     @FXML
    private JFXTextField txtLogin;

    @FXML
    private JFXPasswordField txtSenha;

    @FXML
    private JFXButton btLogar;

    @FXML
    private JFXButton btSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estado();
    }    

    @FXML
    private void clkSair(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    private void clkLogin(ActionEvent event) throws IOException {
        String login;
        String senha;
        login = txtLogin.getText();
        senha = txtSenha.getText();
        try{
            if(new FuncionarioControl().verificausuario(login,senha)){
                ((Stage)((Button)event.getSource()).getScene().getWindow()).close();
                if(new EmpresaControl().verificaempresa()){
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/View/MenuPrincipal.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Tela Menu");
                    stage.showAndWait();
                }else{
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/View/CadastroEmpresa.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Cadastra Empresa");
                    stage.showAndWait();
                }
                
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void estado(){
        Validadores v = new Validadores();
        txtLogin.setOnKeyPressed(k ->{
              final KeyCombination TAB = new KeyCodeCombination(KeyCode.TAB);
            if (TAB.match(k)) {
              if(v.validacamposlogin(txtLogin.getText())) 
                txtSenha.requestFocus();
              else{
                  Alert alert = new Alert(Alert.AlertType.WARNING);
                  alert.setHeaderText("Cuidado");
                  alert.setContentText("Login Maior Que 10 Caracteres ou contem numeros");
                  alert.showAndWait();
                  txtLogin.requestFocus();
              }
            }
        });
        txtSenha.setOnKeyPressed(k ->{
              final KeyCombination TAB = new KeyCodeCombination(KeyCode.TAB);
            if (TAB.match(k)) {
              if(!v.validacampossenha(txtSenha.getText())){
                  Alert alert = new Alert(Alert.AlertType.WARNING);
                  alert.setHeaderText("Cuidado");
                  alert.setContentText("Senha com mais de 8 Caracteres");
                  alert.showAndWait();
              }   
            }
        });
    }
    
}
