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

    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping
    public List<UserResponse> getUsers() {
        List<UserResponse> listaUsuarios = service.buscarTodosUsuarios();
        return listaUsuarios;
    }

    @PostMapping
    public String postUser(){
        return "chamei o endpoint como um POST!";
    }

    @DeleteMapping
    public String deleteUser() {
        return "chamei o endpoint como um DELETE!";
    }

    @PutMapping
    public String putUser(){
        return "chamei o endpoint como um PUT!";
    }

    @PatchMapping
    public String patchUser(){
        return "chamei o endpoint como um PATCH!";
    }

}
