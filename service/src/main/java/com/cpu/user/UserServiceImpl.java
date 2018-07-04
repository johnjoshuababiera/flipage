package com.cpu.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    @Value("${adminId}")
    private String ADMIN_ID;
    @Value("${adminPassword}")
    private String ADMIN_PASS;



    @Autowired
    private UserRepository repository;

    @Override
    public User findOne(long id) {
        return repository.getOne(id);
    }

    @Override
    public User save(User user) throws Exception {
        if(!hasDuplicate(user)){
            encryptPassword(user);
            return repository.save(user);
    }
        return null;
    }


    @Override
    public User updateUser(User user) throws Exception {
        User savedUser = repository.getOne(user.getId());
        if(!hasDuplicate(user)){
            savedUser.setUsername(user.getUsername());
            savedUser.setAdmin(user.isAdmin());
            savedUser.setDepartment(user.getDepartment());
            savedUser.setIdNumber(user.getIdNumber());
            savedUser.setImage(user.getImage());
            savedUser.setEmail(user.getEmail());
        }
        return null;

    }

    @Override
    public boolean noAdmin() {
        List<User> adminUsers = repository.findByAdminIsTrue();
        return adminUsers==null || adminUsers.isEmpty();
    }

    @Override
    public void initializeAdmin() throws Exception {
        User user = new User();
        user.setAdmin(true);
        user.setIdNumber(ADMIN_ID);
        user.setPassword(ADMIN_PASS);
        save(user);
    }

    @Override
    public boolean hasDuplicate(User user) throws Exception {
        User duplicateIdNo = repository.findByIdNumber(user.getIdNumber());
        if(duplicate(user, duplicateIdNo)){
            throw new Exception("ID number is already in used!");
        }
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

    @Override
    public User getUser(String idNumber, String password) {
        User user = repository.findByIdNumber(idNumber);
        if(user!=null && PASSWORD_ENCODER.matches(password,user.getPassword())){
            return user;
        }
        return null;
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
