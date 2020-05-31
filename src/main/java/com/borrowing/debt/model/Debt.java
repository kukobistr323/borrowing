package com.borrowing.debt.model;

import com.borrowing.item.model.Item;
import com.borrowing.status.model.Status;
import com.borrowing.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * @author Vlad Kukoba
 */
@Entity
@Getter
@Setter
@Builder
@ToString(exclude = {"status", "creditor", "debtor", "item"})
@EqualsAndHashCode(exclude = {"status", "creditor", "debtor", "item"})
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    private User creditor;

    @ManyToOne(fetch = FetchType.EAGER)
    private User debtor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Item item;
}
