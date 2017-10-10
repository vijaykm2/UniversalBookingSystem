package com.bookingsystem.entities;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="ACCOUNT")
public final class Account extends BaseEntity implements Comparable<Account> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public final Long id;

    @Column(name="ACCOUNT_ID", unique = true)
    private final String accountId;

    @Column
    private final String firstName;

    @Column
    private final String lastName;

    @Column
    private final String middleName;

    @Column
    private final String email;


    @Column(length = 60)
    private final String password;

    @Column
    private final boolean enabled;

    @Column
    private final String secret;

    @Column
    private final boolean isUsing2FA;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
    private final Collection<Role> roles;

    @ManyToMany
    @JoinTable(name="ACCOUNT_ADDRESSES", joinColumns = @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name="ADDRESS_ID", referencedColumnName = "ID"))
    private final Set<Address> addresses;

    public Account(Long id, String accountId, String firstName, String lastName, String middleName, String email, String password, boolean enabled, boolean isUsing2FA, String secret, Collection<Role> roles, Set<Address> addresses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.isUsing2FA = isUsing2FA;
        this.secret = secret;
        this.roles = roles;
        this.addresses = addresses;
        this.accountId = accountId;
        this.id = id;
    }

    private Account(){
        id = null;
        firstName = null;
        lastName = null;
        middleName = null;
        email = null;
        accountId = null;
        password = null;
        isUsing2FA = true;
        enabled = false;
        secret = null;
        addresses = null;
        roles = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (enabled != account.enabled) return false;
        if (isUsing2FA != account.isUsing2FA) return false;
        if (!id.equals(account.id)) return false;
        if (firstName != null ? !firstName.equals(account.firstName) : account.firstName != null) return false;
        if (lastName != null ? !lastName.equals(account.lastName) : account.lastName != null) return false;
        if (middleName != null ? !middleName.equals(account.middleName) : account.middleName != null) return false;
        if (email != null ? !email.equals(account.email) : account.email != null) return false;
        if (password != null ? !password.equals(account.password) : account.password != null) return false;
        return secret != null ? secret.equals(account.secret) : account.secret == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (isUsing2FA ? 1 : 0);
        result = 31 * result + (secret != null ? secret.hashCode() : 0);
        return result;
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public int compareTo(Account account) {
        return this.accountId.compareToIgnoreCase(account.accountId);
    }
}
