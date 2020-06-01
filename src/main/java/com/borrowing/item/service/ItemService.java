package com.borrowing.item.service;

import com.borrowing.item.model.Item;
import com.borrowing.item.repository.ItemRepository;
import com.borrowing.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author Vlad Kukoba
 */
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item saveItemByName(String itemName, User user) {
        return itemRepository.save(Item.builder()
                .borrowed(false)
                .name(itemName)
                .owner(user)
                .build());
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getBorrowedItemByOwnerLogin(String login) {
        return itemRepository.getAllByBorrowedTrueAndAndOwner_Login(login);
    }

    public List<Item> getAllItemsByOwnerId(Long id) {
        return itemRepository.getAllByOwner_Id(id);
    }

    public List<Item> getAllItemsByOwnerLogin(String login) {
        return itemRepository.getAllByOwner_Login(login);
    }

    public Item findItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Item with id %d not found", itemId)));
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
