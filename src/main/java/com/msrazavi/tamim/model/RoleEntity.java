package com.msrazavi.tamim.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "TBL_ROLE")
@Access(AccessType.PROPERTY)
public class RoleEntity {
    private Long id;
    private String name;
    private Set<UserEntity> users;

    @Id
    @Column(name = "PK_ROLE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonManagedReference
    @ManyToMany(mappedBy = "roles")
    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}
