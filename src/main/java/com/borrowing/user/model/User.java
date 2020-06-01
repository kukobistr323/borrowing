package com.borrowing.user.model;

import com.borrowing.debt.model.Debt;
import com.borrowing.item.model.Item;
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
import javax.persistence.JoinColumn;
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
@ToString(exclude = {"items", "creditorDebts", "debtorDebts"})
@EqualsAndHashCode(exclude = {"items", "creditorDebts", "debtorDebts"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    private String login;

    private String password;

    private Boolean admin;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private List<Item> items;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "creditor_id")
    private List<Debt> creditorDebts;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "debtor_id")
    private List<Debt> debtorDebts;
}
