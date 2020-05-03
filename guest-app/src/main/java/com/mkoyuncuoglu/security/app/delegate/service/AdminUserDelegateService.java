package com.mkoyuncuoglu.security.app.delegate.service;

import com.mkoyuncuoglu.security.app.delegate.model.AdminModel;
import com.mkoyuncuoglu.security.app.domain.model.dto.AdminUser;
import java.util.List;

public interface AdminUserDelegateService {

    List<AdminUser> getAllAdminUser();

    AdminUser addAdminUser(AdminModel adminModel);

    AdminUser getAdminUser(Long id);

    AdminUser updateAdminUser(Long id, AdminModel adminModel);

    void deleteAdminUser(Long id);

}
