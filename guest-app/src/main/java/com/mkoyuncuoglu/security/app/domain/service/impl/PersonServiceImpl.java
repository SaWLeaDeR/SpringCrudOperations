package com.mkoyuncuoglu.security.app.domain.service.impl;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import com.mkoyuncuoglu.security.app.domain.context.PersonContextMapper;
import com.mkoyuncuoglu.security.app.domain.model.dto.AdminUser;
import com.mkoyuncuoglu.security.app.domain.model.dto.Guest;
import com.mkoyuncuoglu.security.app.domain.service.PersonService;
import java.util.List;
import javax.naming.Name;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.LdapName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapNameAware;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService, BaseLdapNameAware {

    private final LdapTemplate ldapTemplate;
    private LdapName baseLdapPath;

    public PersonServiceImpl(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public void setBaseLdapPath(LdapName baseLdapPath) {
        this.baseLdapPath = baseLdapPath;
    }

    @Override
    public void create(AdminUser p) {
        Name dn = buildDn(p);
        ldapTemplate.bind(dn, null, buildAttributes(p));
    }

    @Override
    public void create(Guest p) {
        Name dn = buildDn(p);
        ldapTemplate.bind(dn, null, buildAttributes(p));
    }

    @Override
    public List<AdminUser> findAll() {
        EqualsFilter filter = new EqualsFilter("objectclass", "person");
        return ldapTemplate.search(LdapUtils.emptyLdapName(), filter.encode(), new PersonContextMapper());
    }

    @Override
    public AdminUser findOne(String uid) {
        Name dn = LdapNameBuilder.newInstance(baseLdapPath)
            .add("ou", "people")
            .add("uid", uid)
            .build();
        return ldapTemplate.lookup(dn, new PersonContextMapper());
    }

    @Override
    public List<AdminUser> findByName(String name) {
        LdapQuery q = query()
            .where("objectclass").is("person")
            .and("cn").whitespaceWildcardsLike(name);
        return ldapTemplate.search(q, new PersonContextMapper());
    }

    @Override
    public void update(AdminUser p) {
        ldapTemplate.rebind(buildDn(p), null, buildAttributes(p));
    }

    @Override
    public void updateLastName(AdminUser p) {
        Attribute attr = new BasicAttribute("sn", p.getLastName());
        ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);
        ldapTemplate.modifyAttributes(buildDn(p), new ModificationItem[]{item});
    }

    @Override
    public void delete(AdminUser p) {
        ldapTemplate.unbind(buildDn(p));
    }

    @Override
    public void delete(Guest p) {
        ldapTemplate.unbind(buildDn(p));
    }

    private Name buildDn(AdminUser p) {
        return LdapNameBuilder.newInstance(baseLdapPath)
            .add("ou", "people")
            .add("uid", p.getUserName())
            .build();
    }

    private Name buildDn(Guest p) {
        return LdapNameBuilder.newInstance(baseLdapPath)
            .add("ou", "people")
            .add("uid", p.getUserName())
            .build();
    }

    private Attributes buildAttributes(AdminUser p) {
        Attributes attrs = new BasicAttributes();
        BasicAttribute ocAttr = new BasicAttribute("objectclass");
        ocAttr.add("top");
        ocAttr.add("person");
        ocAttr.add("organizationalPerson");
        ocAttr.add("inetOrgPerson");
        attrs.put(ocAttr);
        attrs.put("ou", "people");
        attrs.put("uid", p.getUserName());
        attrs.put("cn", p.getFirstName());
        attrs.put("sn", p.getLastName());
        attrs.put("userPassword", p.getUserPassword());
        return attrs;
    }

    private Attributes buildAttributes(Guest p) {
        Attributes attrs = new BasicAttributes();
        BasicAttribute ocAttr = new BasicAttribute("objectclass");
        ocAttr.add("inetOrgPerson");
        attrs.put(ocAttr);
        attrs.put("ou", "people");
        attrs.put("uid", p.getUserName());
        attrs.put("cn", p.getFirstName());
        attrs.put("sn", p.getLastName());
        attrs.put("userPassword", p.getPassword());
        return attrs;
    }
}
