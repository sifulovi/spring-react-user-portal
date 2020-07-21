package com.ovi.user.service;

import com.ovi.user.domain.User;
import com.ovi.user.exception.UserEmailException;
import com.ovi.user.payload.request.LoginRequest;
import com.ovi.user.payload.request.SignUpRequest;
import com.ovi.user.payload.response.UserDetailsResponseModel;
import com.ovi.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void save(SignUpRequest user) throws UserEmailException {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            User newUser = new User();
            BeanUtils.copyProperties(user, newUser);
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            userRepository.save(newUser);
        }
        else {
            throw new UserEmailException("Email address already exist");
        }
    }

    public Boolean login(LoginRequest loginRequest) throws UserEmailException {
        User getUser = userRepository.findByEmail(loginRequest.getEmail());
        if (getUser == null) {
            return false;
        }
        return getUser.getPassword().equals(loginRequest.getPassword());
    }

    public UserDetailsResponseModel showUserInfo(String email) throws UserEmailException {
        UserDetailsResponseModel userDetailsResponseModel = new UserDetailsResponseModel();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserEmailException("Not Found");
        }
        BeanUtils.copyProperties(user, userDetailsResponseModel);
        return userDetailsResponseModel;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        Collection<GrantedAuthority> collection = new ArrayList<>();
        GrantedAuthority grantedAuthority = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "USER";
            }
        };
        collection.add(grantedAuthority);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), collection);
    }
}
