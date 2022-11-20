package org.cards.userrequests.service;

import org.cards.userrequests.controller.UserRequestApiRequestDto;
import org.cards.userrequests.controller.UserRequestApiResponseDto;
import org.cards.userrequests.mq.MessageFromPayProc;

public interface UserRequestsService {

    UserRequestApiResponseDto openRequest(UserRequestApiRequestDto userRequest);

    void requestCompleted(MessageFromPayProc userRequestCompletionNotification);
}
