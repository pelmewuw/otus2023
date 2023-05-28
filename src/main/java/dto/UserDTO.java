package dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UserDTO{
	String firstName;
	String lastName;
	String password;
	Long userStatus;
	String phone;
	Long id;
	String email;
	String username;
}