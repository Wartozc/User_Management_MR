package co.com.ibero.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class UserDTO {
    private String userName;
    private String userLastName;
    private String userDocumentType;
    private String userDocumentNumber;
    private String userBirthDate;
    private Integer userAge;
    private String userEmail;
    private String userPassword;
}
