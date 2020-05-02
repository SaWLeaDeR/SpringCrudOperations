package com.mkoyuncuoglu.security.services.domain.service;

import com.mkoyuncuoglu.security.services.domain.model.GuestModel;
import com.mkoyuncuoglu.security.services.domain.model.dto.Guest;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface GuestService {

    List<Guest> getAllGuests();

    ResponseEntity<Guest> addGuest(GuestModel model);

    Guest getGuest(Long id);

    void updateGuest(Long id, Guest guest);

    void deleteGuest(Long id);

}
