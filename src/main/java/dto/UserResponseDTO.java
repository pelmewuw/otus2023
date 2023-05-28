package dto;

import lombok.Data;

@Data
public class UserResponseDTO {
  private String firstName;
  private String lastName;
  private String password;
  private Long userStatus;
  private String phone;
  private Long id;
  private String email;
  private String username;
}
