package com.mkoyuncuoglu.security.app.domain.service;

import com.mkoyuncuoglu.security.app.domain.model.Group;
import com.mkoyuncuoglu.security.app.domain.model.dto.AdminUser;
import com.mkoyuncuoglu.security.app.domain.model.dto.Guest;
import java.util.List;

public interface GroupService {

    List<Group> findAll();

    void addMemberToGroup(String groupName, AdminUser p);

    void addMemberToGroup(String groupName, Guest p);

    void removeMemberFromGroup(String groupName, AdminUser p);

    void removeMemberFromGroup(String groupName, Guest p);

}
