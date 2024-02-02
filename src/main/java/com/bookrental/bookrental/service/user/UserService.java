package com.bookrental.bookrental.service.user;

import com.bookrental.bookrental.pojo.user.UserRequestPojo;
import com.bookrental.bookrental.pojo.user.UserResponsePojo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserService {
    void createUser(UserRequestPojo user);

    UserResponsePojo getUserById(Integer id);

    List<UserResponsePojo> getAllUser();

    void deleteUser(Integer id);

    Boolean changePassword(String oldPassword, String newPassword, Principal principal);

    ByteArrayInputStream getExcelData() throws IOException;
}
