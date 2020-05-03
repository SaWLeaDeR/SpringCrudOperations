package com.mkoyuncuoglu.security.services.domain.repository;

import com.mkoyuncuoglu.security.services.domain.model.dto.Guest;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {

    @Override
    List<Guest> findAll();
}