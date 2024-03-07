package com.example.template;

import com.example.template.repo.SQLRepo;
import com.example.template.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SQLRepo repo = new SQLRepo("src/melodii.db");
        Service s = new Service(repo);

        // ne facem un hello controller

        // Set the Service s in the controller
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        HelloController hc = new HelloController(s);
        fxmlLoader.setControllerFactory(param -> hc);
        //IN ORDINEA ASTA SE DECLARA FXML-URILE
        //ORICE LIST VIEW SE INITIALIZEAZA CAND IL DECLARAM, CA SA NU NE DEA NULL PTR EXCEPTION

//        s.add(100,"Coldplay", "Yellow", "pop", "03:20");
//        s.add(102, "Coldplay", "Scientist", "Alternative", "02:40");
//        s.add(101, "Maroon 5", "Girls like you", "Pop", "04:00");
//        s.add(105, "Kiss", "never", "rock", "05:15");
//        s.add(104, "Vama", "Epilog","hippie", "03:50");
        hc.loadCakes();
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
        hc.loadCakes();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}