package org.example.Entities;

import lombok.*;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ApplicationUser {

    public ApplicationUser(String userName, String email, String password, String name, String lastName, String address, Set<Role> roles, Subscription subscription) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.roles = roles;
        this.subscription = subscription;
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

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationUser that = (ApplicationUser) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getUserName(), that.getUserName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getName(), that.getName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getRoles(), that.getRoles()) && Objects.equals(getSubscription(), that.getSubscription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getEmail(), getPassword(), getName(), getLastName(), getAddress(), getRoles(), getSubscription());
    }
}
