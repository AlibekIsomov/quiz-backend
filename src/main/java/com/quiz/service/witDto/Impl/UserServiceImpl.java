package com.quiz.service.witDto.Impl;


import com.quiz.converter.AbstractDTOConverter;
import com.quiz.dto.UserDTO;
import com.quiz.entity.Role;
import com.quiz.entity.User;
import com.quiz.repository.DistributedRepository;
import com.quiz.repository.userRepository;
import com.quiz.service.witDto.UserService;
import com.quiz.vm.UserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@Service
public class UserServiceImpl extends AbstractDTOService<User, UserDTO> implements UserService {

    @Autowired
    userRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    public UserServiceImpl(DistributedRepository<User> repository, AbstractDTOConverter<User, UserDTO> converter) {
        super(repository, converter);
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @Override
    public boolean changePassword(UserVM vm) {
        Optional<User> user = userRepository.findByUsername(vm.getUsername());
        if(user.isPresent() && encoder.matches(vm.getOldPassword(),user.get().getPassword())){
            user.get().setPassword(encoder.encode(vm.getNewPassword()));
            userRepository.save(user.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserDTO getCurrentUser(){
        String username = getPrincipal();
        if (username != null)
            return userRepository.findByUsername(username).map(UserDTO::new).orElse(null);
        return null;
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Page<User> search(String key, Pageable pageable) {

        try{
            Long n=Long.parseLong(key);
            return userRepository.findAllByIdOrNameContainsIgnoreCaseOrSurnameContainsIgnoreCaseOrUsernameContainsIgnoreCaseOrEmailContainsIgnoreCase(n,key,key,key,key, pageable);
        }
        catch (Exception x) {
            return userRepository.findAllByIdOrNameContainsIgnoreCaseOrSurnameContainsIgnoreCaseOrUsernameContainsIgnoreCaseOrEmailContainsIgnoreCase((long)-1,key,key,key, key, pageable);
        }
    }


    @Override
    public void someChangesForCreate(User entity) {
        entity.setPassword(encoder.encode(entity.getPassword()));
        entity.setRoles(Set.of(Role.USER));
        entity.setActive(true);
    }

    @Override
    public void someChangesForUpdate(User entity) {

    }
}