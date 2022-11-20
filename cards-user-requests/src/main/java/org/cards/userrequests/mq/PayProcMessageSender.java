package org.cards.userrequests.mq;

public interface PayProcMessageSender {

    void sendMessage(UserRequestMqSend messageDto);
}
