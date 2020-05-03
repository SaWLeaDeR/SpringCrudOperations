package com.mkoyuncuoglu.security.app.domain.service.impl;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import com.mkoyuncuoglu.security.app.domain.context.GroupContextMapper;
import com.mkoyuncuoglu.security.app.domain.model.Group;
import com.mkoyuncuoglu.security.app.domain.model.dto.AdminUser;
import com.mkoyuncuoglu.security.app.domain.model.dto.Guest;
import com.mkoyuncuoglu.security.app.domain.service.GroupService;
import java.util.List;
import javax.naming.Name;
import javax.naming.ldap.LdapName;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapNameAware;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService, BaseLdapNameAware {

    private static final String UNIQUE_MEMBER = "uniqueMember";

    private final LdapTemplate ldapTemplate;
    private LdapName baseLdapPath;

    public GroupServiceImpl(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public void setBaseLdapPath(LdapName baseLdapPath) {
        this.baseLdapPath = baseLdapPath;
    }

    @Override
    public List<Group> findAll() {
        return ldapTemplate.search(
            query().where("objectclass").is("groupOfUniqueNames"),
            new GroupContextMapper());
    }

    @Override
    public void addMemberToGroup(String groupName, AdminUser p) {
        Name groupDn = buildGroupDn(groupName);
        Name personDn = buildPersonDn(p);

        DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
        ctx.addAttributeValue(UNIQUE_MEMBER, personDn);

        ldapTemplate.modifyAttributes(ctx);
    }

    @Override
    public void addMemberToGroup(String groupName, Guest p) {
        Name groupDn = buildGroupDn(groupName);
        Name personDn = buildPersonDn(p);

        DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
        ctx.addAttributeValue(UNIQUE_MEMBER, personDn);

        ldapTemplate.modifyAttributes(ctx);
    }

    @Override
    public void removeMemberFromGroup(String groupName, AdminUser p) {
        Name groupDn = buildGroupDn(groupName);
        Name personDn = buildPersonDn(p);

        DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
        ctx.removeAttributeValue(UNIQUE_MEMBER, personDn);

        ldapTemplate.modifyAttributes(ctx);
    }

    @Override
    public void removeMemberFromGroup(String groupName, Guest p) {
        Name groupDn = buildGroupDn(groupName);
        Name personDn = buildPersonDn(p);

        DirContextOperations ctx = ldapTemplate.lookupContext(groupDn);
        ctx.removeAttributeValue(UNIQUE_MEMBER, personDn);

        ldapTemplate.modifyAttributes(ctx);

    }

    private Name buildGroupDn(String groupName) {
        return LdapNameBuilder.newInstance(baseLdapPath)
            .add("ou", "groups")
            .add("cn", groupName)
            .build();
    }

    private Name buildPersonDn(AdminUser person) {
        return LdapNameBuilder.newInstance(baseLdapPath)
            .add("ou", "people")
            .add("uid", person.getUserName())
            .build();
    }

    private Name buildPersonDn(Guest person) {
        return LdapNameBuilder.newInstance(baseLdapPath)
            .add("ou", "people")
            .add("uid", person.getUserName())
            .build();
    }
}
