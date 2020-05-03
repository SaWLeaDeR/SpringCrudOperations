package com.mkoyuncuoglu.security.app.domain.service;

import com.mkoyuncuoglu.security.app.domain.model.dto.AdminUser;
import com.mkoyuncuoglu.security.app.domain.model.dto.Guest;
import java.util.List;

public interface PersonService {

    void create(AdminUser p);

    void create(Guest p);

    List<AdminUser> findAll();

    AdminUser findOne(String uid);

    List<AdminUser> findByName(String name);

    void update(AdminUser p);

    void updateLastName(AdminUser p);

    void delete(AdminUser p);

    void delete(Guest p);

}
