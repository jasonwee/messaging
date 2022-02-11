package ch.weetech.point2point;

import javax.naming.InitialContext;

import jakarta.jms.DeliveryMode;
import jakarta.jms.Queue;
import jakarta.jms.QueueConnection;
import jakarta.jms.QueueConnectionFactory;
import jakarta.jms.QueueSender;
import jakarta.jms.QueueSession;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;

public class Sender
{
    public static void main(String[] args) throws Exception
    {
       // get the initial context
       InitialContext context = new InitialContext();

       // lookup the queue object
       Queue queue = (Queue) context.lookup("java:comp/env/queue/queue0");

       // lookup the queue connection factory
       QueueConnectionFactory conFactory = (QueueConnectionFactory) context.lookup ("java:comp/env/queue/connectionFactory");

       // create a queue connection
       QueueConnection queConn = conFactory.createQueueConnection();

       // create a queue session
       QueueSession queSession = queConn.createQueueSession(false, Session.DUPS_OK_ACKNOWLEDGE);

       // create a queue sender
       QueueSender queSender = queSession.createSender(queue);
       queSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

       // create a simple message to say "Hello World"
       TextMessage message = queSession.createTextMessage("Hello World");

       // send the message
       queSender.send(message);

       // print what we did
       System.out.println("Message Sent: " + message.getText());

       // close the queue connection
       queConn.close();
    }
}
