package wsclient;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author bensakina
 */
public class MainController implements Initializable {

    private final RestTemplate restTemplate = new RestTemplate();

    @FXML
    TableView<Banque> tableview = new TableView<>();
    @FXML
    TableColumn<Banque, Integer> nBanque = new TableColumn();
    @FXML
    TableColumn<Banque, Float> nCompte = new TableColumn();
    @FXML
    TableColumn<Banque, Float> nCompteEuro = new TableColumn();
    Image addimage = new Image(getClass().getResourceAsStream("/add.png"));
    Image editimage = new Image(getClass().getResourceAsStream("/edit.png"));

    Image removeimage = new Image(getClass().getResourceAsStream("/remove.png"));

    ImageView imgview1 = new ImageView();
    ImageView imgview2 = new ImageView();
    ImageView imgview3 = new ImageView();

    @FXML
    JFXButton add = new JFXButton();
    @FXML
    JFXButton edit = new JFXButton();
    @FXML
    JFXButton remove = new JFXButton();
    public static ObservableList<Banque> myobservablelist;

    /**
     * Initializes the controller class.
     */
    public static ArrayList<Banque> banquearraylist = new ArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // initializing button's icons
        imgview1.setImage(addimage);
        imgview1.setFitHeight(50);
        imgview1.setFitWidth(50);
        imgview2.setImage(editimage);
        imgview2.setFitHeight(50);
        imgview2.setFitWidth(50);
        imgview3.setImage(removeimage);
        imgview3.setFitHeight(50);
        imgview3.setFitWidth(50);
        add.setGraphic(imgview1);
        edit.setGraphic(imgview2);
        remove.setGraphic(imgview3);
        // gettings list of banques
        banquearraylist = new ArrayList(new BankService(restTemplate).getAllBanks());
        // binding banque to list
        nBanque.setCellValueFactory(new PropertyValueFactory<>("nbanque"));
        nCompte.setCellValueFactory(new PropertyValueFactory<>("ncompte"));
        nCompteEuro.setCellValueFactory(new PropertyValueFactory<>("ncompteeuro"));
        tableview.getColumns().setAll(nBanque, nCompte, nCompteEuro);
        myobservablelist = FXCollections.observableArrayList(banquearraylist);
        refreshmytable();
    }

    @FXML
    public void addBanque() throws IOException {
        Stage s = new Stage();
        s.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("/add.fxml"));
        s.setTitle("Ajouter une Banque");
        s.setScene(new Scene(root));
        s.showAndWait();
        refreshmytable();
    }
    public static Banque updated;

    @FXML
    public void editBanque() throws IOException {
        updated = tableview.getSelectionModel().getSelectedItem();
        if (updated == null) {
            JOptionPane.showMessageDialog(null, "Selectionner un element d'abord ","info",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Stage s = new Stage();
        s.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("/edit.fxml"));
        s.setTitle("Updater une Banque");
        s.setScene(new Scene(root));
        s.showAndWait();
        refreshmytable();
    }

    @FXML
    public void supprimerBanque() {
        Banque banquetodelete = tableview.getSelectionModel().getSelectedItem();
        if (banquetodelete == null) {
            JOptionPane.showMessageDialog(null, "Selectionner un element d'abord ","info",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int answer = JOptionPane.showConfirmDialog(null, "Etes vous sure de vouloir supprimer?", "suppression", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            new BankService(restTemplate).deleteBank(banquetodelete.getNbanque());
            banquearraylist.remove(tableview.getSelectionModel().getSelectedItem());
            refreshmytable();
        }
    }

    private void refreshmytable() {
        myobservablelist = FXCollections.observableArrayList(banquearraylist);
        tableview.setItems(myobservablelist);
        tableview.refresh();
    }

}
