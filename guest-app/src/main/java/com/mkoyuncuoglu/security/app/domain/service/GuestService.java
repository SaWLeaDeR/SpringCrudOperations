package com.mkoyuncuoglu.security.app.domain.service;

import com.mkoyuncuoglu.security.app.delegate.model.GuestModel;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface GuestService {

    String getGuests(Model model);

    ModelAndView addGuest(HttpServletRequest request, Model model,
        GuestModel guestModel);

    String getGuest(Model model, Long id);

    String updateGuest(Model model, Long id, GuestModel guestModel);

    String deleteGuest(Long id);

}
