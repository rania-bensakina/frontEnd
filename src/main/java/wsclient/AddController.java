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
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author bensakina
 */
public class AddController implements Initializable {

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField banqueid = new TextField();
    @FXML
    TextField ncompte = new TextField();
    @FXML
    TextField ncompteeuro = new TextField();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void ok() {
        try {
            Banque banque = new Banque();
            banque.setNbanque(Long.valueOf(banqueid.getText().toString()));
            banque.setNcompte(Float.valueOf(ncompte.getText().toString()));
            banque.setNcompteeuro(Float.valueOf(ncompteeuro.getText().toString()));
            Banque banqueexists = new BankService(restTemplate).consultBanque(banque.getNbanque());
            if(banqueexists==null){
            new BankService(restTemplate).addBanque(banque);
            wsclient.MainController.banquearraylist.add(banque);
            }else{
                JOptionPane.showMessageDialog(null,"Cette Banque existe déja","Erreur",JOptionPane.ERROR_MESSAGE);
            }
            Stage stage = (Stage) banqueid.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Entrer des nombres valides SVP !","Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
}
