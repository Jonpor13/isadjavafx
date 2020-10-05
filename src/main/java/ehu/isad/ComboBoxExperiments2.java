package ehu.isad;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.*;
import java.io.*;

public class ComboBoxExperiments2 extends Application {
    public static void main(String[] args)  {

        Application.launch(args);

    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("ComboBox Experiment 2");

        Label labelBox = new Label();
        ComboBox comboBox = new ComboBox();
        comboBox.getItems().add("BTC");
        comboBox.getItems().add("ETH");
        comboBox.getItems().add("LTC");
        comboBox.getSelectionModel().selectFirst();

        comboBox.setEditable(false);

        comboBox.setOnAction(e -> {
            try {
                String txanp = comboBox.getValue().toString().toLowerCase();
                System.out.print(txanp);
                labelBox.setText("1" +txanp.toUpperCase()+ " = " + web(txanp));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        String txanp = comboBox.getValue().toString().toLowerCase();
        System.out.print(txanp);
        labelBox.setText("1" +txanp.toUpperCase()+ " = " + web(txanp));


        VBox vbox = new VBox(labelBox, comboBox);

        Scene scene = new Scene(vbox, 200, 120);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public float web(String txanp) throws IOException {

        String inputLine;
        URL coin = new URL("https://api.gdax.com/products/" +txanp+ "-eur/ticker");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(coin.openStream()));

        inputLine = in.readLine();
        in.close();

        Gson gson = new Gson();
        Txanpona txanpon = gson.fromJson(inputLine, Txanpona.class);
        float price = txanpon.price;
        return price;

    }
}