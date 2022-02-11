package ch.weetech.point2point;

// https://howtodoinjava.com/jms/jms-point-to-point-message-example/

/*

    <Resource name="queue/connectionFactory"
              auth="Container"
	      type="org.apache.activemq.artemis.jms.client.ActiveMQQueueConnectionFactory"
	      description="JMS Connection Factory"
              factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
	      brokerURL="tcp://localhost:61616" />
    <Resource name="queue/queue0"
	      auth="Container"
	      type="org.apache.activemq.artemis.jms.client.ActiveMQQueue"
	      description="JMS Queue"
              factory="org.apache.activemq.artemis.jndi.JNDIReferenceFactory"
	      address="ExampleQueue" />

*/