package ifgram.service;

import ifgram.Model.User;
import ifgram.Repository.UserRepository;
import ifgram.dto.UserRequest;
import ifgram.dto.UserResponse;
import org.aspectj.weaver.bcel.ExceptionRange;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ifgram.dto.UserResponse.*;


@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public UserResponse criar(UserRequest request) throws Exception {
        if (repository.existsByEmail(request.email())) {
            throw new Exception("Email já cadastrado: " + request.email());
        }
        User usuarioNovo = new User(request.nome(), request.email());
        User usuarioSalvo = repository.save(usuarioNovo);

        return from(usuarioSalvo);
    }

    private UserResponse from(User usuarioSalvo) {
        return null;
    }

    public List<UserResponse> buscarTodosUsuarios() {
        List<User> listaUsuarios = repository.findAll();
        List<UserResponse> listaUsuariosResponse = new ArrayList<>();
        for (User user : listaUsuarios) {
            UserResponse userReponse = new UserResponse(
                    user.getId(),
                    user.getNome(),
                    user.getEmail()
            );
            listaUsuariosResponse.add(userReponse);
        }
        return listaUsuariosResponse;
    }

    public UserResponse atualizarUsuario(UserRequest request) throws Exception {
        Optional<User> usuario = repository.findByEmail(request.email());
        if (usuario.isEmpty()) {
            throw new Exception("usuário não existe na base de dados.");
        }


        User usuarioSalvo = usuario.get();
        usuarioSalvo.setNome(request.nome());
        repository.save(usuarioSalvo);

        return UserResponse.from(usuarioSalvo);
    }
}