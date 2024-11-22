package co.com.ibero.user.gateways;


import co.com.ibero.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> createUser(User user);

    Mono<User> updateUser(User user);

    Flux<User> listUser(String userAdmin);

    Mono<Boolean> deleteUser(String userId);
}
