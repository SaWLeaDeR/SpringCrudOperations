package com.mkoyuncuoglu.security.services.domain.service;

import com.mkoyuncuoglu.security.services.domain.model.AdminModel;
import com.mkoyuncuoglu.security.services.domain.model.dto.AdminUser;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    List<AdminUser> getAllAdminUser();

    ResponseEntity<AdminUser> addAdminUser(AdminModel model);

    AdminUser getAdminUser(Long id);

    void updateAdminUser(Long id, AdminUser adminUser);

    void deleteAdminUser(Long id);

}
