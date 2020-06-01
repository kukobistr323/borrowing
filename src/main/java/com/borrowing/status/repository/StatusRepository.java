package com.borrowing.status.repository;

import com.borrowing.status.model.Status;
import com.borrowing.status.model.StatusName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Vlad Kukoba
 */
@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    Optional<Status> findStatusByName(StatusName statusName);
}
