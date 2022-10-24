package com.rediscache.security.repository;

import com.rediscache.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    //Optional<Role> findByName(ERole name);
    Role findByName(String role);
}
