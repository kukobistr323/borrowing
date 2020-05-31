package com.borrowing.item.web;

import com.borrowing.item.model.Item;
import com.borrowing.item.model.ItemDto;
import com.borrowing.item.service.ItemService;
import com.borrowing.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * @author Vlad Kukoba
 */
@RestController
@RequestMapping(ItemController.PATH_MAIN)
public class ItemController {

    public static final String PATH_MAIN = "/items";
    public static final String PATH_ALL = "/all";
    public static final String PATH_BORROWED = "/borrowed";
    public static final String PATH_GET_BY_ID = "/{id}";

    private final UserService userService;
    private final ItemService itemService;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemController(ItemService itemService, ModelMapper modelMapper, UserService userService) {
        this.itemService = itemService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<ItemDto> getAllItemsByOwnerLogin(Principal principal) {
        return modelMapper.map(itemService.getAllItemsByOwnerLogin(principal.getName()), new TypeToken<List<ItemDto>>() {
        }.getType());
    }

    @GetMapping(value = PATH_GET_BY_ID)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<ItemDto> getAllItemsByOwnerId(@PathVariable Long id) {
        return modelMapper.map(itemService.getAllItemsByOwnerId(id), new TypeToken<List<ItemDto>>() {
        }.getType());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = PATH_BORROWED)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<ItemDto> getAllBorrowedItemsByOwnerLogin(Principal principal) {
        return modelMapper.map(itemService.getBorrowedItemByOwnerLogin(principal.getName()), new TypeToken<List<ItemDto>>() {
        }.getType());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = PATH_ALL)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<ItemDto> getAllItems() {
        return modelMapper.map(itemService.getAllItems(), new TypeToken<List<ItemDto>>() {
        }.getType());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ItemDto createItem(@RequestBody ItemDto itemDto, Principal principal) {
        Item item = modelMapper.map(itemDto, Item.class);
        item.setOwner(userService.loadUserByLogin(principal.getName()));
        return modelMapper.map(itemService.saveItem(item), ItemDto.class);
    }
}
