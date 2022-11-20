package org.cards.cardsinfo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Table(name = "accounts")
public class Account {
    @Id
    @Column(name = "account_id", nullable = false)
    private Integer id;

    @Column(name = "cust_name", nullable = false)
    private String custName;

    @Column(name = "pan", nullable = false, length = 16)
    private String pan;

    @Column(name = "expires", nullable = false)
    private LocalDate expires;

    @Column(name = "balance", nullable = false, precision = 8, scale = 2)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "sts", nullable = false)
    private AccountStatus status;
}