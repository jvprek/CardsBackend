package org.cards.userrequests.service;
import org.cards.userrequests.controller.UserRequestApiResponseDto;
import org.cards.userrequests.mq.UserRequestMqSend;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DtoMapper {

    @Mapping(source = "requestTime", target = "submitted")
    @Mapping(source = "userRequestType", target = "requestType")
    UserRequestApiResponseDto map(UserRequestMqSend jmsMessage);
}
