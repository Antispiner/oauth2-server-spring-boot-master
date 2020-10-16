package com.internalproject.api.repository.user;

import com.internalproject.api.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsUserByUserName(String userName);

    boolean existsUserByEmail(String userName);

    User findByUserName(String userName);
}
