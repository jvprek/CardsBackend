package org.cards.userrequests.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.userrequests.persistence.Account;
import org.cards.userrequests.persistence.AccountRepository;
import org.cards.userrequests.service.UserRequestsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/")
@Validated
@Tag(name = "CARDS-USER-REQUESTS", description = """
        Cards User Requests API
        Initiate Flows for Lost & Stollen (LS), Replace Card (RC) User Requests
        """)
@Slf4j
public class ApiController {

    private final UserRequestsService userRequestsService;

    public ApiController(UserRequestsService userRequestsService) {
        this.userRequestsService = userRequestsService;
    }

    @Operation(summary = "Submit User Request", description = "Lookup Credit Card Account Information, given the Card Account ID", tags = {"CARDS-USER_REQUESTS"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserRequestApiResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Payload"),
            @ApiResponse(responseCode = "404", description = "Credit Card Account Details Not Found")
    })
    @PostMapping(value = "/cards/user-requests", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    UserRequestApiResponseDto submitRequest(@Valid @RequestBody UserRequestApiRequestDto request) {
        return userRequestsService.openRequest(request);

    }





}
