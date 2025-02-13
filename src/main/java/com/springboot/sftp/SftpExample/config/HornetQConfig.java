package com.springboot.sftp.SftpExample.config;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.api.jms.JMSFactoryType;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.jms.ConnectionFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class HornetQConfig {

    @Value("${hornetq.host}")
    private String host;

    @Value("${hornetq.port}")
    private int port;

    @Value("${hornetq.user}")
    private String user;

    @Value("${hornetq.password}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        // Define transport configuration for Netty
        Map<String, Object> connectionParams = new HashMap<>();
        connectionParams.put("host", host);
        connectionParams.put("port", port);

        TransportConfiguration transportConfig = new TransportConfiguration(
            NettyConnectorFactory.class.getName(),
            connectionParams
        );

        // Create ConnectionFactory with username/password
        return HornetQJMSClient.createConnectionFactoryWithoutHA(
            JMSFactoryType.CF, // ConnectionFactory type
            transportConfig
          //  user,    // Username
           // password // Password
        );
    }
}