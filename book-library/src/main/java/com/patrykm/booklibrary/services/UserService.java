package com.patrykm.booklibrary.services;

import com.patrykm.booklibrary.domain.Role;
import com.patrykm.booklibrary.domain.User;
import com.patrykm.booklibrary.dto.UserDto;
import com.patrykm.booklibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    //@Transactional
    public void createUser(String username, String password) {
        if (username != null && password != null) {
            PasswordEncoder pe = PasswordEncoderFactories.createDelegatingPasswordEncoder();

            User user = new User(username, pe.encode(password));
            userRepository.addUser(user);
            //User user = userRepository.getUser(username);
            //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + user.getRoles() == null);

            //if (user == null) {
            //    User newUser = new User(username, password);
            //    userRepository.addUser(newUser);

        }
    }

    //@Transactional
    public void addRoleUser(String username, String roleName) {
        if (username != null && roleName != null) {
            Role role = new Role(roleName);
            userRepository.addRoleToUser(username, role);
        }
    }

    public User getUser(String username) {
        return userRepository.getUser(username);
    }

    public User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null){
            String username = auth.getName();
            return getUser(username);
        }else
            return null;
    }

    public UserDto convert(User user){
        if(user == null)
            return null;

        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setFullName(user.getFirstName() + " " + user.getLastName());
        return userDto;
    }

}
