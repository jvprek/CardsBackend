package org.cards.userrequests.mq;


import lombok.AllArgsConstructor;
import org.cards.userrequests.configuration.MQProperties;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PayProcMessageProducerImpl implements PayProcMessageSender {

    private final MQProperties properties;
    private final JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(UserRequestMqSend messageDto) {
        jmsTemplate.convertAndSend(properties.getOutQueue(),messageDto);
    }
}
