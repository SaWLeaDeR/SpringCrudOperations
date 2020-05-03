package com.mkoyuncuoglu.security.services.domain.service.impl;

import com.mkoyuncuoglu.security.services.domain.model.GuestModel;
import com.mkoyuncuoglu.security.services.domain.model.dto.Guest;
import com.mkoyuncuoglu.security.services.domain.repository.GuestRepository;
import com.mkoyuncuoglu.security.services.domain.service.GuestService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository repository;

    public GuestServiceImpl(GuestRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Guest> getAllGuests() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<Guest> addGuest(GuestModel model) {
        Guest guest = this.repository.save(model.translateModelToGuest());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(guest.getId()).toUri();
        return ResponseEntity.created(location).body(guest);
    }

    @Override
    public Guest getGuest(Long id) {
        return !repository.findById(id).isPresent() ? null : repository.findById(id).get();
    }

    @Override
    public void updateGuest(Long id, @Valid Guest guest) {
        guest.setId(id);
        repository.save(guest);
    }

    @Override
    public void deleteGuest(Long id) {
        repository.delete(!repository.findById(id).isPresent() ? null : repository.findById(id).get());
    }
}
