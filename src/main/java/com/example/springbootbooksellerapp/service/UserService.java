package com.example.springbootbooksellerapp.service;

import com.example.springbootbooksellerapp.model.Role;
import com.example.springbootbooksellerapp.model.User;
import com.example.springbootbooksellerapp.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService implements  IUserService {


    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole((Role.USER));
        user.setCreateTime(LocalDateTime.now());

        return  userRepository.save(user);
    }

    //Username göre kullanıcı bulma

    @Override
    public Optional<User> findByUsername(String username){

        return  userRepository.findByUsername(username);
    }


    //Varsayılan kuyllanıcı rolünün admin olarak değiştirme
    //Spring de silme güncelleme işlemi yapıcaksak transaction anatasyonu zorunludur
    //transactional clas seviyesinde tanımlanırsa sınıf altındakii tüm metodları etkiler metod üstüne tanımlarsan sadece o metodu etkiler

    @Override
    @Transactional
    public  void makeAdmin(String username) {

        userRepository.updateUserRole(username,Role.ADMIN);
    }

}
