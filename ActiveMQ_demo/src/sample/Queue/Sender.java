package sample.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Sender extends Thread {
    static String[] messageList = {
            "这是我的第1条消息",
            "这是我的第2条消息",
            "这是我的第3条消息",
            "这是我的第4条消息",
            "这是我的第5条消息",
            "这是我的第6条消息",
            "这是我的第7条消息",
    };
    public static void main() {
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session =null;
        MessageProducer messageProducer = null;
        Destination destination;
        connectionFactory = new ActiveMQConnectionFactory(
                "admin",
                "admin",
                "tcp://localhost:61616"
        );
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("刘子文");
            messageProducer = session.createProducer(destination);
            Scanner sc = new Scanner(System.in);
            int countOfMessage = sc.nextInt();
            String message = "Sender:我要发送"+countOfMessage+"条消息了";
            TextMessage textMessage = session.createTextMessage(message);
            messageProducer.send(textMessage);
            System.out.println(message);

            for(int i = 0; i < countOfMessage; ++i){
                //message = messageList[i];
                message = sc.next();
                textMessage = session.createTextMessage(message);
                messageProducer.send(textMessage);
                System.out.println("Sender:"+message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                messageProducer.close();
                session.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public void run(){
        Sender.main();
    }

    public void start(){
        System.out.println("进入Sender线程");
        Thread T = new Thread(this,"SenderThread");
        T.start();
    }
}
