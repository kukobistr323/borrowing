package com.borrowing.debt.web;

import com.borrowing.core.exception.NotValidPrincipal;
import com.borrowing.debt.model.Debt;
import com.borrowing.debt.model.DebtCreate;
import com.borrowing.debt.model.DebtDto;
import com.borrowing.debt.model.DebtUpdate;
import com.borrowing.debt.service.DebtService;
import com.borrowing.item.model.Item;
import com.borrowing.item.service.ItemService;
import com.borrowing.user.model.User;
import com.borrowing.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping(DebtController.PATH_MAIN)
public class DebtController {

    public static final String PATH_MAIN = "/debts";
    public static final String PATH_ALL = "/all";
    public static final String PATH_INCOMING = "/incoming";
    public static final String PATH_OUTGOING = "/outgoing";

    private final UserService userService;
    private final DebtService debtService;
    private final ItemService itemService;
    private final ModelMapper modelMapper;

    @Autowired
    public DebtController(UserService userService, DebtService debtService, ItemService itemService, ModelMapper modelMapper) {
        this.userService = userService;
        this.debtService = debtService;
        this.itemService = itemService;
        this.modelMapper = modelMapper;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody DebtDto createDebt(@RequestBody DebtCreate debtCreate, Principal principal) {
        User debtor = userService.loadUserByLogin(principal.getName());
        Item item = itemService.findItemById(debtCreate.getItemId());
        User creditor = item.getOwner();

        if (creditor.getLogin().equals(debtor.getLogin())) {
            throw new NotValidPrincipal("Wrong Principal");
        }
        return modelMapper.map(debtService.createDebt(debtor, creditor, item, debtCreate.getReturnDate()), DebtDto.class);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = PATH_ALL)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<DebtDto> getAllDebts() {
        return modelMapper.map(debtService.getAllDebts(), new TypeToken<List<DebtDto>>() {
        }.getType());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = PATH_INCOMING)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<DebtDto> getAllDebtsByCreditorLogin(Principal principal) {
        return modelMapper.map(debtService.getAllDebtsByCreditorLogin(principal.getName()), new TypeToken<List<DebtDto>>() {
        }.getType());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = PATH_OUTGOING)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<DebtDto> getAllDebtsByDebtorLogin(Principal principal) {
        return modelMapper.map(debtService.getAllDebtsByDebtorLogin(principal.getName()), new TypeToken<List<DebtDto>>() {
        }.getType());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody DebtDto updateDebtStatus(@RequestBody DebtUpdate debtUpdate, Principal principal) {
        Debt debt = debtService.findDebtById(debtUpdate.getId());
        if (!debt.getCreditor().getLogin().equals(principal.getName())) {
            throw new NotValidPrincipal("Wrong Principal");
        }
        return modelMapper.map(debtService.updateDebtStatus(debtUpdate.getId(), debtUpdate.getStatusName(), debt.getItem()), DebtDto.class);
    }
}
