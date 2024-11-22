package co.com.ibero.user;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
public class User {
    private String userId;
    private String userName;
    private String userLastName;
    private String userDocumentType;
    private String userDocumentNumber;
    private String userBirthDate;
    private Integer userAge;
    private String userEmail;
    private String userPassword;

}
