package ifgram.service;

import ifgram.dto.UserRequest;
import ifgram.dto.UserResponse;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public UserResponse criar(UserRequest request) {
        if (repository.existsByemail(request.email())) {
            throw new EmailDuplicadoException(request.email());
        }

        User salvo = repository.save(new User(request.nome(), request.email()));
        return UserResponse.from(salvo);
    }
}
