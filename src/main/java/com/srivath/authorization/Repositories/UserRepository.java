package com.srivath.authorization.Repositories;

import com.srivath.authorization.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    Optional<User> findByUserName(String id);
    Optional<User> findByEmail(String email);
    //List<User> findAllUsers();
    //User save(User user);


}
