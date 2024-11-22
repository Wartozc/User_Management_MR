package co.com.ibero.usecase;

import co.com.ibero.user.User;
import co.com.ibero.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;


@RequiredArgsConstructor
public class ListerUseCase {

    private final UserRepository userRepository;

    public Flux<User> listUsers(String userAdmin) {

        return userRepository.listUser(userAdmin);
    }
}
