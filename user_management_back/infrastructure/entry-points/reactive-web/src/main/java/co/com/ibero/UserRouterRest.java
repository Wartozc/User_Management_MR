package co.com.ibero;

import co.com.ibero.config.UserRouterConfig;
import co.com.ibero.openapi.UserOpenApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route;

@Configuration
@RequiredArgsConstructor
public class UserRouterRest {
    private final UserHandler userHandler;
    private final UserRouterConfig userRouterConfig;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return route().POST(userRouterConfig.getCreate(), userHandler::createUser, UserOpenApi::buildCreateUser)
                .PUT(userRouterConfig.getUpdate(), userHandler::updateUser, UserOpenApi::buildUpdateUser)
                .GET(userRouterConfig.getList(), userHandler::listUsers, UserOpenApi::buildListUsers)
                .DELETE(userRouterConfig.getDelete(), userHandler::deleteUser, UserOpenApi::buildDeleteUser)
                .build();
    }
}
