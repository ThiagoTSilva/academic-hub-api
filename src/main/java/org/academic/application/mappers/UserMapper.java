package org.academic.application.mappers;

import org.academic.application.dto.user.UserResponse;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserResponse toDTO(UserRepresentation user) {
        UserResponse dto = new UserResponse();
        dto.id = user.getId();
        dto.username = user.getUsername();
        dto.email = user.getEmail();

        System.out.println(user.getClientRoles());

        Map<String, List<String>> clientRoles = user.getClientRoles();
        if (clientRoles != null) {
            List<String> allRoles = clientRoles.values()
                    .stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
            dto.setRole(allRoles);
        }

        return dto;
    }

    public static UserRepresentation toEntity(UserResponse userResponse){
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userResponse.username);
        user.setEmail(userResponse.email);
        user.setEnabled(true);
        return user;
    }

}
