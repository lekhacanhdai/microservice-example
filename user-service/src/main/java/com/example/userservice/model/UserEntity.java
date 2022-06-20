package com.example.userservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel(value = "User", description = "User in the system")
public class UserEntity {

    @ApiModelProperty(notes = "Id of user generated", name = "id", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "Username of user", name = "username", required = true)
    private String username;
    @ApiModelProperty(notes = "Password of user", name = "password", required = true)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @ApiModelProperty(notes = "Role of user", name = "roles", required = true)
    private Set<Role> roles = new HashSet<>();

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
