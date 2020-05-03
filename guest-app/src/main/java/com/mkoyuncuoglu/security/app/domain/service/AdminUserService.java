package com.mkoyuncuoglu.security.app.domain.service;

import com.mkoyuncuoglu.security.app.delegate.model.AdminModel;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

public interface AdminUserService {

    String getAdminUsers(Model model);

    ModelAndView addAdminUser(HttpServletRequest request, Model model,
        @ModelAttribute AdminModel adminModel);

    String getAdminUser(Model model, Long id);

    String updateAdminUser(Model model, Long id, AdminModel adminModel);

    String deleteAdminUser(Long id);
}
