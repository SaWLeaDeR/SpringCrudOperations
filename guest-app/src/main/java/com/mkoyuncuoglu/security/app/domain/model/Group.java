package com.mkoyuncuoglu.security.app.domain.model;

import java.util.HashSet;
import java.util.Set;
import javax.naming.Name;

public class Group {

    private String name;
    private Set<Name> members;

    public Set<Name> getMembers() {
        return members;
    }

    public void setMembers(Set<Name> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMember(Name member) {
        if (this.members == null) {
            this.members = new HashSet<>();
        }
        members.add(member);
    }

    public void removeMember(Name member) {
        members.remove(member);
    }

    @Override
    public String toString() {
        return "Group{" +
            "name='" + name + '\'' +
            ", members=" + members +
            '}';
    }
}