package org.cards.userrequests.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.userrequests.controller.UserRequestApiRequestDto;
import org.cards.userrequests.controller.UserRequestApiResponseDto;
import org.cards.userrequests.mq.MessageFromPayProc;
import org.cards.userrequests.mq.UserRequestMqSend;
import org.cards.userrequests.mq.PayProcMessageSender;
import org.cards.userrequests.persistence.AccountRepository;
import org.cards.userrequests.persistence.AccountStatus;
import org.cards.userrequests.persistence.UserRequestEntity;
import org.cards.userrequests.persistence.UserRequestRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Slf4j
@AllArgsConstructor
@Service
public class UserRequestsServiceImpl implements UserRequestsService {

    private final AccountRepository accountRepository;
    private final UserRequestRepository userRequestRepository;

    private final PayProcMessageSender mqSender;

    private final DtoMapper mapper;


    @Transactional
    @Override
    public UserRequestApiResponseDto openRequest(UserRequestApiRequestDto userRequestDto) {

        var account = accountRepository.findById(userRequestDto.getAccountId())
                .orElseThrow(()->new NotFoundException("No account with ID:"+userRequestDto.getAccountId()));
        account.setStatus(AccountStatus.SUSPENDED);

        var userRequestEntity = new UserRequestEntity();
        userRequestEntity.setAccount(account);
        userRequestEntity.setSubmitted(LocalDateTime.now());
        userRequestEntity.setRequestType(userRequestDto.getRequestType());
        userRequestRepository.save(userRequestEntity);

        var toPayProcMessageDto = new UserRequestMqSend();
        toPayProcMessageDto.setPan(account.getPan());
        toPayProcMessageDto.setRequestId(userRequestEntity.getId());
        toPayProcMessageDto.setUserRequestType(userRequestEntity.getRequestType());
        toPayProcMessageDto.setRequestTime(userRequestEntity.getSubmitted());
        mqSender.sendMessage(toPayProcMessageDto);

        return mapper.map(toPayProcMessageDto);
    }

    @Override
    public void requestCompleted(MessageFromPayProc userRequestCompletionNotification) {

    }


}
