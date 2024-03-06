package com.springsecutiyinmemory.services;

import com.springsecutiyinmemory.models.Error;
import com.springsecutiyinmemory.models.User;
import com.springsecutiyinmemory.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity getAllUsers(){
        try {

            List<User> userList = userRepository.findAll();

            if (userList.isEmpty()) {
                Error error = Error.builder().Code(HttpStatus.BAD_REQUEST.getReasonPhrase()).message("Sorry!! There is no user in databaser").build();
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(userList,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            Error error= Error.builder().Code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Sorry!! Internel Server Error occured").build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity addUser(User user){
        try {
//            if (ObjectUtils.isEmpty(user)) {
//                Error error = Error.builder().Code(HttpStatus.BAD_REQUEST.getReasonPhrase()).message("Sorry!! user is null").build();
//                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//            }

            User savingUser = userRepository.save(user);
            return new ResponseEntity<>(savingUser,HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            Error error = Error.builder().Code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Soory!! Internel Server error occured").build();
            return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
