package com.internalproject.api.repository.user;

import com.internalproject.api.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsUserByUserNameAndEmail(String userName, String userEmail);

    boolean existsUserByEmail(String userEmail);

    User findByUserName(String userName);

    User findByEmail(String email);
}
