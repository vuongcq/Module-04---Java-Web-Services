package com.example.session08.service;

import com.example.session08.model.dto.request.UserDTO;
import com.example.session08.model.dto.response.UserLoginResponse;
import com.example.session08.model.entity.Role;
import com.example.session08.model.entity.User;
import com.example.session08.repository.RoleRepository;
import com.example.session08.repository.UserRepository;
import com.example.session08.security.jwt.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private JWTProvider jwtProvider;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User register(UserDTO userDTO) {
        User userExists = userRepository.findByUsername(userDTO.getUsername());
        if (userExists != null) {
            return null ;
        }else {
            Role roleUser = roleService.findByRoleName("user");
            User newUser = User
                    .builder()
                    .username(userDTO.getUsername())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .role(roleUser)
                    .build();
            try {
                return userRepository.save(newUser);
            }catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

    }

    public UserLoginResponse login(UserDTO userDTO) {
        User userLogin = getUserByUsername(userDTO.getUsername());
        if (userLogin != null && passwordEncoder.matches(userDTO.getPassword(), userLogin.getPassword())) {
            return UserLoginResponse
                    .builder()
                    .username(userDTO.getUsername())
                    .accessToken(jwtProvider.generateToken(userDTO.getUsername()))
                    .build();
        }
        else {
            return null;
        }
    }

    public String updatePassword(User user, String oldPassword, String newPassword, String confirmPassword) {
        // Kiểm tra thông tin và cập nhật mật khẩu
        if (passwordEncoder.matches(oldPassword,user.getPassword()) && newPassword.equals(confirmPassword)) {
            user.setPassword(newPassword);
            userRepository.save(user);
            return "Changed password successfully";
        } else {
            return "Passwords do not match";
        }
    }
}
