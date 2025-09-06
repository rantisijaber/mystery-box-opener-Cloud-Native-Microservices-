package com.jaberrantisi.userservice.repo;

import com.jaberrantisi.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    void deleteUserByCognitoSub(String sub);
    User findUserByUsername(String username);
}
