package com.bookrental.bookrental.service.user;

import com.bookrental.bookrental.config.CustomMessageSource;
import com.bookrental.bookrental.constants.ModuleNameConstants;
import com.bookrental.bookrental.enums.Message;
import com.bookrental.bookrental.exception.AppException;
import com.bookrental.bookrental.helpers.Helper;
import com.bookrental.bookrental.mapper.UserMapper;
import com.bookrental.bookrental.model.Author;
import com.bookrental.bookrental.model.User;
import com.bookrental.bookrental.pojo.ChangePasswordRequest;
import com.bookrental.bookrental.pojo.user.ChangePasswordOTPRequest;
import com.bookrental.bookrental.pojo.user.UserRequestPojo;
import com.bookrental.bookrental.pojo.user.UserResponsePojo;
import com.bookrental.bookrental.repository.UserRepository;
import com.bookrental.bookrental.service.email.NewEmailService;
import com.bookrental.bookrental.service.otp.OTPService;
import com.bookrental.bookrental.utils.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();

    private final PasswordEncoder passwordEncoder;

    private final CustomMessageSource customMessageSource;

    private final UserMapper userMapper;

    @Override
    public void createUser(UserRequestPojo user) {
        User u = new User();
        if (user.getId() != null) {
            u = userRepository.findById(user.getId()).orElse(u);
        }
        try {
            beanUtils.copyProperties(u, user);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AppException(e.getMessage());
        }

        u.setEmail(user.getEmail().toLowerCase());
        u.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            userRepository.save(u);
        } catch (Exception e) {
            throw new AppException(customMessageSource.get(Message.ALREADY_EXISTS.getCode(), ModuleNameConstants.USER));
        }
    }

    @Override
    public UserResponsePojo getUserById(Integer id) {
        return userMapper.getSingleUser(id).orElseThrow(() -> new AppException(customMessageSource.get(Message.ID_NOT_FOUND.getCode(), ModuleNameConstants.USER)));
    }

    @Override
    public List<UserResponsePojo> getAllUser() {
        return userMapper.allUser();
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }


    public static String SHEET_NAME = "author";

    public static String[] getHeaders(Class<?> className) {
        List<String> headers = new ArrayList<>();
        Field[] fields = className.getDeclaredFields();
        for (Field field : fields) {
            headers.add(field.getName());
        }
        return headers.toArray(new String[headers.size()]);
    }

    public ByteArrayInputStream getExcelData() throws IOException {
        List<UserResponsePojo> all = userMapper.allUser();
        ByteArrayInputStream byteArrayInputStream = Helper.dataToExcel(all, SHEET_NAME, getHeaders(UserResponsePojo.class));
        return byteArrayInputStream;
    }

    @Override
    public Boolean changePassword(String oldPassword, String newPassword, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new AppException(customMessageSource.get(Message.ID_NOT_FOUND.getCode(), ModuleNameConstants.USER)));
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new AppException(customMessageSource.get(Message.PASSWORD_NOT_MATCH.getCode(), ModuleNameConstants.USER));
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return true;
    }
}
