package edu.gemini.jms.activemq.broker;

import edu.gemini.jms.api.Broker;

import java.io.Serializable;
import java.util.logging.Logger;

import static edu.gemini.jms.activemq.broker.ActiveMQBroker.activemq;

/**
 * Thin wrapper of ActiveMQBroker to expose the configuration to the ConfigAdmin service
 *
 * @author cquiroz
 */
public class ActiveMQBrokerComponent implements Serializable {
    private static final Logger LOG = Logger.getLogger(ActiveMQBrokerComponent.class.getName());

    private final Broker _broker;

    private final String url;

    public ActiveMQBrokerComponent(boolean useJmx,
                                   boolean persistent,
                                   String brokerName,
                                   String url,
                                   boolean deleteMsgOnStartup,
                                   boolean useAdvisoryMessages,
                                   int jmxRmiServerPort,
                                   int jmxConnectorPort,
                                   double memoryPercentage,
                                   int maxMessagesLimit,
                                   int maxStorageMB) {
        this.url = url;

        _broker = activemq()
                .name(brokerName)
                .url(url)
                .useJmx(useJmx)
                .persistent(persistent)
                .useAdvisoryMessages(useAdvisoryMessages)
                .deleteMsgOnStartup(deleteMsgOnStartup)
                .jmxConnectorPort(jmxConnectorPort)
                .jmxRrmiServerPort(jmxRmiServerPort)
                .memoryPercentage(memoryPercentage)
                .maxStorageMB(maxStorageMB)
                .maxMessagesLimit(maxMessagesLimit)
                .build();
    }

    public synchronized void startBroker() {
        LOG.info("Starting ActiveMQBroker broker with URL:" + url);
        // Start in a separate thread, otherwise there is a risk of a race condition
        new Thread(new Runnable() {
            @Override
            public void run() {
                _broker.start();
            }
        }).start();

    }

    public synchronized void stopBroker() {
        _broker.shutdown();
    }
}
