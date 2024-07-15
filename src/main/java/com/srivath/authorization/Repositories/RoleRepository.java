package com.srivath.authorization.Repositories;


import com.srivath.authorization.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{
    Optional<Role> findByRoleName(String role);
    //Role save(Role role);
    //List<Role> findAllRoles();
}
