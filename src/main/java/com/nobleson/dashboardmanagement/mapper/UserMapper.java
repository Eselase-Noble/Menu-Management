package com.nobleson.dashboardmanagement.mapper;

import com.nobleson.dashboardmanagement.dto.UserDTO;
import com.nobleson.dashboardmanagement.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User UserDTOToUser(UserDTO userDTO);
    UserDTO UserToUserDTO(User user);
}
