package com.mkoyuncuoglu.security.app.domain.service.impl;

import com.mkoyuncuoglu.security.app.delegate.model.GuestModel;
import com.mkoyuncuoglu.security.app.delegate.service.GuestDelegateService;
import com.mkoyuncuoglu.security.app.domain.crypto.PasswordEncode;
import com.mkoyuncuoglu.security.app.domain.model.dto.Guest;
import com.mkoyuncuoglu.security.app.domain.service.GroupService;
import com.mkoyuncuoglu.security.app.domain.service.GuestService;
import com.mkoyuncuoglu.security.app.domain.service.PersonService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@Service
public class GuestServiceImpl implements GuestService {

    private static final String GUEST_VIEW = "guest-view";
    private static final String GUEST = "guest";

    private final GroupService groupService;
    private final PersonService personService;
    private final GuestDelegateService guestDelegateService;
    private final PasswordEncode encoder;

    public GuestServiceImpl(GroupService groupService, PersonService personService, GuestDelegateService guestDelegateService,
        PasswordEncode encoder) {
        this.groupService = groupService;
        this.personService = personService;
        this.guestDelegateService = guestDelegateService;
        this.encoder = encoder;
    }

    @Override
    public String getGuests(Model model) {
        List<Guest> guests = guestDelegateService.getAllGuests();
        model.addAttribute("guests", guests);
        return "guests-view";
    }

    @Override
    public ModelAndView addGuest(HttpServletRequest request, Model model, GuestModel guestModel) {
        String encryptedPass = encoder.encode(guestModel.getPassword());
        guestModel.setPassword(encryptedPass);

        Guest guest = guestDelegateService.addGuest(guestModel);
        Guest guestUser = guestModel.translateModelToGuest(encryptedPass);
        personService.create(guestUser);
        groupService.addMemberToGroup("user", guestUser);

        model.addAttribute(GUEST, guest);
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        return new ModelAndView("redirect:/guests/" + guest.getId());
    }

    @Override
    public String getGuest(Model model, Long id) {
        Guest guest = guestDelegateService.getGuest(id);
        model.addAttribute(GUEST, guest);
        return GUEST_VIEW;
    }

    @Override
    public String updateGuest(Model model, Long id, GuestModel guestModel) {
        String encryptedPass = encoder.encode(guestModel.getPassword());
        guestModel.setPassword(encryptedPass);

        personService.delete(guestModel.translateModelToGuest(encryptedPass));
        groupService.removeMemberFromGroup("user", guestModel.translateModelToGuest(encryptedPass));

        personService.create(guestModel.translateModelToGuest(encryptedPass));
        groupService.addMemberToGroup("user", guestModel.translateModelToGuest(encryptedPass));

        guestDelegateService.updateGuest(id, guestModel);

        model.addAttribute(GUEST, null);
        model.addAttribute("guestModel", new GuestModel());
        return GUEST_VIEW;
    }

    public String deleteGuest(Long id) {
        guestDelegateService.deleteGuest(id);
        return "guests-view";
    }
}
