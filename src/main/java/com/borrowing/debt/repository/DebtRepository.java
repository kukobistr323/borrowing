package com.borrowing.debt.repository;

import com.borrowing.debt.model.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Vlad Kukoba
 */
@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {

    List<Debt> findAllByCreditor_Login(String login);

    List<Debt> findAllByDebtor_Login(String login);
}
