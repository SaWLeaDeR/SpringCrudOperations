package com.mkoyuncuoglu.security.services.domain.controller;

import com.mkoyuncuoglu.security.services.domain.model.GuestModel;
import com.mkoyuncuoglu.security.services.domain.model.dto.Guest;
import com.mkoyuncuoglu.security.services.domain.service.GuestService;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * fpmoles password      jdoe foobar
 */
@RestController
@RequestMapping("/guests")
public class GuestServicesController {

    private final GuestService service;

    public GuestServicesController(GuestService service) {
        super();
        this.service = service;
    }

    @GetMapping
    public List<Guest> getAllGuests() {
        return service.getAllGuests();
    }

    @PostMapping
    public ResponseEntity<Guest> addGuest(@RequestBody GuestModel model) {
        return service.addGuest(model);
    }

    @GetMapping("/{id}")
    public Guest getGuest(@PathVariable("id") Long id) {
        return service.getGuest(id);
    }

    @PutMapping("/{id}")
    public void updateGuest(@PathVariable("id") Long id, @Valid @RequestBody Guest guest) {
        service.updateGuest(id, guest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteGuest(@PathVariable Long id) {
        service.deleteGuest(id);
    }
}
