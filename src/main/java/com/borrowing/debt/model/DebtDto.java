package com.borrowing.debt.model;

import com.borrowing.status.model.StatusName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author Vlad Kukoba
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DebtDto {

    private Long id;

    private String itemName;

    private String creditorLogin;

    private String debtorLogin;

    private LocalDate returnDate;

    private StatusName statusName;
}
