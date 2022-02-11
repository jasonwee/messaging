package ch.weetech.point2point;

import javax.naming.InitialContext;

import jakarta.jms.Queue;
import jakarta.jms.QueueConnection;
import jakarta.jms.QueueConnectionFactory;
import jakarta.jms.QueueReceiver;
import jakarta.jms.QueueSession;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;

public class Receiver {

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
	       QueueSession queSession = queConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	 
	       // create a queue receiver
	       QueueReceiver queReceiver = queSession.createReceiver(queue);

	       // start the connection
	       queConn.start();

	       // receive a message
	       TextMessage message = (TextMessage) queReceiver.receive();

	       // print the message
	       System.out.println("Message Received: " + message.getText());

	       // close the queue connection
	       queConn.close();
	    }
}
