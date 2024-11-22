package co.com.ibero.util;

import co.com.ibero.dto.UserDTO;
import co.com.ibero.user.User;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@UtilityClass
public class BuilderServerRequest {
    private ModelMapper modelMapper = new ModelMapper();

    public Mono<User> buildRequestCreateUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserDTO.class)
                .map(userDTO -> modelMapper.map(userDTO, User.class))
                .map(user -> user.toBuilder().userId(UUID.randomUUID().toString()).build());
    }

    public Mono<User> buildRequestUpdateUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserDTO.class)
                .map(userDTO -> modelMapper.map(userDTO, User.class))
                .map(user -> user.toBuilder().userId(serverRequest.pathVariable("id"))
                        .build());
    }

    public Mono<String> buildRequestListUser(ServerRequest serverRequest) {
        return Mono.just(Objects.requireNonNull(serverRequest.headers().firstHeader("user-admin")));
    }

    public Mono<String> buildRequestDeleteUser(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("id"));
    }
}
