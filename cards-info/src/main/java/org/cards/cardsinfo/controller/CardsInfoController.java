package org.cards.cardsinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.cards.cardsinfo.model.Account;
import org.cards.cardsinfo.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/")
@Validated
@Tag(name = "CARDS-INFO", description = "Cards Information API - Basic Info for a card's account")
public class CardsInfoController {

    private final AccountRepository repository;

    public CardsInfoController(AccountRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Credit Card Account Details", description = "Lookup Credit Card Account Information, given the Card Account ID", tags = {"CARDS-INFO"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Card Account ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Credit Card Account Details Not Found")
    })
    @GetMapping(value = "/cards/{id}/info", produces = MediaType.APPLICATION_JSON_VALUE)
    Account findAccountById(
            @Parameter(description = "Card Account ID", required = true)
            @Digits(integer = 10, fraction = 0, message = "Card Account ID must be numeric, with up to 10 digits")
            @Min(value = 1, message = "Card Account ID must be numeric, with Minimum value 1")
            @PathVariable("id")
            String id) {
        var numId = Integer.parseInt(id);
        return repository.findById(numId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Card Account ID for id:" + id));
    }

    @GetMapping(value = "/cards", produces = MediaType.APPLICATION_JSON_VALUE)
    Account findAccountByIdMisingId() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card Account ID missing");
    }


}
