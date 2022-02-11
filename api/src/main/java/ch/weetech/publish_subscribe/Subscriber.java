package ch.weetech.publish_subscribe;

import javax.naming.InitialContext;

import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import jakarta.jms.Topic;
import jakarta.jms.TopicConnection;
import jakarta.jms.TopicConnectionFactory;
import jakarta.jms.TopicSession;
import jakarta.jms.TopicSubscriber;

public class Subscriber {

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

        // create a topic subscriber
        TopicSubscriber topicSubscriber = topicSession.createSubscriber(topic);

        // start the connection
        topicConn.start();

        // receive the message
        TextMessage message = (TextMessage) topicSubscriber.receive();

        // print the message
        System.out.println("Message received: " + message.getText());

        // close the topic connection
        topicConn.close();
    }

}
