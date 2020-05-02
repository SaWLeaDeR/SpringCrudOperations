package com.mkoyuncuoglu.security.app.domain.service;

import com.mkoyuncuoglu.security.app.domain.model.dto.AdminUser;
import com.mkoyuncuoglu.security.app.delegate.model.AdminModel;

import java.util.List;

public interface AdminUserService {
    List<AdminUser> getAllAdminUser();

    AdminUser addAdminUser(AdminModel adminModel, String hashedVal);

    AdminUser getAdminUser(String id);

    AdminUser updateAdminUser(String id, AdminModel adminModel, String hashedVal);

    void deleteAdminUser(String id);
}
