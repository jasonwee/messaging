package ch.weetech.publish_subscribe;

import javax.naming.InitialContext;

import jakarta.jms.DeliveryMode;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import jakarta.jms.Topic;
import jakarta.jms.TopicConnection;
import jakarta.jms.TopicConnectionFactory;
import jakarta.jms.TopicPublisher;
import jakarta.jms.TopicSession;

public class Publisher {

    public static void main(String[] args) throws Exception {

        // get the initial context
        InitialContext ctx = new InitialContext();

        // lookup the topic object
        Topic topic = (Topic) ctx.lookup("java:comp/env/topic/topic0");

        // lookup the topic connection factory
        TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx.lookup("java:comp/env/topic/connectionFactory");

        // create a topic connection
        TopicConnection topicConn = connFactory.createTopicConnection();

        // create a topic session
        TopicSession topicSession = topicConn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        // create a topic publisher
        TopicPublisher topicPublisher = topicSession.createPublisher(topic);
        topicPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        // create the "Hello World" message
        TextMessage message = topicSession.createTextMessage();
        message.setText("Hello World");

        // publish the messages
        topicPublisher.publish(message);

        // print what we did
        System.out.println("Message published: " + message.getText());

        // close the topic connection
        topicConn.close();

    }

}
