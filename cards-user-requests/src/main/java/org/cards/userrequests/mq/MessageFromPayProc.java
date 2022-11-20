package org.cards.userrequests.mq;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageFromPayProc {
    private Long requestId;
    private LocalDateTime completionTime;
}
