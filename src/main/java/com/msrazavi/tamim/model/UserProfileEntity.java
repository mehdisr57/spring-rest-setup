package com.msrazavi.tamim.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by Mehdi on 2/1/2018.
 */
@Entity
@Table(name = "TBL_USER_PROFILE")
@Access(AccessType.PROPERTY)
public class UserProfileEntity {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserEntity user;

    @Id
    @Column(name = "PK_USER_PROFILE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotEmpty(message = "first name is mandatory")
    @Column(name = "FIRST_NAME", length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotEmpty(message = "last name is mandatory")
    @Column(name = "LAST_NAME", length = 100)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Email
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
