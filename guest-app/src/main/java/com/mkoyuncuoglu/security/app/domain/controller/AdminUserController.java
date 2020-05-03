package com.mkoyuncuoglu.security.app.domain.controller;

import com.mkoyuncuoglu.security.app.delegate.model.AdminModel;
import com.mkoyuncuoglu.security.app.domain.service.AdminUserService;
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
public class AdminUserController {

    private static final String ADMIN_VIEW = "admin-view";
    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping(value = "/admins")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getAdminUsers(Model model) {
        return adminUserService.getAdminUsers(model);
    }

    @GetMapping(value = "/admins/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddGuestForm(Model model) {
        return ADMIN_VIEW;
    }

    @PostMapping(value = "/admins")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addAdminUser(HttpServletRequest request, Model model,
        @ModelAttribute AdminModel adminModel) {
        return adminUserService.addAdminUser(request, model, adminModel);
    }

    @GetMapping(value = "/admins/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getAdminUser(Model model, @PathVariable Long id) {
        return adminUserService.getAdminUser(model, id);
    }

    @PostMapping(value = "/admins/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateAdminUser(Model model, @PathVariable Long id, @ModelAttribute AdminModel adminModel) {
        return adminUserService.updateAdminUser(model, id, adminModel);
    }

    @DeleteMapping(value = "/admins/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteAdminUser(@PathVariable Long id) {
        return adminUserService.deleteAdminUser(id);
    }
}
