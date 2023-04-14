package org.example.Entities;

import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ApplicationUser {

    public ApplicationUser(String userName, String email, String password, String name, String lastName, String address, Set<Role> roles) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.roles = roles;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    private String email;

    private String password;

    private String name;

    private String lastName;

    private String address;

    @ManyToMany
    @ToString.Exclude
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ApplicationUser applicationUser = (ApplicationUser) o;
        return id != null && Objects.equals(id, applicationUser.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
