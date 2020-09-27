package com.internalproject.api.repository.user;

import com.internalproject.api.model.entity.user.Role;
import com.internalproject.api.model.entity.user.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(RoleType type);
}
