package ifgram.dto;


import ifgram.Model.User;

public record UserResponse(Long id, String nome, String email) {
    public static  UserResponse from(User user){
        return new UserResponse(user.getId(), user.getNome(), user.getEmail());
    }
}