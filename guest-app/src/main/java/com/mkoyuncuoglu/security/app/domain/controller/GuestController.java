package com.mkoyuncuoglu.security.app.domain.controller;

import com.mkoyuncuoglu.security.app.delegate.model.GuestModel;
import com.mkoyuncuoglu.security.app.domain.service.GuestService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class GuestController {

    private static final String GUEST_VIEW = "guest-view";

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        super();
        this.guestService = guestService;
    }

    @GetMapping(value = {"/", "/index"})
    public String getHomePage(Model model) {
        return "index";
    }

    @GetMapping(value = "/login")
    public String getLoginPage(Model model) {
        return "login";
    }

    @GetMapping(value = "/logout-success")
    public String getLogoutPage(Model model) {
        return "logout";
    }

    @GetMapping(value = "/guests")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getGuests(Model model) {
        return guestService.getGuests(model);
    }

    @GetMapping(value = "/guests/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddGuestForm(Model model) {
        return GUEST_VIEW;
    }

    @PostMapping(value = "/guests")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addGuest(HttpServletRequest request, Model model,
        @ModelAttribute GuestModel guestModel) {
        return guestService.addGuest(request, model, guestModel);
    }

    @GetMapping(value = "/guests/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getGuest(Model model, @PathVariable Long id) {
        return guestService.getGuest(model, id);
    }

    @PostMapping(value = "/guests/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateGuest(Model model, @PathVariable Long id, @ModelAttribute GuestModel guestModel) {
        return guestService.updateGuest(model, id, guestModel);
    }

    @DeleteMapping(value = "/guests/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteGuest(@PathVariable Long id) {
        return guestService.deleteGuest(id);
    }
}
