package com.mkoyuncuoglu.security.app.domain.context;

import com.mkoyuncuoglu.security.app.domain.model.dto.AdminUser;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonContextMapper extends AbstractContextMapper<AdminUser> {

    public AdminUser doMapFromContext(DirContextOperations context) {
        AdminUser person = new AdminUser();
        person.setFirstName(context.getStringAttribute("cn"));
        person.setLastName(context.getStringAttribute("sn"));
        return person;
    }

}
