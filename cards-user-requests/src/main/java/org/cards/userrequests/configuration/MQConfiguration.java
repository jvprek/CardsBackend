package org.cards.userrequests.configuration;

import lombok.AllArgsConstructor;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.cards.userrequests.mq.UserRequestMqSend;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.oxm.xstream.XStreamMarshaller;

@Configuration
@EnableJms
@AllArgsConstructor
public class MQConfiguration {

    private final MQProperties properties;

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        var connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setTrustAllPackages(true);
        connectionFactory.setBrokerURL(properties.getBrokerUrl());
        connectionFactory.setPassword(properties.getBrokerUser());
        connectionFactory.setUserName(properties.getBrokerPassword());
        return connectionFactory;
    }

    @Bean
    public MessageConverter messageConverter() {
        var converter = new MarshallingMessageConverter(xmlMarshaller());
        converter.setTargetType(MessageType.TEXT);
        return converter;
    }

    @Bean
    XStreamMarshaller xmlMarshaller() {
        var marshaller = new XStreamMarshaller();
        marshaller.setSupportedClasses(UserRequestMqSend.class);
        return marshaller;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        var template = new JmsTemplate();
        template.setConnectionFactory(activeMQConnectionFactory());
        template.setMessageConverter(messageConverter());
        template.setDeliveryPersistent(true);
        return template;
    }
}
