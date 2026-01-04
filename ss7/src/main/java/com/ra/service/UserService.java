package com.ra.service;

import com.ra.model.dto.user.LoginRequestDTO;
import com.ra.model.dto.user.UserResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    // phuong thuc dang ky nguoi dung và phương thức login

    // Phương thưức login sẽ trả về thông tin nguời dùng, Token JWT không trả về mk
    // Phương thức register có thể trả về thông báo đăng ký thành công hoặc cả thông tin người dùng, không trả
    // về Token(Nếu như k yêu cầu tự động login)
    UserResponseDTO registerUser(UserResponseDTO userResponseDTO);
    UserResponseDTO loginUser(LoginRequestDTO loginRequestDTO);
}
