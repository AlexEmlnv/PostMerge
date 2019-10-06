package com.corporation.postmerge;

import java.util.Objects;
import java.util.Set;

public class User {
    private String name;
    private Set<String> mailAddresses ;

    public User(String name, Set<String> mailAddresses) {
        this.name = name;
        this.mailAddresses = mailAddresses;
    }

    public String getName() {
        return name;
    }

    public Set<String> getMailAddresses() {
        return mailAddresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + " -> " + mailAddresses;
    }
}
