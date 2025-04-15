package org.academic.application.mappers;

import org.academic.application.dto.UserDTO;
import org.keycloak.representations.idm.UserRepresentation;

public class UserMapper {

    public static UserDTO toDTO(UserRepresentation user) {
        UserDTO dto = new UserDTO();
        dto.id = user.getId();
        dto.username = user.getUsername();
        dto.email = user.getEmail();

        return dto;
    }

    public static UserRepresentation toEntity(UserDTO userDto){
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDto.username);
        user.setEmail(userDto.email);
        user.setEnabled(true);
        return user;
    }

}
