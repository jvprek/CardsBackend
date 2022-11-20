package org.cards.userrequests.controller;

import lombok.Data;
import org.cards.userrequests.model.UserRequestType;

import javax.validation.constraints.NotNull;

@Data
public class UserRequestApiRequestDto {
    @NotNull
    private Long accountId;
    @NotNull
    private UserRequestType requestType;
}
