/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsclient;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class EditController implements Initializable {


    private final RestTemplate restTemplate = new RestTemplate();
    @FXML
    TextField ncompte = new TextField();
    @FXML
    TextField ncompteeuro = new TextField();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    @FXML
    public void okedit() {
        try {
            Banque banque =  wsclient.MainController.updated;
            banque.setNcompte(Float.valueOf(ncompte.getText().toString()));
            banque.setNcompteeuro(Float.valueOf(ncompteeuro.getText().toString()));
            new BankService(new RestTemplate()).modifierBanque(banque);
           
            Stage stage = (Stage) ncompte.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Entrer des valeurs nombres valides SVP !","Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
