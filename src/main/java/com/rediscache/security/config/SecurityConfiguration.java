package com.rediscache.security.config;


import com.rediscache.security.entity.Role;
import com.rediscache.security.repository.RoleRepository;
import com.rediscache.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SecurityConfiguration {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    public void setupData(){
        //Roles
        Role role;

        role = new Role("USER");
        role.setId(1L);
        roleRepository.save(role);

        role = new Role("MODERATOR");
        role.setId(2L);
        roleRepository.save(role);

        role = new Role("ADMIN");
        role.setId(3L);
        roleRepository.save(role);

    }
}
