package com.mkoyuncuoglu.security.app.delegate.service.impl;

import com.mkoyuncuoglu.security.app.delegate.model.GuestModel;
import com.mkoyuncuoglu.security.app.delegate.service.GuestDelegateService;
import com.mkoyuncuoglu.security.app.domain.model.dto.Guest;
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
public class GuestDelegateServiceImpl implements GuestDelegateService {

    private static final String GUESTS = "/guests";
    private static final String SLASH = "/";
    private final RestTemplate restTemplate;
    @Value("${landon.guest.service.url}")
    private String guestServiceUrl;

    public GuestDelegateServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Guest> getAllGuests() {
        String url = guestServiceUrl + GUESTS;
        HttpEntity<String> request = new HttpEntity<>(null, null);
        return this.restTemplate.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<List<Guest>>() {
        }).getBody();
    }

    @Override
    public Guest addGuest(GuestModel guestModel) {
        String url = guestServiceUrl + GUESTS;
        HttpEntity<GuestModel> request = new HttpEntity<>(guestModel, null);
        return this.restTemplate.exchange(url, HttpMethod.POST, request, Guest.class).getBody();
    }

    @Override
    public Guest getGuest(Long id) {
        String url = guestServiceUrl + GUESTS + SLASH + id;
        HttpEntity<String> request = new HttpEntity<>(null, null);
        return this.restTemplate.exchange(url, HttpMethod.GET, request, Guest.class).getBody();
    }

    @Override
    public Guest updateGuest(Long id, GuestModel guestModel) {
        String url = guestServiceUrl + GUESTS + SLASH + id;
        HttpEntity<GuestModel> request = new HttpEntity<>(guestModel, null);
        return this.restTemplate.exchange(url, HttpMethod.PUT, request, Guest.class).getBody();
    }

    @Override
    public void deleteGuest(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = guestServiceUrl + GUESTS + SLASH + id;
        HttpEntity<GuestModel> request = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, request, Void.class, 101);
    }
}
