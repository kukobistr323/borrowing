package com.borrowing.status.service;

import com.borrowing.status.model.Status;
import com.borrowing.status.model.StatusName;
import com.borrowing.status.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * @author Vlad Kukoba
 */
@Service
public class StatusService {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Status findStatusByName(StatusName statusName) {
        return statusRepository.findStatusByName(statusName)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Status with name %s not found", statusName)));
    }
}
