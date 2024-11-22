package co.com.ibero.usecase;


import co.com.ibero.user.User;
import co.com.ibero.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class CreatorUseCase {

    private final UserRepository userRepository;

    public Mono<User> createUser(User user) {

        return userRepository.createUser(user);
    }
}
