package com.quiz.converter;


import com.quiz.dto.UserDTO;
import com.quiz.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConvertor extends AbstractDTOConverter<User, UserDTO> {
    @Override
    public UserDTO convert(User entity) {
        UserDTO userDTO = new UserDTO(entity);

        super.convert(entity, userDTO);

        return userDTO;
    }
}
