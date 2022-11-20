package org.cards.userrequests.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.params.mq")
public class MQProperties {

    private String brokerUrl;
    private String brokerUser;
    private String brokerPassword;
    private String inQueue;
    private String outQueue;
}
