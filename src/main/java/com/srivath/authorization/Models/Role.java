package com.srivath.authorization.Models;

import ch.qos.logback.core.joran.action.BaseModelAction;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Role extends BaseModel  {
    private String roleName;
    private String roleDescription;
//    @ManyToMany(mappedBy = "roles")
//    private List<User> users;

    public Role() {

    }
    public Role(String roleName, String roleDescription) {
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                '}';
    }
}
