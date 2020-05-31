package com.borrowing.item.model;

import com.borrowing.debt.model.Debt;
import com.borrowing.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author Vlad Kukoba
 */
@Entity
@Getter
@Setter
@Builder
@ToString(exclude = {"owner", "debts"})
@EqualsAndHashCode(exclude = {"owner", "debts"})
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean borrowed;

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private List<Debt> debts;
}
