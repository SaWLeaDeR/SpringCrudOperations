package com.mkoyuncuoglu.security.app.domain.context;

import com.mkoyuncuoglu.security.app.domain.model.Group;
import javax.naming.Name;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Component;

@Component
public class GroupContextMapper extends AbstractContextMapper<Group> {

    private static final String UNIQUE_MEMBER = "uniqueMember";

    public Group doMapFromContext(DirContextOperations context) {
        Group group = new Group();
        group.setName(context.getStringAttribute("cn"));
        Object[] members = context.getObjectAttributes(UNIQUE_MEMBER);
        for (Object member : members) {
            Name memberDn = LdapNameBuilder.newInstance(String.valueOf(member)).build();
            group.addMember(memberDn);
        }
        return group;
    }

}
