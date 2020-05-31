package com.borrowing.debt.service;

import com.borrowing.debt.model.Debt;
import com.borrowing.debt.repository.DebtRepository;
import com.borrowing.item.model.Item;
import com.borrowing.item.service.ItemService;
import com.borrowing.status.model.StatusName;
import com.borrowing.status.service.StatusService;
import com.borrowing.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Vlad Kukoba
 */
@Service
public class DebtService {

    private final DebtRepository debtRepository;
    private final StatusService statusService;
    private final ItemService itemService;

    @Autowired
    public DebtService(DebtRepository debtRepository, StatusService statusService, ItemService itemService) {
        this.debtRepository = debtRepository;
        this.statusService = statusService;
        this.itemService = itemService;
    }

    public Debt findDebtById(Long debtId) {
        return debtRepository.findById(debtId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Debt with id %d not found", debtId)));
    }

    public List<Debt> getAllDebts() {
        return debtRepository.findAll();
    }

    public List<Debt> getAllDebtsByCreditorLogin(String login) {
        return debtRepository.findAllByCreditor_Login(login);
    }

    public List<Debt> getAllDebtsByDebtorLogin(String login) {
        return debtRepository.findAllByDebtor_Login(login);
    }

    @Transactional
    public Debt updateDebtStatus(Long debtId, StatusName statusName, Item item) {
        Debt debt = debtRepository.findById(debtId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Debt with id %d not found", debtId)));

        switch (statusName) {
            case ACCEPTED:
                item.setBorrowed(true);
                break;
            case RETURNED:
                item.setBorrowed(false);
                break;
        }
        itemService.saveItem(item);
        debt.setStatus(statusService.findStatusByName(statusName));
        return debtRepository.save(debt);
    }

    @Transactional
    public Debt createDebt(User debtor, User creditor, Item item, LocalDate returnDate) {
        return debtRepository.save(Debt.builder()
                .debtor(debtor)
                .creditor(creditor)
                .item(item)
                .returnDate(returnDate)
                .status(statusService.findStatusByName(StatusName.PENDING))
                .build());
    }
}
