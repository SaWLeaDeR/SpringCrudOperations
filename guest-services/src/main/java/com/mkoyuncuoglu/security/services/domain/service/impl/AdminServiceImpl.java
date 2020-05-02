package com.mkoyuncuoglu.security.services.domain.service.impl;

import com.mkoyuncuoglu.security.services.domain.model.AdminModel;
import com.mkoyuncuoglu.security.services.domain.model.dto.AdminUser;
import com.mkoyuncuoglu.security.services.domain.repository.AdminRepository;
import com.mkoyuncuoglu.security.services.domain.service.AdminService;
import java.net.URI;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

    private final AdminRepository repository;

    public AdminServiceImpl(AdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AdminUser> getAllAdminUser() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<AdminUser> addAdminUser(AdminModel model) {
        AdminUser adminUser = repository.save(model.translateModelToAdminUser());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(adminUser.getId()).toUri();
        return ResponseEntity.created(location).body(adminUser);
    }

    @Override
    public AdminUser getAdminUser(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void updateAdminUser(Long id, AdminUser adminUser) {
        adminUser.setId(id);
        repository.save(adminUser);
    }

    @Override
    public void deleteAdminUser(Long id) {
        repository.delete(!repository.findById(id).isPresent() ? null : repository.findById(id).get());
    }
}
