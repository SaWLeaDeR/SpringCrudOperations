package com.mkoyuncuoglu.security.app.delegate.service;

import com.mkoyuncuoglu.security.app.delegate.model.GuestModel;
import com.mkoyuncuoglu.security.app.domain.model.dto.Guest;
import java.util.List;

public interface GuestDelegateService {

    List<Guest> getAllGuests();

    Guest addGuest(GuestModel guestModel);

    Guest getGuest(Long id);

    Guest updateGuest(Long id, GuestModel guestModel);

    void deleteGuest(Long id);
}
