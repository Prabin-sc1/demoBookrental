package com.bookrental.bookrental.service.user;

import com.bookrental.bookrental.config.CustomMessageSource;
import com.bookrental.bookrental.constants.ModuleNameConstants;
import com.bookrental.bookrental.enums.Message;
import com.bookrental.bookrental.exception.AppException;
import com.bookrental.bookrental.mapper.UserMapper;
import com.bookrental.bookrental.model.User;
import com.bookrental.bookrental.pojo.user.UserRequestPojo;
import com.bookrental.bookrental.pojo.user.UserResponsePojo;
import com.bookrental.bookrental.repository.UserRepository;
import com.bookrental.bookrental.utils.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.List;

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
        u.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(u);
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

    @Override
    public UserResponsePojo getUserById(Integer id) {
        return userMapper.getSingleUser(id).orElseThrow(() -> new AppException("User not found"));
    }

    @Override
    public List<UserResponsePojo> getAllUser() {
        return userMapper.allUser();
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

}
