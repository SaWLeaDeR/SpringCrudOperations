package com.mkoyuncuoglu.security.services.domain.repository;

import com.mkoyuncuoglu.security.services.domain.model.dto.AdminUser;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<AdminUser, Long> {

    @Override
    List<AdminUser> findAll();

}
