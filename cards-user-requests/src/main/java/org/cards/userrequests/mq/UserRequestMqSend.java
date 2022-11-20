package org.cards.userrequests.mq;

import lombok.Data;
import org.cards.userrequests.model.UserRequestType;

import java.time.LocalDateTime;

@Data
public class UserRequestMqSend {

    private Long requestId;
    private String pan;
    private UserRequestType userRequestType;
    private LocalDateTime requestTime;

}
