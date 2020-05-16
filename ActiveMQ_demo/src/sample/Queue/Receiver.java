package sample.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Receiver extends Thread{
    public static void main() {

        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageConsumer messageConsumer = null;
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
            messageConsumer = session.createConsumer(destination);
           /* Message message = messageConsumer.receive();
            TextMessage textMessage = (TextMessage) message;
            System.out.println(textMessage.getText());*/

            while (true){
                TextMessage textMessage = (TextMessage) messageConsumer.receive();
                if(null == textMessage)
                    break;
                else
                    System.out.println("Receiver:"+textMessage.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                messageConsumer.close();
                session.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public void run(){
        Receiver.main();
    }

    public void start(){
        System.out.println("进入Receiver线程");
        Thread T = new Thread(this,"ReceiverThread");
        T.start();
    }
}
