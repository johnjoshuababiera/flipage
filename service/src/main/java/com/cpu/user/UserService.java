package com.cpu.user;

import java.util.List;

public interface UserService {
    User findOne(long id);
    User save(User user) throws Exception;
    boolean hasDuplicate(User user) throws Exception;
    User getUser(String idNumber, String password);
    User updateUser(User user) throws Exception;
    boolean noAdmin();
    void initializeAdmin() throws Exception;
}
