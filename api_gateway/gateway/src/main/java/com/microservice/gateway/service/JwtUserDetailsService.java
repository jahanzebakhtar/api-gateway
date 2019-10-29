package com.microservice.gateway.service;

import com.microservice.gateway.dao.UserDao;
import com.microservice.gateway.dao.UserRoleDao;
import com.microservice.gateway.model.UserRoles;
import com.microservice.gateway.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> usersList = userDao.findById(username);
        //List<GrantedAuthority> authorities = new ArrayList<>();
        Set authorities = new HashSet<>();
        if(usersList.isPresent()) {
            Users users = usersList.get();
            if (users != null) {

                if (users.getUsername().equals(username)) {
                    Iterable<UserRoles> userRoles = userRoleDao.findAll();

                    userRoles.forEach(userRole -> {
                        authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getUserRole()));
                    });
                    return new User(username, users.getPassword(),
                            authorities);
                } else {
                    throw new UsernameNotFoundException("User not found with username: " + username);
                }
            } else {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}