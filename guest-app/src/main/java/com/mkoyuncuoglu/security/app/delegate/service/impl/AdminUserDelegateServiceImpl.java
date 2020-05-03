package com.mkoyuncuoglu.security.app.delegate.service.impl;

import com.mkoyuncuoglu.security.app.delegate.model.AdminModel;
import com.mkoyuncuoglu.security.app.delegate.service.AdminUserDelegateService;
import com.mkoyuncuoglu.security.app.domain.model.dto.AdminUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminUserDelegateServiceImpl implements AdminUserDelegateService {

    private static final String ADMINS = "/admins";
    private static final String SLASH = "/";

    private final RestTemplate restTemplate;
    @Value("${landon.guest.service.url}")
    private String adminServiceUrl;

    public AdminUserDelegateServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<AdminUser> getAllAdminUser() {
        String url = adminServiceUrl + ADMINS;
        HttpEntity<String> request = new HttpEntity<>(null, null);
        return this.restTemplate.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<List<AdminUser>>() {
        }).getBody();
    }

    @Override
    public AdminUser addAdminUser(AdminModel adminModel) {
        String url = adminServiceUrl + ADMINS;
        HttpEntity<AdminModel> request = new HttpEntity<>(adminModel, null);
        return this.restTemplate.exchange(url, HttpMethod.POST, request, AdminUser.class).getBody();
    }

    @Override
    public AdminUser getAdminUser(Long id) {
        String url = adminServiceUrl + ADMINS + SLASH + id;
        HttpEntity<String> request = new HttpEntity<>(null, null);
        return this.restTemplate.exchange(url, HttpMethod.GET, request, AdminUser.class).getBody();
    }

    @Override
    public AdminUser updateAdminUser(Long id, AdminModel adminModel) {
        String url = adminServiceUrl + ADMINS + SLASH + id;
        HttpEntity<AdminModel> request = new HttpEntity<>(adminModel, null);
        return this.restTemplate.exchange(url, HttpMethod.PUT, request, AdminUser.class).getBody();
    }

    @Override
    public void deleteAdminUser(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = adminServiceUrl + ADMINS + SLASH + id;
        HttpEntity<AdminModel> request = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, request, Void.class, 101);
    }
}
