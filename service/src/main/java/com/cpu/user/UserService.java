package com.cpu.user;

import java.util.List;

public interface UserService {
    User findOne(long id);
    User save(User user);
    User validateUser(String username, String password);
    void remove(long id);
    boolean hasDuplicate(User user) throws Exception;
}
