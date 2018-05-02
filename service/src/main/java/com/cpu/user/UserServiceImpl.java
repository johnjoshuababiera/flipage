package com.cpu.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository repository;

    @Override
    public User findOne(long id) {
        return repository.getOne(id);
    }

    @Override
    public User save(User user) {
        encryptPassword(user);
        return repository.save(user);
    }

    @Override
    public User validateUser(String username, String password) {
        User user = repository.findByUsername(username);
        if(user!=null && PASSWORD_ENCODER.matches(password,user.getPassword())){
            return user;
        }
        return null;
    }

    @Override
    public void remove(long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean hasDuplicate(User user) throws Exception {
        User duplicate = repository.findByUsername(user.getUsername());
        if(duplicate(user, duplicate)){
            throw new Exception("Duplicate username!");
        }
        duplicate= repository.findByEmail(user.getUsername());
        if(duplicate(user, duplicate)){
            throw new Exception("Duplicate email!");
        }
        return false;
    }

    private boolean duplicate(User user, User duplicate) {
        return duplicate!=null && duplicate.getId()==user.getId();
    }

    private void encryptPassword(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }
}
