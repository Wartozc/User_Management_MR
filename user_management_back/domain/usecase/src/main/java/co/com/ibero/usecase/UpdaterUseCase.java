package co.com.ibero.usecase;

import co.com.ibero.user.User;
import co.com.ibero.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class UpdaterUseCase {

    private final UserRepository userRepository;

    public Mono<User> updateUser(User user) {

        return userRepository.updateUser(user);
    }
}
