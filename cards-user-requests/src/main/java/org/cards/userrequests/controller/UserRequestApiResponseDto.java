package org.cards.userrequests.controller;

import lombok.Data;
import org.cards.userrequests.model.UserRequestType;

import java.time.LocalDateTime;

@Data
public class UserRequestApiResponseDto {
    private Long requestId;
    private UserRequestType requestType;
    private LocalDateTime submitted;
}
