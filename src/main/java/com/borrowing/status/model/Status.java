package com.borrowing.status.model;

import com.borrowing.debt.model.Debt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@ToString(exclude = "debts")
@EqualsAndHashCode(exclude = "debts")
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusName name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "debt_id")
    private List<Debt> debts;
}
