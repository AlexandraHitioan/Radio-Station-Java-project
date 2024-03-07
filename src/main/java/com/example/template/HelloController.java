package com.example.template;

import com.example.template.domain.Melodie;
import com.example.template.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HelloController {
    @FXML
    private Button btnFilter;
    @FXML
    private Button btnReset;

    @FXML
    private Label lblObjects;

    @FXML
    private ListView<String> lstObjects = new ListView<String>();

    @FXML
    private TextField txtInputFilter;



    private Service s;
    public HelloController(Service s)
    {
        this.s = s;
    }

    ObservableList<Melodie> allProducts = FXCollections.observableList(new ArrayList<Melodie>()); //facem lista de torturi basically

    public HelloController() {
    }


    public void loadCakes() {
        allProducts.clear();
        List<Melodie> melodii= s.getAll().stream()
                .sorted(Comparator.comparing(Melodie::getFormatie, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Melodie::getTitlu, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());

        ObservableList<String> cakeStrings = FXCollections.observableArrayList(); //lista de strings pentru GUI

        if (!melodii.isEmpty()) {
            for ( var m: melodii) {
                cakeStrings.add(m.toString());
            }
            lstObjects.setItems(cakeStrings);
            allProducts.addAll(melodii);
        } else {
            System.out.println("List is empty!");
        }
    }


    public void btnFiltreaza(ActionEvent actionEvent) {
        List<Melodie> rez = new ArrayList<>();
        ObservableList<String> filteredStrings = FXCollections.observableArrayList();
        String keyWord = txtInputFilter.getText();
        rez = s.filtreaza(keyWord);
        if(rez.size()==0){
            Alert hopa = new Alert(Alert.AlertType.ERROR);
            hopa.setHeaderText("No results, wrong input!");
            hopa.show();
        }
        else{
            for (Melodie m : rez) {
                filteredStrings.add(m.toString());
            }
        }
          lstObjects.setItems(filteredStrings);
    }

    public void btnReset(ActionEvent actionEvent) {
       txtInputFilter.setText("");
        ObservableList<String> stringMelodii = FXCollections.observableArrayList();
        List<Melodie> rez = s.getAll();
        for (Melodie m : s.getAll()) {
           stringMelodii.add(m.toString());
        }
        lstObjects.setItems(stringMelodii);
    }

}