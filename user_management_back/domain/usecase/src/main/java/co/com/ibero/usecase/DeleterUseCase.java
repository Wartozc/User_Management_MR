package co.com.ibero.usecase;


import co.com.ibero.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class DeleterUseCase {
    private final UserRepository userRepository;

    public Mono<Boolean> deleteUser(String userId) {

        return userRepository.deleteUser(userId);
    }
}
