package edu.gemini.aspen.gmp.services;

import edu.gemini.jms.api.JmsProvider;
import org.mockito.Matchers;
import org.mockito.Mockito;

import javax.jms.*;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockedJmsArtifactsTestBase {
    protected ConnectionFactory connectionFactory;
    protected Session session;
    protected JmsProvider provider;
    protected MessageProducer producer;
    protected MessageConsumer consumer;
    protected MapMessage mapMessage;
    protected Destination destination = mock(Destination.class);

    public void createMockedObjects() {
        provider = Mockito.mock(JmsProvider.class);
        try {
            mockSessionProducerAndConsumer();
            when(provider.getConnectionFactory()).thenReturn(connectionFactory);
        } catch (JMSException e) {
            // Shouldn't happen as we are mocking
            e.printStackTrace();
        }
    }

    protected void mockSessionProducerAndConsumer() throws JMSException {
        session = mockSessionCreation();

        producer = Mockito.mock(MessageProducer.class);
        when(session.createProducer(Matchers.<Destination>anyObject())).thenReturn(producer);
        consumer = Mockito.mock(MessageConsumer.class);
        when(session.createConsumer(Matchers.<Destination>anyObject())).thenReturn(consumer);

        Queue queue = mock(Queue.class);
        when(session.createQueue(anyString())).thenReturn(queue);

        Topic topic = mock(Topic.class);
        when(session.createTopic(anyString())).thenReturn(topic);

        mapMessage = Mockito.mock(MapMessage.class);
        when(mapMessage.getJMSReplyTo()).thenReturn(destination);
        when(session.createMapMessage()).thenReturn(mapMessage);

        TextMessage textMessage = Mockito.mock(TextMessage.class);
        when(session.createTextMessage(any(String.class))).thenReturn(textMessage);
    }

    private Session mockSessionCreation() throws JMSException {
        Session session = Mockito.mock(Session.class);
        // Mock connection factory
        connectionFactory = Mockito.mock(ConnectionFactory.class);

        // Mock connection
        Connection connection = Mockito.mock(Connection.class);
        when(connectionFactory.createConnection()).thenReturn(connection);

        // Mock session
        when(connection.createSession(Matchers.anyBoolean(), Matchers.anyInt())).thenReturn(session);
        return session;
    }
}
