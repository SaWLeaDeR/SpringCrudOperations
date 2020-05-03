package com.mkoyuncuoglu.security.app.domain.service.impl;


import com.mkoyuncuoglu.security.app.delegate.model.AdminModel;
import com.mkoyuncuoglu.security.app.delegate.service.AdminUserDelegateService;
import com.mkoyuncuoglu.security.app.domain.crypto.PasswordEncode;
import com.mkoyuncuoglu.security.app.domain.model.dto.AdminUser;
import com.mkoyuncuoglu.security.app.domain.service.AdminUserService;
import com.mkoyuncuoglu.security.app.domain.service.GroupService;
import com.mkoyuncuoglu.security.app.domain.service.PersonService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    private static final String ADMIN_VIEW = "admin-view";
    private static final String ADMINS_VIEW = "admins-view";
    private static final String ADMIN = "admin";
    private static final String USER = "user";

    private final PersonService personService;
    private final GroupService groupService;
    private final AdminUserDelegateService adminUserDelegateService;
    private final PasswordEncode encoder;

    public AdminUserServiceImpl(PersonService personService, GroupService groupService, AdminUserDelegateService adminUserDelegateService,
        PasswordEncode encoder) {
        this.personService = personService;
        this.groupService = groupService;
        this.adminUserDelegateService = adminUserDelegateService;
        this.encoder = encoder;
    }

    @Override
    public String getAdminUsers(Model model) {
        List<AdminUser> admins = adminUserDelegateService.getAllAdminUser();
        model.addAttribute("admins", admins);
        return ADMINS_VIEW;
    }

    @Override
    public ModelAndView addAdminUser(HttpServletRequest request, Model model, AdminModel adminModel) {
        String encryptedPass = encoder.encode(adminModel.getUserPassword());
        adminModel.setUserPassword(encryptedPass);

        AdminUser admin = adminModel.translateModelToAdminUser(encryptedPass);
        personService.create(admin);
        applyAdminGroup(admin);
        model.addAttribute(ADMIN, admin);
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        return new ModelAndView("redirect:/admins/" + admin.getId());
    }

    @Override
    public String getAdminUser(Model model, Long id) {
        AdminUser adminUser = adminUserDelegateService.getAdminUser(id);
        model.addAttribute(ADMIN, adminUser);
        return ADMIN_VIEW;
    }

    @Override
    public String updateAdminUser(Model model, Long id, AdminModel adminModel) {
        String encryptedPass = encoder.encode(adminModel.getUserPassword());
        adminModel.setUserPassword(encryptedPass);

        personService.delete(adminModel.translateModelToAdminUser(encryptedPass));
        groupService.removeMemberFromGroup(ADMIN, adminModel.translateModelToAdminUser(encryptedPass));
        groupService.removeMemberFromGroup(USER, adminModel.translateModelToAdminUser(encryptedPass));
        personService.create(adminModel.translateModelToAdminUser(encryptedPass));

        applyAdminGroup(adminModel.translateModelToAdminUser(encryptedPass));

        adminUserDelegateService.updateAdminUser(id, adminModel);

        model.addAttribute(ADMIN, null);
        model.addAttribute("adminModel", new AdminModel());
        return ADMIN_VIEW;
    }

    @Override
    public String deleteAdminUser(Long id) {
        adminUserDelegateService.deleteAdminUser(id);
        return ADMINS_VIEW;
    }

    private void applyAdminGroup(AdminUser adminuser) {
        groupService.addMemberToGroup(ADMIN, adminuser);
        groupService.addMemberToGroup(USER, adminuser);
    }
}
