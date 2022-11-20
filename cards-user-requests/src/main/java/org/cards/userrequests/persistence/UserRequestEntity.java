package org.cards.userrequests.persistence;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.cards.userrequests.model.UserRequestType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "user_requests")
public class UserRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "account_id")
    private Account account;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "request_type", nullable = false)
    private UserRequestType requestType;

    @NotNull
    @Column(name = "submitted", nullable = false)
    private LocalDateTime submitted;

    @Column(name = "completed")
    private LocalDateTime completed;

}