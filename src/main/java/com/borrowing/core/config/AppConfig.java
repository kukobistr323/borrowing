package com.borrowing.core.config;

import com.borrowing.debt.model.Debt;
import com.borrowing.debt.model.DebtDto;
import com.borrowing.item.model.Item;
import com.borrowing.item.model.ItemDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Vlad Kukoba
 */
@Component
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        //debt to debtDto
        TypeMap<Debt, DebtDto> debtDebtDtoTypeMap = modelMapper.createTypeMap(Debt.class, DebtDto.class);
        debtDebtDtoTypeMap.addMapping(src -> src.getCreditor().getLogin(), DebtDto::setCreditorLogin);
        debtDebtDtoTypeMap.addMapping(src -> src.getDebtor().getLogin(), DebtDto::setDebtorLogin);
        debtDebtDtoTypeMap.addMapping(src -> src.getItem().getName(), DebtDto::setItemName);
        debtDebtDtoTypeMap.addMapping(src -> src.getStatus().getName(), DebtDto::setStatusName);

        //item to itemDto
        TypeMap<Item, ItemDto> itemItemDtoTypeMap = modelMapper.createTypeMap(Item.class, ItemDto.class);
        itemItemDtoTypeMap.addMapping(src -> src.getOwner().getLogin(), ItemDto::setOwnerLogin);

        return modelMapper;
    }
}
