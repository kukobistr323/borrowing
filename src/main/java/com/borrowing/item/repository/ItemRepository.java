package com.borrowing.item.repository;

import com.borrowing.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Vlad Kukoba
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> getAllByOwner_Login(String login);

    List<Item> getAllByBorrowedTrueAndAndOwner_Login(String login);

    List<Item> getAllByOwner_Id(Long id);
}
