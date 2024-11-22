package co.com.ibero;

import co.com.ibero.usecase.CreatorUseCase;
import co.com.ibero.usecase.DeleterUseCase;
import co.com.ibero.usecase.ListerUseCase;
import co.com.ibero.usecase.UpdaterUseCase;
import co.com.ibero.util.BuilderServerRequest;
import co.com.ibero.util.BuilderServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final CreatorUseCase creatorUseCase;
    private final UpdaterUseCase updaterUseCase;
    private final ListerUseCase listerUseCase;
    private final DeleterUseCase deleterUseCase;

    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        return BuilderServerRequest.buildRequestCreateUser(serverRequest)
                .flatMap(creatorUseCase::createUser)
                .flatMap(BuilderServerResponse::buildResponseCreateUser);
    }

    public Mono<ServerResponse> updateUser(ServerRequest serverRequest) {
        return BuilderServerRequest.buildRequestUpdateUser(serverRequest)
                .flatMap(updaterUseCase::updateUser)
                .flatMap(BuilderServerResponse::buildResponseUpdateUser);
    }

    public Mono<ServerResponse> listUsers(ServerRequest serverRequest) {
        return BuilderServerRequest.buildRequestListUser(serverRequest)
                .flatMap(userAdmin -> listerUseCase.listUsers(userAdmin).collectList())
                .flatMap(BuilderServerResponse::buildResponseListUsers);
    }

    public Mono<ServerResponse> deleteUser(ServerRequest serverRequest) {
        return BuilderServerRequest.buildRequestDeleteUser(serverRequest)
                .flatMap(deleterUseCase::deleteUser)
                .flatMap(BuilderServerResponse::buildResponseDeleteUser);
    }
}
