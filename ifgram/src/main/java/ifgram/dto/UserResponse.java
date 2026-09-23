package ifgram.dto;


public record UserResponse(Long id, String nome, String email) {
    public static  UserResponse from(User user){
        return new UserResponse(user.getId(), user.getNome(), user.getEmail());
    }
}


