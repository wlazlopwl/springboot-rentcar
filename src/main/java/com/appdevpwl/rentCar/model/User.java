package com.appdevpwl.rentCar.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, name = "name")
    @NotNull(message = "This field is required")
    @Size(min = 3, max = 14, message = "First name must be between 3 and 14 characters")
    private String name;
    @Column(name = "surname")
    @NotNull(message = "This field is required")
    @Size(min = 3, max = 14, message = "Surname must be between 3 and 14 characters")
    private String surname;
    @Column(name = "phone")
    @NotNull(message = "This field is required")
    @Size(min = 12, max = 12, message = "Phone number must be format +48XXXXXXXXX")
    private String phone;
    @Column(name = "pesel")
    @NotNull(message = "This field is required")
    @Size(min = 11, max = 11, message = "PESEL must have 11 characters")
    private String pesel;

    private String email;
    @NotNull(message = "This field is required")
    @Size(min = 7, message = "Password must have min. 8 characters")
    private String password;
    @Column(name = "is_enabled")
    private Boolean isEnabled;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
