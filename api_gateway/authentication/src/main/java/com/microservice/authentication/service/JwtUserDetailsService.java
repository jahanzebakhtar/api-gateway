package com.microservice.authentication.service;

import java.util.*;

import com.microservice.authentication.dao.UserDao;
import com.microservice.authentication.dao.UserRoleDao;
import com.microservice.authentication.model.UserDTO;
import com.microservice.authentication.model.UserRoles;
import com.microservice.authentication.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

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
                        authorities.add(new SimpleGrantedAuthority(userRole.getUserRole()));
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

    public Users save(UserDTO user) {
        Users newUser = new Users();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDao.save(newUser);
    }
}