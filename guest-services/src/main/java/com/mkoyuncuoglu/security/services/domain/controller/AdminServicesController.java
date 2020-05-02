package com.mkoyuncuoglu.security.services.domain.controller;

import com.mkoyuncuoglu.security.services.domain.model.AdminModel;
import com.mkoyuncuoglu.security.services.domain.model.dto.AdminUser;
import com.mkoyuncuoglu.security.services.domain.service.AdminService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
public class AdminServicesController {

    private final AdminService service;

    public AdminServicesController(AdminService service) {
        super();
        this.service = service;
    }

    @GetMapping
    public List<AdminUser> getAllAdminUser() {
        return service.getAllAdminUser();
    }

    @PostMapping
    public ResponseEntity<AdminUser> addAdminUser(@RequestBody AdminModel model) {
        return service.addAdminUser(model);
    }

    @GetMapping("/{id}")
    public AdminUser getAdminUser(@PathVariable("id") Long id) {
        return service.getAdminUser(id);
    }

    @PutMapping("/{id}")
    public void updateAdminUser(@PathVariable("id") Long id, @Valid @RequestBody AdminUser adminUser) {
        service.updateAdminUser(id, adminUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteAdminUser(@PathVariable Long id) {
        service.deleteAdminUser(id);
    }
}
