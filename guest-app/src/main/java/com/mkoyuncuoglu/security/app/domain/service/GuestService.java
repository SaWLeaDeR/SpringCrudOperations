package com.mkoyuncuoglu.security.app.domain.service;

import com.mkoyuncuoglu.security.app.domain.model.dto.Guest;
import com.mkoyuncuoglu.security.app.delegate.model.GuestModel;
import org.bson.types.ObjectId;

import java.util.List;

public interface GuestService {
    List<Guest> getAllGuests();

    Guest addGuest(GuestModel guestModel, String hashedVal);

    Guest getGuest(ObjectId id);

    Guest updateGuest(ObjectId id, GuestModel guestModel,String hashedVal);

    void deleteGuest(ObjectId id);
}
