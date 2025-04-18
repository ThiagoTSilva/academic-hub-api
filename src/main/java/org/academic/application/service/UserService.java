package org.academic.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.academic.application.dto.user.UserResponse;
import org.academic.application.mappers.UserMapper;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    private final Keycloak keycloak;

    @ConfigProperty(name = "keycloak.target-realm")
    String targetRealm;



    public UserService(@ConfigProperty(name = "keycloak.server-url") String serverUrl,
                       @ConfigProperty(name = "keycloak.main-realm") String mainRealm,
                       @ConfigProperty(name = "keycloak.login") String login,
                       @ConfigProperty(name = "keycloak.password") String password,
                       @ConfigProperty(name = "keycloak.client-id") String clientId) {
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(mainRealm)
                .username(login)
                .password(password)
                .clientId(clientId)
                .build();
    }

    public List<UserResponse> listAll() {
        List<UserRepresentation> user = keycloak.realm(targetRealm).users().list();

        return user.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void create(UserResponse userResponse) {
        Response response = keycloak.realm(targetRealm)
                .users()
                .create(UserMapper.toEntity(userResponse));

        if (response.getStatus() != 201) {
            throw new WebApplicationException("Erro ao criar usuário: " + response.getStatusInfo(), response.getStatus());
        }
    }

    public void update(String id, UserResponse userResponse) {

        UserResource userResource = keycloak.realm(targetRealm).users().get(id);
        UserRepresentation user = userResource.toRepresentation();
        user.setEmail(userResponse.email);
        userResource.update(user);
    }

    public void delete(String id) {
        keycloak.realm(targetRealm)
                .users()
                .get(id)
                .remove();
    }

    public List<UserResponse> getUserById(String id) {
        var user = keycloak.realm(targetRealm)
                    .users()
                    .get(id)
                    .toRepresentation();

        return List.of(UserMapper.toDTO(user));
    }

}
