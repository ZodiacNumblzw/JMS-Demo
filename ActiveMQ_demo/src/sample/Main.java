package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Queue.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }



    public static void main(String[] args) {
        //launch(args);

        //多线程
        /*Sender sender = new Sender();
        sender.start();

        Receiver receiver = new Receiver();
        receiver.start();*/

        //异步
        Sender.main();
        Receiver.main();
    }


}
