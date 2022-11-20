package org.cards.cardsinfo.repository;

import org.cards.cardsinfo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AccountRepository extends JpaRepository<Account, Integer> {
}