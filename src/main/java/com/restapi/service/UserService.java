package com.restapi.service;

import com.restapi.dto.UserDTO;
import com.restapi.entity.Role;
import com.restapi.entity.User;
import com.restapi.repository.RoleRepository;
import com.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository ){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Set<GrantedAuthority> authorities = user
                .getRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public User readUserById(Long userId){
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public User readUserByUsername(String userName){
        return userRepository.findByUsername(userName);
    }

    public List<User> readAll(){
        return userRepository.findAll();
    }

    public boolean save(UserDTO userDto) {
        User userFromDB = userRepository.findByUsername(userDto.getUsername());

        if (userFromDB != null) {
            return false;
        }
        String encodePassword = passwordEncoder.encode(userDto.getPassword());
        User user = User.builder()
                .username(userDto.getUsername())
                .password(encodePassword)
                .roles(Collections.singleton(new Role(1L, "ROLE_USER")))
                .build();
        userRepository.save(user);
        return true;
    }
    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

}
