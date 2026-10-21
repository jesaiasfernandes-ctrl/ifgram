package ifgram.Controller;

import ifgram.dto.UserRequest;
import ifgram.dto.UserResponse;
import ifgram.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    public final UserService service;

    public UserController(UserService service){
        this.service = service;
    }


    @GetMapping
    public List<UserResponse> getUsers(){
        List<UserResponse> listaUsuarios = service.buscarTodosUsuarios();
        return listaUsuarios;
    }

    @PostMapping
    public UserResponse postUser(UserRequest request) throws Exception {
        UserResponse userResponse = service.criar(request);
        return userResponse;
    }

    @PutMapping
    public UserResponse UpdateUser(UserRequest request) throws Exception {
        return service.atualizarUsuario(request);
    }

    @DeleteMapping
    public String deleteUser(UserRequest request) throws Exception {
        service.deletarUsuario(request);
        return "Usuário deletado com sucesso!";
    }
}